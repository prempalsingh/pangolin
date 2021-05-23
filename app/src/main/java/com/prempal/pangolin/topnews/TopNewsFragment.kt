package com.prempal.pangolin.topnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopNewsFragment : Fragment() {

    private lateinit var topNewsView: TopNewsView
    private val viewModel: TopNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        topNewsView = TopNewsView(requireContext()) {
            viewModel.retryClicked()
        }
        return topNewsView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            topNewsView.setLayoutState(it)
        }
    }
}
