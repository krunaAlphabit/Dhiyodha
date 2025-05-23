package com.alphabit.dhiyodha.BottomSheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.AddAddressBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddAddressBottomSheet : BottomSheetDialogFragment() {

    private var _binding: AddAddressBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddAddressBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}