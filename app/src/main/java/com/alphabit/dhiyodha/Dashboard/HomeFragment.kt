package com.alphabit.dhiyodha.Dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.alphabit.dhiyodha.App.Constants
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.Retrofit.ApiServiceProvider
import com.alphabit.dhiyodha.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.google.android.play.integrity.internal.k
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.webview.webViewClient = WebViewClient()
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl("https://dhiyodha-buyer-portal.vercel.app/")
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}