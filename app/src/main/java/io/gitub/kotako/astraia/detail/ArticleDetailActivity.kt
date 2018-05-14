package io.gitub.kotako.astraia.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
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
        ViewModelProviders.of(this, viewModelFactory).get(ArticleDetailViewModel::class.java)
    }
    private lateinit var binding: ActivityArticleDetailBinding
    private val readLaterObserver = Observer<Boolean> { readLater: Boolean? ->
        if (readLater == true) {
            binding.readLatorButton.setImageResource(R.drawable.ic_turned_in_red)
        } else {
            binding.readLatorButton.setImageResource(R.drawable.ic_turned_in)
        }
    }
    private val favoriteObserver = Observer<Boolean> { favorite: Boolean? ->
        if (favorite == true) {
            binding.favoriteButton.setImageResource(R.drawable.ic_favorite_red)
        } else {
            binding.favoriteButton.setImageResource(R.drawable.ic_favorite_border_black_24dp)
        }
    }
    private val expandObserver = Observer<Boolean> { expand: Boolean? ->
        if (expand == true) {
            binding.expandButton.setImageResource(R.drawable.ic_expand_less)
        } else {
            binding.expandButton.setImageResource(R.drawable.ic_expand_more)
        }
    }

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
        binding.setLifecycleOwner(this)

        viewModel.setArticle(intent.extras[ARTICLE] as Article)
        viewModel.setNavigator(this)
        viewModel.article.observeForever({})
        viewModel.isLoading.observeForever({})
        viewModel.isExpand.observeForever(expandObserver)
        viewModel.readLater.observeForever(readLaterObserver)
        viewModel.favorite.observeForever(favoriteObserver)

        binding.viewModel = viewModel
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun openBrowser() {
    }

    override fun searchAuthor() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}