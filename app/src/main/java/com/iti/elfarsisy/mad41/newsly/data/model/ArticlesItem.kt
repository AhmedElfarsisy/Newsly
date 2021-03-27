package com.iti.elfarsisy.mad41.newsly.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ArticlesItem(
    @PrimaryKey(autoGenerate = true)
    val articleId: Int?,
    @field:SerializedName("publishedAt")
    val publishedAt: String?,

    @field:SerializedName("author")
    val author: String?,

    @field:SerializedName("urlToImage")
    val urlToImage: String?,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("source")
    val source: Source,

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("url")
    val url: String?,

    @field:SerializedName("content")
    val content: String?
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("source"),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(articleId)
        parcel.writeString(publishedAt)
        parcel.writeString(author)
        parcel.writeString(urlToImage)
        parcel.writeString(description)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticlesItem> {
        override fun createFromParcel(parcel: Parcel): ArticlesItem {
            return ArticlesItem(parcel)
        }

        override fun newArray(size: Int): Array<ArticlesItem?> {
            return arrayOfNulls(size)
        }
    }
}