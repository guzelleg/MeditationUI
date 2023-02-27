package com.guzelgimadieva.stockmarketapp.data.mapper

import com.guzelgimadieva.stockmarketapp.data.local.CompanyListingEntity
import com.guzelgimadieva.stockmarketapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing{
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity{
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}