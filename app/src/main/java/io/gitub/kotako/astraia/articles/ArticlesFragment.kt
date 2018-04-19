package io.gitub.kotako.astraia.articles

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.gitub.kotako.astraia.R
import io.gitub.kotako.astraia.databinding.FragmentArticlesBinding

class ArticlesFragment: Fragment() {
    companion object {
        fun newInstance(): Fragment = ArticlesFragment()
    }
    private lateinit var viewModel: ArticlesViewModel

    fun setViewModel(viewModel: ArticlesViewModel) {
        this.viewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_articles, container, false)
        val binding = FragmentArticlesBinding.bind(view)
        binding.viewModel = viewModel
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.start()
    }
}