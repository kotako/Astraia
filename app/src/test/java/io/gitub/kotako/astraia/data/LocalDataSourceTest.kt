package io.gitub.kotako.astraia.data

import io.gitub.kotako.astraia.BuildConfig
import io.gitub.kotako.astraia.data.Entity.*
import io.gitub.kotako.astraia.data.source.local.LocalDataSource
import io.realm.Realm
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.CountDownLatch

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [27])
class LocalDataSourceTest {

    private lateinit var localDataSource: LocalDataSource
    private lateinit var realm: Realm
    private lateinit var latch: CountDownLatch
    private val sampleArticle: Article = object : Article {
        override val id: Long? = 100L
        override val link: String? = null
        override val linkJson: String? = null
        override val title: String? = "testobject"
        override val titleInEnglish: String? = null
        override val description: String? = null
        override val lang: String? = null
        override val authors: List<Author?>? = null
        override val publishers: List<Publisher?>? = null
        override val publications: List<Publication?>? = null
        override val sources: List<Source?>? = null
        override val issn: String? = null
        override val nacsisId: String? = null
        override val dnlId: String? = null
        override val citedCount: Int? = null
        override val journalNumber: Int? = null
        override val issueNumber: Int? = null
        override val startingPage: Int? = null
        override val endingPage: Int? = null
        override val publishedAt: String? = null
        override val jounal: Journal? = null
        override val relatedLinks: List<Link?>? = null
        override val topics: List<Topic?>? = null
        override val image: Link? = null
    }

    @Before
    fun init() {
        realm = Mockito.mock(Realm::class.java)
        localDataSource = LocalDataSource(realm)
        latch = CountDownLatch(1)
    }

    @Test
    fun お気に入りのArticleデータを保存する() {
        assert(true)
    }

    @Test
    fun 後で読むArticleデータを保存する() {
        assert(true)
    }

    @Test
    fun 同じデータを追加すると上書きされる() {
        assert(true)
    }

    @Test
    fun LiveDataの取得() {
        assert(true)
    }
}