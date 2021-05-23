package com.prempal.pangolin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopNewsFragment : Fragment() {

    private val viewModel: TopNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return HelloWorldView(requireContext(), getString(R.string.top_news))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            Log.d("TopNewsViewState", it.toString())
        }
    }
}
