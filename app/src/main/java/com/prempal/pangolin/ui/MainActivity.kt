package com.prempal.pangolin.ui

import android.content.ActivityNotFoundException
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prempal.pangolin.PangolinApplication
import com.prempal.pangolin.R
import com.prempal.pangolin.di.module.ActivityModule
import com.prempal.pangolin.ui.rv.HeadlineAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var adapter: HeadlineAdapter

    @Inject
    lateinit var customTabsIntent: CustomTabsIntent

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progress_bar)
        setupRecyclerView()
        setupObservers()
    }

    private fun inject() {
        (application as PangolinApplication).appComponent
            .getActivityComponent(ActivityModule(this))
            .inject(this)
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                recyclerView.layoutManager?.run {
                    if (this is LinearLayoutManager
                        && itemCount > 0
                        && itemCount == findLastVisibleItemPosition() + 1
                    ) {
                        viewModel.onLoadMore()
                    }
                }
            }
        })
    }

    private fun setupObservers() {
        viewModel.items.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.loading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.toastEvent.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                showToast(it)
            }
        })

        viewModel.openArticleEvent.observe(this, Observer {
            it.getContentIfNotHandled()?.let { url ->
                try {
                    customTabsIntent.launchUrl(this, Uri.parse(url))
                } catch (ex: ActivityNotFoundException) {
                    showToast(R.string.error_opening_article)
                }
            }
        })
    }

    private fun showToast(@StringRes int: Int) {
        Toast.makeText(this, getString(int), Toast.LENGTH_SHORT).show()
    }
}
