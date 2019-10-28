package com.mevent.mgallery.models

data class ResponseMode(
    val totalItems: Int,
    val itemsPerPage: Int,
    val countOfPages: Int,
    val data: List<Data>
)

data class Data (
    val id: Int,
    val name: String,
    val description: String,
    val new: Boolean,
    val popular: Boolean,
    val image: Image
)

data class Image (
    val id: Int,
    val contentUrl: String
)