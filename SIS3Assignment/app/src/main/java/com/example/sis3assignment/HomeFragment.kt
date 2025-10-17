package com.example.sis3assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.navigateButton)
        button.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment("Hello from HomeFragment!")
            findNavController().navigate(action)
        }
    }
}