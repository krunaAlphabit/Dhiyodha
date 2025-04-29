package com.alphabit.dhiyodha.Dashboard

class MainCategoriesModel(
    val id: String,
    val uuid: String?,
    val createdAt: String?,
    val createdBy: String?,
    val updatedAt: String?,
    val updatedBy: String?,
    val sellable: Boolean,
    val browsePath: String?,
    val categoryId: String?,
    val displayPath: String,
    val label: String,
    val itemType: String?,
    val productType: String,
    val numberOfAuthorizedChildren: Int,
    val level: Int,
    val recommendedBrowseNode: String,
    val confidence: String?,
    val authorized: Boolean,
    val handmade: Boolean,
    val active: Boolean,
)