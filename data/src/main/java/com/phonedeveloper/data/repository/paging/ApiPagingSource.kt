package com.phonedeveloper.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.phonedeveloper.data.repository.datasource.NewsRemoteDataSource
import com.phonedeveloper.domain.model.Article
import com.phonedeveloper.domain.utils.Constants.Companion.DEFAULT_NAME
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 0
private const val LIMIT_PAGE_INDEX = 20

class ApiPagingSource(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        // Start refresh at offset 1 if undefined.
        val nextPage = params.key ?: STARTING_PAGE_INDEX
        return try {
//            var nameStartsWith: String? = null
//            if (!query.isEmpty()) nameStartsWith = query
            val response = newsRemoteDataSource.getNewsArticlePagingOrderBy(
//                nameStartsWith,
                DEFAULT_NAME,
                limit = params.loadSize,
                page = nextPage
            )
            val data = response.data!!.articleItems

            val list = mutableListOf<Article>()
            val article = data?.map { it
//                Article(
//                    it.title,
//                    it.abstract
//                )
            }
            list.addAll(article!!)

            LoadResult.Page(
                data = list,
                prevKey = if (nextPage == params.loadSize) null else nextPage - params.loadSize,
                nextKey = if (list.isEmpty()) null else nextPage + params.loadSize
            )
        } catch (io: IOException) {
            LoadResult.Error(io)
        } catch (http: HttpException) {
            LoadResult.Error(http)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}