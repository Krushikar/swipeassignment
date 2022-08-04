package com.example.assignmentswipebalusonawane.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentswipebalusonawane.R
import com.example.assignmentswipebalusonawane.databinding.ViewListOfProductsBinding
import com.example.assignmentswipebalusonawane.model.Product
import com.example.assignmentswipebalusonawane.ui.adapter.AdapterProducts
import com.example.assignmentswipebalusonawane.ui.viewmodel.MainViewModel
import com.example.assignmentswipebalusonawane.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewProducts : Fragment() {

    private var _binding: ViewListOfProductsBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var productAdapter: AdapterProducts
    private var products = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ViewListOfProductsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress("show")
        setRecyclerView()
        if (!mainViewModel.productConsume) mainViewModel.getProducts()
        observeData()


        binding.buttonNewProduct.setOnClickListener {

            findNavController().navigate(R.id.action_viewProducts_to_viewAddProduct)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun setRecyclerView() {

        productAdapter = AdapterProducts(products)

        binding.viewProductsRecycler.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = productAdapter
            setHasFixedSize(true)
        }

    }

    private fun observeData() {

        mainViewModel.products.observe(viewLifecycleOwner) {

            when (it) {

                is Resource.Success -> {
                    it.data?.let { products ->
                        productAdapter.addProducts(products)
                        mainViewModel.setProductConsume(true)

                    }
                    progress("hide")
                }

                is Resource.Error -> {
                    progress("hide")
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {

                   if (!mainViewModel.productConsume) progress("show")
                }

            }

        }

    }

    fun progress(state: String = "show") {

        binding.progressBar.visibility = if (state == "show") View.VISIBLE else View.INVISIBLE

    }
}