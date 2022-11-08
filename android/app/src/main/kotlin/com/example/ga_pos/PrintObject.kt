package com.example.ga_pos

import com.squareup.moshi.Json

data class PrintObject(@Json(name = "Receipt") val printFields: List<PrintField>)

data class PrintField(
    @Json(name = "Bitmap") val filename: String,
    @Json(name = "letterSpacing") val letterSpacing: Int,
    @Json(name = "String") val stringFields: List<StringField>
)

data class StringField(
    @Json(name = "isMultiline") val isMultiline: Boolean,
    @Json(name = "header") val header: TextField,
    @Json(name = "body") val body: TextField
)

data class TextField(
    @Json(name = "text") val text: String,
    @Json(name = "align") val align: String?,
    @Json(name = "size") val size: String?,
    @Json(name = "isBold") val isBold: Boolean?
)

