package com.example.assignmentswipebalusonawane.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.assignmentswipebalusonawane.R
import com.example.assignmentswipebalusonawane.model.Product
import okio.blackholeSink
import kotlin.math.roundToInt

class AdapterProducts(
    private val list: MutableList<Product>
) : RecyclerView.Adapter<AdapterProducts.ViewHolder>() {

    inner class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_view_product, parent, false)) {

        val name: TextView? = itemView.findViewById(R.id.product_name)
        val type: TextView? = itemView.findViewById(R.id.product_type)
        val price: TextView? = itemView.findViewById(R.id.product_price)
        val tax: TextView? = itemView.findViewById(R.id.product_tax)

        val image: ImageView? = itemView.findViewById(R.id.product_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = list[position]

        holder.apply {

            name?.text = product.product_name
            type?.text = product.product_type
            price?.text = "${product.price.roundToInt()}"
            tax?.text = "Tax:${product.tax}%"

            image?.load(product.image){
                placeholder(R.drawable.docs)
                error(R.drawable.docs)
            }
        }

    }

    override fun getItemCount() = list.size

    fun addProducts(product: List<Product>){
        this.list.clear()
        this.list.addAll(product)
        notifyDataSetChanged()
    }

}