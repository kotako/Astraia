package io.gitub.kotako.astraia.articles

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.gitub.kotako.astraia.R
import javax.inject.Inject

class ArticlesActivity: AppCompatActivity(), ArticlesNavigator, ArticleItemNavigator {

    @Inject
    lateinit var viewModel: ArticlesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        val fragment = ArticlesFragment.newInstance()
        viewModel.setNavigator(this)
        (fragment as ArticlesFragment).setViewModel(viewModel)

        supportFragmentManager.beginTransaction().run {
            add(R.id.container, fragment)
            commit()
        }
    }

    override fun onStartArticleDetail() {
        // start activity
    }
}