package com.paul.groceryapp.Fragments

import CartAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.Model.CartItem
import com.paul.groceryapp.R
import com.paul.groceryapp.viewModel.CartViewModel


class CartFragment : Fragment(),CartAdapter.OnCartItemClickListener{

    private lateinit var totalTextView: TextView
    private lateinit var totalPriceTextView: TextView
    private lateinit var discountTextView: TextView
    private lateinit var finalPriceTextView: TextView

    private lateinit var totalItemTextView:TextView
    private lateinit var priceTextView:TextView
    private lateinit var discountView:TextView
    private lateinit var viewCheck:View
    private lateinit var totalPriceTextFinal:TextView
    private lateinit var checkOutTextview:TextView
    private lateinit var oppssText:TextView
    private lateinit var boxImage:ImageView
    private lateinit var textViewItem:TextView
    private lateinit var startBrowsingText:TextView

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    //private val cartList = mutableListOf<CartItem>()
   /* companion object {
        val cartList = mutableListOf<CartItem>()
    }*/

    private lateinit var viewModel: CartViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)


        recyclerView = view.findViewById(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        totalTextView = view.findViewById(R.id.totalItem)
        totalPriceTextView = view.findViewById(R.id.price)
        discountTextView = view.findViewById(R.id.discountText)
        finalPriceTextView = view.findViewById(R.id.finalPriceTextView)

        totalItemTextView = view.findViewById(R.id.totalItemText)
        priceTextView = view.findViewById(R.id.priceText)
        discountView = view.findViewById(R.id.discount)
        viewCheck = view.findViewById(R.id.viewBar)
        totalPriceTextFinal = view.findViewById(R.id.totalPriceText)
        checkOutTextview = view.findViewById(R.id.checkOutText)
        boxImage = view.findViewById(R.id.boxImageView)
        oppssText = view.findViewById(R.id.oppssTextView)
        textViewItem = view.findViewById(R.id.textView)
        startBrowsingText = view.findViewById(R.id.startBrowsing)


        val backImageView = view.findViewById<ImageView>(R.id.backToMainFrg)
        backImageView.setOnClickListener {
            val homeFragment = MainFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, homeFragment)
                .addToBackStack(null)
                .commit()
        }

        startBrowsingText.setOnClickListener {
            val homeFragment = MainFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, homeFragment)
                .addToBackStack(null)
                .commit()

        }

        val checkOutText =view.findViewById<TextView>(R.id.checkOutText)
        checkOutText.setOnClickListener {
            val addressFragment = CheckOutFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, addressFragment)
                .addToBackStack(null)
                .commit()
        }

        viewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]

        adapter = CartAdapter(mutableListOf(), this) // Empty list initially
        recyclerView.adapter = adapter

// Observe LiveData from Room DB
        viewModel.allCartItems.observe(viewLifecycleOwner) { items ->
            adapter = CartAdapter(items, this)
            recyclerView.adapter = adapter
            updateTotalItems(items)
        }




/*
        adapter = CartAdapter(cartList,this)
        recyclerView.adapter = adapter

        val name = arguments?.getString("name")
        val imageResId = arguments?.getInt("imageResId")
        val quantity = arguments?.getInt("quantity")
        val foodPrice =arguments?.getString("price")

        if (name != null && imageResId != null && quantity != null && foodPrice !=null) {
            val item = CartItem(name, imageResId, quantity,pricePerItem = foodPrice.toDouble())
            cartList.add(item)
            adapter.notifyDataSetChanged()
            updateTotalItems()
            Log.d("TT","Item added to cart: $item")

        }*/

 /*val cartItem1 =  CartItem(name ="item1",imageResId = R.drawable.cart,quantity =10, pricePerItem = 10.0)
        cartList.add(cartItem1)
        adapter.notifyDataSetChanged()*/


        return view
    }

  /*  override fun onItemSub(item: CartItem, position: Int) {
        val currentItem = cartList.get(position)
        val newQuantity = currentItem.quantity - 1
        if (newQuantity > 0) {
            currentItem.quantity = newQuantity
            adapter.notifyItemChanged(position)

        }else {
            cartList.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
        updateTotalItems()
    }

    override fun onItemAdd(item: CartItem, position: Int) {
        val currentItem = cartList.get(position)
        val newQuantity = currentItem.quantity + 1
        if (newQuantity > 0) {
            currentItem.quantity = newQuantity
            adapter.notifyItemChanged(position)
            updateTotalItems()
        }
    }

    override fun onItemDelete(item: CartItem, position: Int) {
       val currentItem = cartList.get(position)
        cartList.removeAt(position)
        adapter.notifyItemRemoved(position)
        updateTotalItems()
    }

    private fun updateTotalItems() {
        *//*for(len in 0..cartList.size){
            val totalItemPrice+= cartList[len].quantity * cartList[len].pricePerItem
        }*//*

        totalTextView.text = " ${cartList.size}"

        val totalPrice = totalItemPrice()
        totalPriceTextView.text = "₹%.2f".format(totalPrice)

        val discount = totalPrice * 0.10
        discountTextView.text = "₹%.2f".format(discount)

        val finalPrice = totalPrice - discount
        finalPriceTextView.text = "₹%.2f".format(finalPrice)

        if (cartList.isEmpty()) {
            checkOutTextview.visibility = View.GONE
            totalTextView.visibility = View.GONE
            totalPriceTextView.visibility = View.GONE
            discountTextView.visibility = View.GONE
            finalPriceTextView.visibility = View.GONE
            totalItemTextView.visibility = View.GONE
            priceTextView.visibility = View.GONE
            discountView.visibility = View.GONE
            viewCheck.visibility = View.GONE
            totalPriceTextFinal.visibility = View.GONE
            boxImage.visibility = View.VISIBLE
            oppssText.visibility = View.VISIBLE
            textViewItem.visibility = View.VISIBLE
            startBrowsingText.visibility = View.VISIBLE
        } else {
            checkOutTextview.visibility = View.VISIBLE
            totalTextView.visibility = View.VISIBLE
            totalPriceTextView.visibility = View.VISIBLE
            discountTextView.visibility = View.VISIBLE
            finalPriceTextView.visibility = View.VISIBLE
            totalItemTextView.visibility = View.VISIBLE
            priceTextView.visibility = View.VISIBLE
            discountView.visibility = View.VISIBLE
            viewCheck.visibility = View.VISIBLE
            totalPriceTextFinal.visibility = View.VISIBLE
            boxImage.visibility = View.GONE
            oppssText.visibility = View.GONE
            textViewItem.visibility = View.GONE
            startBrowsingText.visibility = View.GONE
        }


    }

    private fun totalItemPrice(): Double {
        var totalItemPrice = 0.0
        for (len in 0 until cartList.size) {
            totalItemPrice += cartList[len].quantity * cartList[len].pricePerItem
        }
        return totalItemPrice
    }*/

    override fun onItemAdd(item: CartItem, position: Int) {
        val newQuantity = item.quantity + 1
        if (newQuantity > 0) {
            val updatedItem = item.copy(quantity = newQuantity)
            viewModel.update(updatedItem)
        }
    }

    override fun onItemSub(item: CartItem, position: Int) {
        val newQuantity = item.quantity - 1
        if (newQuantity > 0) {
            val updatedItem = item.copy(quantity = newQuantity)
            viewModel.update(updatedItem)
        } else {
            viewModel.update(item)
        }
    }

    override fun onItemDelete(item: CartItem, position: Int) {

        viewModel.deleteByItemId(item.id)

    }
    private fun updateTotalItems(items: List<CartItem>) {
        totalTextView.text = "${items.size}"

        val totalPrice = items.sumOf { it.quantity * it.pricePerItem }
        totalPriceTextView.text = "₹%.2f".format(totalPrice)

        val discount = totalPrice * 0.10
        discountTextView.text = "₹%.2f".format(discount)

        val finalPrice = totalPrice - discount
        finalPriceTextView.text = "₹%.2f".format(finalPrice)

        if (items.isEmpty()) {
            checkOutTextview.visibility = View.GONE
            totalTextView.visibility = View.GONE
            totalPriceTextView.visibility = View.GONE
            discountTextView.visibility = View.GONE
            finalPriceTextView.visibility = View.GONE
            totalItemTextView.visibility = View.GONE
            priceTextView.visibility = View.GONE
            discountView.visibility = View.GONE
            viewCheck.visibility = View.GONE
            totalPriceTextFinal.visibility = View.GONE
            boxImage.visibility = View.VISIBLE
            oppssText.visibility = View.VISIBLE
            textViewItem.visibility = View.VISIBLE
            startBrowsingText.visibility = View.VISIBLE
        } else {
            checkOutTextview.visibility = View.VISIBLE
            totalTextView.visibility = View.VISIBLE
            totalPriceTextView.visibility = View.VISIBLE
            discountTextView.visibility = View.VISIBLE
            finalPriceTextView.visibility = View.VISIBLE
            totalItemTextView.visibility = View.VISIBLE
            priceTextView.visibility = View.VISIBLE
            discountView.visibility = View.VISIBLE
            viewCheck.visibility = View.VISIBLE
            totalPriceTextFinal.visibility = View.VISIBLE
            boxImage.visibility = View.GONE
            oppssText.visibility = View.GONE
            textViewItem.visibility = View.GONE
            startBrowsingText.visibility = View.GONE
        }
    }


}