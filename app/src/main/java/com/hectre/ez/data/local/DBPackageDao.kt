package com.hectre.ez.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DBPackageDao {

    @Query("select * from $PACKAGE_TABLE_NAME")
    suspend fun getAllPackages(): List<DBPackage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(packages: List<DBPackage>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdatePackage(dbPackage: DBPackage)

    @Query("DELETE FROM $PACKAGE_TABLE_NAME")
    suspend fun deleteAll()

}