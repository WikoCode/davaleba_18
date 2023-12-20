package com.example.davaleba_18.fragments

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
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
        setupLoading()
    }

    override fun setupListeners() {
        setupRetry()
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

    private fun setupLoading() {
        usersAdapter.addLoadStateListener { loadState ->
            binding.progressBar.visibility = if (loadState.source.append is LoadState.Loading ||
                loadState.source.refresh is LoadState.Loading
            ) {
                View.VISIBLE
            } else {
                View.GONE
            }

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.refresh as? LoadState.Error
            errorState?.let {
                Toast.makeText(context, "Error: ${it.error.message}", Toast.LENGTH_LONG).show()
                binding.buttonRetry.visibility = View.VISIBLE
            }
        }
    }


    private fun setupRetry() {
        binding.buttonRetry.setOnClickListener {
            usersAdapter.retry()
        }
    }


}


