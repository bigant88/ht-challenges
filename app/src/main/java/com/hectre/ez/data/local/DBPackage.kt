package com.hectre.ez.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hectre.ez.data.domainmodels.PackageModel
import com.hectre.ez.data.domainmodels.ProductModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type


const val PACKAGE_TABLE_NAME = "package_table"

@Entity(tableName = PACKAGE_TABLE_NAME)
data class DBPackage constructor(
    @PrimaryKey
    val id: String,
    val order: Long,
    val alias: String,
    val pickMoney: Int,
    val pickTel: String?,
    val pickFullName: String?,
    val pickProvince: String?,
    val stationId: Long?,
    val productInfoString: String? //save Product array to Json String, no need to save to another table
)

/**
 * Map DBPackage to domain entities
 */
fun List<DBPackage>.asPackageModels(): List<PackageModel> {
    return map {
        it.asPackageModel()
    }
}

fun DBPackage.asPackageModel(): PackageModel {
    val listOfCardsType: Type = Types.newParameterizedType(
        List::class.java,
        ProductModel::class.java
    )
    val jsonAdapter: JsonAdapter<List<ProductModel>> =
        Moshi.Builder().build().adapter(listOfCardsType)
    return PackageModel(
        id = id,
        order = order,
        alias = alias,
        pickFullName = pickFullName,
        pickMoney = pickMoney,
        pickTel = pickTel,
        pickProvince = pickProvince,
        stationId = stationId,
        products = productInfoString?.let { jsonAdapter.fromJson(it) } ?: listOf()
    )
}

