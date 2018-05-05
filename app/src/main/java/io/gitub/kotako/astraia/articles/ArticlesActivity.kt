package io.gitub.kotako.astraia.articles

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.gitub.kotako.astraia.R
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.di.ViewModelFactory
import kotlinx.android.synthetic.main.activity_articles.*
import javax.inject.Inject

class ArticlesActivity: DaggerAppCompatActivity(), ArticlesNavigator {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var repository: ArticleRepository
    private val viewModel: ArticlesViewModel by lazy {
        viewModelFactory.create(ArticlesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        setSupportActionBar(toolbar)

        val fragment = ArticlesFragment.newInstance()
        viewModel.setNavigator(this)
        fragment.setViewModel(viewModel)
        supportFragmentManager.beginTransaction().run {
            add(R.id.container, fragment)
            commit()
        }
    }
}