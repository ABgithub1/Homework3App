package com.example.homework3app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.homework3app.R
import com.example.homework3app.databinding.FragmentDetailsBinding
import com.example.homework3app.databinding.FragmentListBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding) { "Destroyed" }

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            nameDetails.text = args.name
            nicknameDetails.text = args.nickname
            dateOfBirthDetails.text = args.dateOfBirth
            statusDetais.text = args.status
            imageDetails.load(args.imageUrl)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}