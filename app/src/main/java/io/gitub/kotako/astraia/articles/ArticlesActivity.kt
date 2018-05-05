package io.gitub.kotako.astraia.articles

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val view = (menu.findItem(R.id.search) as MenuItem).actionView as SearchView
        val searchService = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        view.setSearchableInfo(searchService.getSearchableInfo(componentName))
        view.isIconified = true

        return true
    }
}