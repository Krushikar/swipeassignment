package com.example.assignmentswipebalusonawane.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentswipebalusonawane.data.repository.MainRepository
import com.example.assignmentswipebalusonawane.model.AddProductResponse
import com.example.assignmentswipebalusonawane.model.NewProduct
import com.example.assignmentswipebalusonawane.model.Product
import com.example.assignmentswipebalusonawane.utils.NetworkHelper
import com.example.assignmentswipebalusonawane.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _products = MutableLiveData<Resource<List<Product>>>(Resource.Loading())
    val products = _products

    private val _addProducts = MutableLiveData<Resource<AddProductResponse>>(Resource.Loading())
    val addProducts = _addProducts

     fun getProducts(){

        if (!networkHelper.isNetworkConnected()) return _products.postValue(Resource.Error("No internet Connection"))

        _products.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = repository.getProducts()){

                is Resource.Success -> _products.postValue(Resource.Success(result.data))

                is Resource.Error -> _products.postValue(Resource.Error(result.message))

                is Resource.Loading -> _products.postValue(Resource.Loading())

            }
        }

    }

    fun addProduct(
        productName: String,
        productType: String,
        productPrice: String,
        productTax: String
    ){

        if (!networkHelper.isNetworkConnected()) return _addProducts.postValue(Resource.Error("No internet Connection"))

        _addProducts.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = repository.addProduct(
                productName,
                productType,
                productPrice,
                productTax
            )){

                is Resource.Success -> _addProducts.postValue(Resource.Success(result.data))

                is Resource.Error -> _addProducts.postValue(Resource.Error(result.message))

                is Resource.Loading -> _addProducts.postValue(Resource.Loading())

            }
        }

    }
}

