package io.gitub.kotako.astraia.data.source.remote.api

import io.gitub.kotako.astraia.BuildConfig
import io.gitub.kotako.astraia.data.source.remote.response.ArticleResponse
import io.gitub.kotako.astraia.data.source.remote.response.ArticlesResponse
import io.gitub.kotako.astraia.data.source.remote.response.AuthorResponse
import io.gitub.kotako.astraia.data.source.remote.response.AuthorsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CiniiApi {
//  sort order -> 出版年 降順/昇順(1 / 2)
//                論文名 降順/昇順(3 / 4)
//                刊行物 降順/昇順(5 / 6)
//                非引用文献数 降順(7)
    @GET("/opensearch/search")
    fun fetchArticles(@Query("q") keyword: String,
                      @Query("count") count: Int? = null,
                      @Query("lang") lang: String? = null,
                      @Query("start") startIndex: Int? = null,
                      @Query("format") format: String = "json",
                      @Query("title") title: String? = null,
                      @Query("author") author: String? = null,
                      @Query("aurhorid") authorId: Long? = null,
                      @Query("issn") issn: String? = null,
                      @Query("publisher") publisher: String? = null,
                      @Query("affiliation") affiliation: String? = null,
                      @Query("journal") journal: String? = null,
                      @Query("volume") volumeNumber: Int? = null,
                      @Query("issue") issueNumber: Int? = null,
                      @Query("page") page: Int? = null,
                      @Query("references") references: String? = null,
                      @Query("year_from") yearFrom: Int? = null,
                      @Query("year_to") yearTo: Int? = null,
                      @Query("range") articleBodyAvailable: Int? = null,
                      @Query("sortorder") sort: Int? = null,
                      @Query("appid") appId: String = BuildConfig.CINII_API_KEY): Observable<ArticlesResponse>

    @GET("/opensearch/author")
    fun fetchAuthors(@Query("q")keyword: String,
                     @Query("count") count: Int? = null,
                     @Query("lang") lang: String? = null,
                     @Query("start") startIndex: Int? = null,
                     @Query("format") format: String = "json",
                     @Query("sortorder") sort: Int? = null,
                     @Query("appid") appId: String = BuildConfig.CINII_API_KEY): Observable<AuthorsResponse>

    @GET("/naid/{article_id}.json")
    fun fetchArticle(@Path("article_id") articleId: Long): Observable<ArticleResponse>

    @GET("/nrid/{author_id}.json")
    fun fetchAuthor(@Path("author_id") authorId: Long?): Observable<AuthorResponse>
}