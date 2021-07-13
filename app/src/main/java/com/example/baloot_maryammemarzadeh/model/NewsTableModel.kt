package com.example.baloot_maryammemarzadeh.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.rxjava3.annotations.NonNull

@Entity(tableName = "Movies")
data class NewsTableModel (
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "imdbID")
    var id: String,

    @ColumnInfo(name = "title")
    var Title: String,

    @ColumnInfo(name = "Year")
    var Year: String,


) {


}