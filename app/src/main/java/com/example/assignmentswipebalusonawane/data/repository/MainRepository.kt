package com.example.assignmentswipebalusonawane.data.repository

import com.example.assignmentswipebalusonawane.model.AddProductResponse
import com.example.assignmentswipebalusonawane.model.NewProduct
import com.example.assignmentswipebalusonawane.model.Product
import com.example.assignmentswipebalusonawane.utils.Resource
import retrofit2.http.Field

//This is a main repository where our viewmodel will communicate to get the required data
interface MainRepository {

    suspend fun getProducts() : Resource<List<Product>>

    suspend fun addProduct(
        productName: String,
        productType: String,
        productPrice: String,
        productTax: String
    ) : Resource<AddProductResponse>

}