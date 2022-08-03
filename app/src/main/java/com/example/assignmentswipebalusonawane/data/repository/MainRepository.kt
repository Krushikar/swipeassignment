package com.example.assignmentswipebalusonawane.data.repository

import com.example.assignmentswipebalusonawane.model.AddProductResponse
import com.example.assignmentswipebalusonawane.model.NewProduct
import com.example.assignmentswipebalusonawane.model.Product
import com.example.assignmentswipebalusonawane.utils.Resource
import retrofit2.http.Field

interface MainRepository {

    suspend fun getProducts() : Resource<List<Product>>

    suspend fun addProduct(
        productName: String,
        productType: String,
        productPrice: String,
        productTax: String
    ) : Resource<AddProductResponse>

}