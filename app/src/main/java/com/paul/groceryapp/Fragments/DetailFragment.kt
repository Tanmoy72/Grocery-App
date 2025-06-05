package com.paul.groceryapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.paul.groceryapp.Model.CartItem
import com.paul.groceryapp.Model.SaveItem
import com.paul.groceryapp.R
import com.paul.groceryapp.viewModel.CartViewModel
import com.paul.groceryapp.viewModel.SaveViewModel


class DetailFragment : Fragment() {

    private lateinit var viewModel: CartViewModel
    private lateinit var saveViewModel: SaveViewModel
    var num = 0
    var name: String? = null
    var imageResId: Int? = null
    var foodPrice: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         viewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]
        saveViewModel = ViewModelProvider(requireActivity())[SaveViewModel::class.java]
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val buyBtn = view.findViewById<TextView>(R.id.buyText)
        val saveImgBtn = view.findViewById<ImageView>(R.id.saveImg)

        name = arguments?.getString("name")
        foodPrice = arguments?.getString("price")
        imageResId = arguments?.getInt("imageResId")


        val add = view.findViewById<ImageView>(R.id.addImageView)
        val remove = view.findViewById<ImageView>(R.id.minusImageView)
        val quantity = view.findViewById<TextView>(R.id.itemCountTextView)
        val backBtn = view.findViewById<ImageView>(R.id.backDetailPage)
        backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        add.setOnClickListener {
            num++
            quantity.text = num.toString()
        }

        remove.setOnClickListener {
            /*num--
            quantity.text = num.toString()*/
            if (num > 0) {
                num--
                quantity.text = num.toString()
            }
        }

        buyBtn.setOnClickListener {
            val bundle = Bundle()
            /*bundle.putString("name", name)
            bundle.putInt("imageResId", imageResId ?: 0)
            bundle.putInt("quantity", num)
            bundle.putString("price",foodPrice)*/


            val cartItem = CartItem(
                name = name ?: "",
                imageResId = imageResId ?: 0,
                quantity = num,
                pricePerItem = foodPrice?.toDouble() ?: 0.0
            )
            Log.d("CartInsert", "Inserting item: $cartItem")
            viewModel.insert(cartItem)

            val cartFragment = CartFragment()
            cartFragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
            //parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, cartFragment) // Replace with your container ID
                .addToBackStack(null)
                .commit()

        }

        saveImgBtn.setOnClickListener {
            val saveItem = SaveItem(
                saveName = name ?: "",
                saveImg = imageResId ?: 0,
                savePrice = foodPrice?.toDouble() ?: 0.0
            )
            Log.d("SaveInsert", "Inserting item: $saveItem")
            saveViewModel.insert(saveItem)
            var isSaved = false

             isSaved = !isSaved

            // Change the image based on state
            if (isSaved) {
                saveImgBtn.setImageResource(R.drawable.heart_active)
            } else {
                saveImgBtn.setImageResource(R.drawable.heart)
            }


        }

       


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: ImageView = view.findViewById(R.id.foodImg)
        val textView: TextView = view.findViewById(R.id.foodName)
        val food: TextView = view.findViewById(R.id.foodPrice)

        // Get data from Bundle
        val name = arguments?.getString("name")
        val imageResId = arguments?.getInt("imageResId")
        val price = arguments?.getString("price")

        textView.text = name
        food.text = price
        imageResId?.let { imageView.setImageResource(it) }
    }


}