package com.hectre.ez.data.domainmodels

import com.hectre.ez.data.local.DBPackage
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type


data class PackageModel(
    val id: String,
    val order: Long,
    val alias: String,
    val pickMoney: Int,
    val pickTel: String?,
    val pickFullName: String?,
    val pickProvince: String?,
    val stationId: Long?,
    val products: List<ProductModel>
)

data class ProductModel(val productName: String, val quantity: Int)

fun List<PackageModel>.asDbPackageModels(): List<DBPackage> {
    return map {
        it.asDbPackageModel()
    }
}

fun PackageModel.asDbPackageModel(): DBPackage {
    val listOfCardsType: Type = Types.newParameterizedType(
        List::class.java,
        ProductModel::class.java
    )
    val jsonAdapter: JsonAdapter<List<ProductModel>> =
        Moshi.Builder().build().adapter(listOfCardsType)

    return DBPackage(
        id = id,
        order = order,
        alias = alias,
        pickFullName = pickFullName,
        pickMoney = pickMoney,
        pickTel = pickTel,
        pickProvince = pickProvince,
        stationId = stationId,
        productInfoString = jsonAdapter.toJson(products)
    )

}



