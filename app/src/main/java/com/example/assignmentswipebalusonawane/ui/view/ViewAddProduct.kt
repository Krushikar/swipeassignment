package com.example.assignmentswipebalusonawane.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.assignmentswipebalusonawane.R
import com.example.assignmentswipebalusonawane.databinding.ViewAddProductBinding
import com.example.assignmentswipebalusonawane.model.NewProduct
import com.example.assignmentswipebalusonawane.ui.viewmodel.MainViewModel
import com.example.assignmentswipebalusonawane.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewAddProduct : Fragment() {

    //binding for bind the ui to fragment
    private var _binding: ViewAddProductBinding? = null
    private val binding get() = _binding!!

    //getting view model by lazy
    private val mainViewmodel: MainViewModel by viewModel()

    private lateinit var newProduct: NewProduct
    private var product_type = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ViewAddProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Toggling progressbar
        progress("hide")

        binding.radioGroup.setOnCheckedChangeListener { _, id ->

            when (id) {

                R.id.product -> {
                    product_type = "product"
                }

                R.id.service -> {
                    product_type = "service"
                }
            }
        }

        binding.buttonAddProduct.setOnClickListener {

            if (validateData()){

              //  Toast.makeText(context, "Adding Product", Toast.LENGTH_SHORT).show()
                mainViewmodel.addProduct(
                    newProduct.product_name,
                    newProduct.product_type,
                    newProduct.price,
                    newProduct.tax
                )
                //Start observing live data to get information about product adding request
                observeData()

            }

        }

    }

    private fun observeData(){


        mainViewmodel.addProducts.observe(viewLifecycleOwner){ it ->

            when(it){

                is Resource.Success -> {
                    it.data?.let { data ->
                        Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
                    }
                    findNavController().navigate(R.id.action_viewAddProduct_to_viewProducts)
                    progress("hide")
                }

                is Resource.Error -> {
                    progress("hide")
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {

                    progress("show")
                }

            }

        }

    }

    private fun progress(state: String = "show") {

        binding.progressBar2.visibility = if (state == "show" ) View.VISIBLE else View.INVISIBLE
        binding.buttonAddProduct.visibility = if (state == "hide" ) View.VISIBLE else View.INVISIBLE
    }

    //Validating the user entered data
    private fun validateData() : Boolean {

        if (product_type.isEmpty()) {
            Toast.makeText(context, "Please  select product type", Toast.LENGTH_SHORT).show()
            return false
        }

        val productName = binding.editTextTextProductName.text.toString()
        if (productName.isEmpty()) {
            Toast.makeText(context, "Please  enter product name", Toast.LENGTH_SHORT).show()
            return false
        }

        val productPrice = binding.editTextTextProductPrice.text.toString()
        if (productPrice.isEmpty()) {
            Toast.makeText(context, "Please  enter product price", Toast.LENGTH_SHORT).show()
            return false
        }

        val productTax = binding.editTextTextProductTax.text.toString()
        if (productTax.isEmpty()) {
            Toast.makeText(context, "Please  enter product tax", Toast.LENGTH_SHORT).show()
            return false
        }

        newProduct = NewProduct(productPrice,productName,product_type,productTax)
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}