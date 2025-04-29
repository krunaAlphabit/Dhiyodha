package com.alphabit.dhiyodha.Product

data class ProductsModel(
    val data: MutableList<Product>,
)

data class Product(
    val productName: String,
    val description: String,
    val productIdType: String,
    val category: Category,
    val brandName: String,
    val hsnCode: String,
    val gstPercentage: Int,
    val storeId: String,
    val listingPrice: String,
    val countryOfOrigin: String,
    val mrp: String,
    val keywords: String,
    val bulletPoints: String,
    val productPreparationType: String,
    val imagesUrl: MutableList<String>,
    val productUuid: String,
)

data class Category(
    val id: String,
    val sellable: Boolean,
    val browsePath: String,
    val categoryId: String,
    val displayPath: String,
    val label: String,
    val itemType: String?, // "null" in JSON means it can be nullable
    val productType: String,
    val numberOfAuthorizedChildren: Int,
    val level: Int,
    val recommendedBrowseNode: String,
    val confidence: String?, // "null" in JSON means it can be nullable
    val authorized: Boolean,
    val handmade: Boolean,
    val active: Boolean,
)