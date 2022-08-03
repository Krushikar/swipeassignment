package com.example.assignmentswipebalusonawane.data.remote

import com.example.assignmentswipebalusonawane.model.AddProductResponse
import com.example.assignmentswipebalusonawane.model.NewProduct
import com.example.assignmentswipebalusonawane.model.Product
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET("get")
    suspend fun getProduct() : Response<List<Product>>

    @FormUrlEncoded
    @POST("add")
    suspend fun addProduct(
        @Field("product_name") productName: String,
        @Field("product_type") productType: String,
        @Field("price") productPrice: String,
        @Field("tax") productTax: String,
    ) : Response<AddProductResponse>

}