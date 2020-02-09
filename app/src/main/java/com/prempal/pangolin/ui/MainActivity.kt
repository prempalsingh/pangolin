package com.prempal.pangolin.ui

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prempal.pangolin.PangolinApplication
import com.prempal.pangolin.R
import com.prempal.pangolin.di.component.DaggerActivityComponent
import com.prempal.pangolin.di.module.ActivityModule
import com.prempal.pangolin.ui.rv.HeadlineAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var adapter: HeadlineAdapter

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
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as PangolinApplication).appComponent)
            .activityModule(ActivityModule(this))
            .build()
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
                        //TODO: load more
                    }
                }
            }
        })
    }

    private fun setupObservers() {

    }
}