package com.hectre.ez.data.network

import com.hectre.ez.data.domainmodels.PackageModel
import com.hectre.ez.data.domainmodels.ProductModel
import com.hectre.ez.data.local.DBPackage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * These are responsible for parsing responses from the server
 * or formatting objects to send to the server. You should convert these to domain objects before
 * using them.
 */
/**
 *
 * This is to parse first level of our network result which looks like
 *
 * {
 *   "videos": []
 * }
 */
//@JsonClass(generateAdapter = true)
//data class NetworkPackageContainer(@Json(name = "videos") val packages: List<NetworkPackage>)

@JsonClass(generateAdapter = true)
data class NetworkPackage(
    val id: String,
    val order: Long?,
    val alias: String,
    @Json(name = "pick_money") val pickMoney: Int?,
    @Json(name = "pick_tel") val pickTel: String?,
    @Json(name = "pick_fullname") val pickFullName: String?,
    @Json(name = "pick_province") val pickProvince: String?,
    @Json(name = "station_id") val stationId: Long?,
    @Json(name = "Product") val products: List<NetworkProduct>?
)

/**
 * convert Network results to domain models
 */
fun List<NetworkPackage>.asPackageModels(): List<PackageModel> {
    return map {
        it.asPackageModel()
    }
}

fun NetworkPackage.asPackageModel(): PackageModel{
    return PackageModel(
        id = id,
        order = order ?: 0,
        alias = alias,
        pickFullName = pickFullName,
        pickMoney = pickMoney ?: 0,
        pickTel = pickTel,
        pickProvince = pickProvince,
        stationId = stationId,
        products = products?.asProductModels() ?: listOf()

    )
}

//fun List<NetworkPackage>.asDatabaseModels(): List<DBPackage> {
//    return map {
//        DBPackage(
//            id = id,
//            order = order ?: 0,
//            alias = alias,
//            pickFullName = pickFullName,
//            pickMoney = pickMoney ?: 0,
//            pickTel = pickTel,
//            pickProvince = pickProvince,
//            stationId = stationId
//        )
//    }
//}

///
@JsonClass(generateAdapter = true)
data class NetworkProduct(
    @Json(name = "product_name") val productName: String?,
    val quantity: Int?
)

fun List<NetworkProduct>.asProductModels(): List<ProductModel> {
    return map {
        it.asProductModel()
    }
}

fun NetworkProduct.asProductModel(): ProductModel {
    return ProductModel(
        productName = productName ?: "",
        quantity = quantity ?: 0
    )
}