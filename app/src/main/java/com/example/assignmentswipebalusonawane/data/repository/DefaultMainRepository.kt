package com.example.assignmentswipebalusonawane.data.repository

import android.util.Log
import com.example.assignmentswipebalusonawane.data.remote.Api
import com.example.assignmentswipebalusonawane.model.AddProductResponse
import com.example.assignmentswipebalusonawane.model.NewProduct
import com.example.assignmentswipebalusonawane.model.Product
import com.example.assignmentswipebalusonawane.utils.Resource
import retrofit2.Response
import java.lang.Exception

//Implementation of main repository to get actual data
class DefaultMainRepository(val api: Api) : MainRepository {

    //Getting list of products from api
    override suspend fun getProducts(): Resource<List<Product>> {

        return try {

            val response = api.getProduct()

            if (!response.isSuccessful) {
                Resource.Error("Request Failed ${response.message()}")
            } else {
                val result = response.body()
                if (result != null) Resource.Success(response.body()) else Resource.Error("No more products")
            }

        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }

    }

    //Passing data to api
    override suspend fun addProduct(
        productName: String,
        productType: String,
        productPrice: String,
        productTax: String
    ): Resource<AddProductResponse> {

        return try {
            val response = api.addProduct(
                productName,
                productType,
                productPrice,
                productTax
            )

            if (!response.isSuccessful){

                Resource.Error(response.message() ?: "Failed Request")
            }else{

                val result = response.body()
                if (result?.success == true) Resource.Success(result) else Resource.Error(result?.message)
            }

        }catch (e: Exception){
            Resource.Error(e.message ?: "Unknown error")
        }

    }

}