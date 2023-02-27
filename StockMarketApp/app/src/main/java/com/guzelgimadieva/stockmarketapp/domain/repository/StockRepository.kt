package com.guzelgimadieva.stockmarketapp.domain.repository

import com.guzelgimadieva.stockmarketapp.domain.model.CompanyListing
import com.guzelgimadieva.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query:String
    ): Flow<Resource<List<CompanyListing>>>
}
