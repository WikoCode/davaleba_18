package com.example.davaleba_18.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.davaleba_18.adapters.UsersAdapter
import com.example.davaleba_18.databinding.FragmentUsersBinding
import com.example.davaleba_18.fragments.basefragment.BaseFragment
import com.example.davaleba_18.viewmodels.UsersViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {


    private val viewModel: UsersViewModel by viewModels()
    private lateinit var usersAdapter: UsersAdapter

    override fun setupUI() {
        setupRecycler()
        setupObservers()
    }

    override fun setupListeners() {
    }

    private fun setupRecycler() {
        usersAdapter = UsersAdapter()
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userList
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collectLatest { pagedData ->
                    usersAdapter.submitData(pagedData)
                }
        }
    }
}