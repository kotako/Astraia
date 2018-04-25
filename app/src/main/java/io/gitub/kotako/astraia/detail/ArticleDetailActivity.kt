package io.gitub.kotako.astraia.detail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.gitub.kotako.astraia.R
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.databinding.ActivityArticleDetailBinding
import io.gitub.kotako.astraia.di.ViewModelFactory
import javax.inject.Inject

class ArticleDetailActivity : DaggerAppCompatActivity(), ArticleDetailNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: ArticleDetailViewModel by lazy {
        viewModelFactory.create(ArticleDetailViewModel::class.java)
    }
    private lateinit var binding: ActivityArticleDetailBinding

    companion object {
        const val ARTICLE = "ARTICLE_KEY"
        fun start(context: Context, article: Article) {
            context.startActivity(Intent(context, ArticleDetailActivity::class.java).apply {
                putExtra(ARTICLE, article)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail)

        viewModel.setNavigator(this)
        binding.article = intent.extras[ARTICLE] as Article
        binding.viewModel = viewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun openBrowser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchAuthor() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}