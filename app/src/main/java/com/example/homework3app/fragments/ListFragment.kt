package com.example.homework3app.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework3app.adapter.PersonListAdapter
import com.example.homework3app.data.Person
import com.example.homework3app.databinding.FragmentListBinding
import com.example.homework3app.extentions.addSpaceDecoration
import com.example.homework3app.retrofit.ProvideRetrofit
import retrofit2.Call
import retrofit2.Response

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding) { "Destroyed" }

    private val adapter by lazy {
        PersonListAdapter() {
            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailsFragment(
                    name = it.name,
                    nickname = it.nickname,
                    dateOfBirth = it.birthday,
                    status = it.status,
                    imageUrl = it.img
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            swipeRefresh.setOnRefreshListener {
                Handler(Looper.getMainLooper())
                    .postDelayed(1000) {
                        adapter.submitList(adapter.currentList.shuffled())
                        swipeRefresh.isRefreshing = false
                    }
            }

            val request = ProvideRetrofit.breakingBadApi.getPersons()
            request.enqueue(object : retrofit2.Callback<List<Person>> {
                override fun onResponse(
                    call: Call<List<Person>>,
                    response: Response<List<Person>>
                ) {
                    if (response.isSuccessful) {

                        val persons = response.body() ?: return
                        adapter.submitList(persons)
                    }
                }

                override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG)
                        .show()
                }
            })

            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.addSpaceDecoration(8)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}