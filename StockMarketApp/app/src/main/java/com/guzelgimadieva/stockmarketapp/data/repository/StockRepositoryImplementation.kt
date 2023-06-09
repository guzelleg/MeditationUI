package com.guzelgimadieva.stockmarketapp.data.repository

import com.guzelgimadieva.stockmarketapp.data.local.StockDatabase
import com.guzelgimadieva.stockmarketapp.data.mapper.toCompanyListing
import com.guzelgimadieva.stockmarketapp.data.remote.StockApi
import com.guzelgimadieva.stockmarketapp.domain.model.CompanyListing
import com.guzelgimadieva.stockmarketapp.domain.repository.StockRepository
import com.guzelgimadieva.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImplementation @Inject constructor(
    val api: StockApi,
    val db: StockDatabase
) : StockRepository {
    private val dao = db.dao
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow  {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(data = localListings.map {
                it.toCompanyListing()
            }))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings()
            }
            catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
            catch (e:HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
        }
    }
}