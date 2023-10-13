package com.example.kisileruygulamasi.entity

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "kisiler")
data class Kisiler(
    @SuppressLint("KotlinNullnessAnnotation") @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kisi_id") @NotNull var kisi_id: Int,
    @SuppressLint("KotlinNullnessAnnotation") @ColumnInfo(name = "kisi_adi") @NotNull var kisi_adi: String,
    @SuppressLint("KotlinNullnessAnnotation") @ColumnInfo(name = "kisi_tel") @NotNull var kisi_tel: String,
) {

}