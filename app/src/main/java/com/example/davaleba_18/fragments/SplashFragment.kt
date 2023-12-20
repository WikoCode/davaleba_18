package com.example.davaleba_18.fragments

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.davaleba_18.R
import com.example.davaleba_18.databinding.FragmentSplashBinding
import com.example.davaleba_18.fragments.basefragment.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun setupUI() {

        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(R.id.action_splashFragment_to_usersFragment)
        }
    }

    override fun setupListeners() {
    }
}