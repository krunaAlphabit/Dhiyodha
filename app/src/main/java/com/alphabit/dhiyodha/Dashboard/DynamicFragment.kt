package com.alphabit.dhiyodha.Dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alphabit.dhiyodha.databinding.FragmentDynamicBinding

class DynamicFragment : Fragment() {

    private var _binding: FragmentDynamicBinding? = null
    private val binding get() = _binding!!
    var variable: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            variable = requireArguments().getInt("someInt", 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDynamicBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun addfrag(`val`: Int): DynamicFragment {
            val fragment = DynamicFragment()
            val args = Bundle()
            args.putInt("someInt", `val`)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}