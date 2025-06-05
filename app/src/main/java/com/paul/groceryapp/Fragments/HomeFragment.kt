package com.paul.groceryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.Model.CartItem
import com.paul.groceryapp.Model.PopularPackModel
import com.paul.groceryapp.OurNewItemsAdapter
import com.paul.groceryapp.PopularPackAdapter
import com.paul.groceryapp.R
import com.paul.groceryapp.viewModel.CartViewModel


class HomeFragment : Fragment(), PopularPackAdapter.OnItemClickListener,OurNewItemsAdapter.OnItemClickListener {
    private lateinit var viewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]
    }

    private var OurNewItemsList: ArrayList<PopularPackModel> = arrayListOf(
        PopularPackModel(R.drawable.two_apple,"Apple ","200"),
        PopularPackModel(R.drawable.milk,"Milk","100"),
        PopularPackModel(R.drawable.basket2,"Onion ","600"),
        PopularPackModel(R.drawable.basket3,"Onion","500"),
        PopularPackModel(R.drawable.basket3,"Onion Salt ","250")
    )
    private lateinit var OurNewItemsAdapter:OurNewItemsAdapter
    private lateinit var ourNewItemsRecyclerView:RecyclerView


    private var popularPackAllList: ArrayList<PopularPackModel> = arrayListOf(
        PopularPackModel(R.drawable.milk,"Milk ","200"),
        PopularPackModel(R.drawable.basket3,"Capsicum Onion..","500"),
        PopularPackModel(R.drawable.basket1,"Red Capsicum","600"),
        PopularPackModel(R.drawable.basket2,"Onion ","400"),
        PopularPackModel(R.drawable.basket3,"Bundile Pack","270"),

    )
    private lateinit var popularPackAdapter: PopularPackAdapter
    private lateinit var popularPackRecyclerView:RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        popularPackRecyclerView = view.findViewById(R.id.popularPackRecyclerView)
        ourNewItemsRecyclerView = view.findViewById(R.id.ourNewItemsRecyclerView)

       val search = view.findViewById<ImageView>(R.id.searchImgView)
        search.setOnClickListener {
            val searchFragment = SearchFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, searchFragment)
                .addToBackStack(null)
                .commit()
        }


        val dashboardImageView = view.findViewById<View>(R.id.dashboardImageView)
        dashboardImageView.setOnClickListener {
            val homeFragment = DashBoardFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, homeFragment)
                .addToBackStack(null)
                .commit()

        }

        implementPopularPackAdapter()
        implementOurNewItemsAdapter()

        return view
    }

    private fun implementPopularPackAdapter() {
        popularPackRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            popularPackAdapter = PopularPackAdapter(requireContext(),this@HomeFragment)
            adapter = popularPackAdapter
            popularPackAdapter.setPopularList(popularPackAllList)

        }
    }

    override fun onItemClick(item: PopularPackModel) {
        val detailFragment = DetailFragment().apply {
            arguments = Bundle().apply {
                putString("name", item.name)
                putInt("imageResId", item.img)
                putString("price", item.price)

            }
        }

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView,detailFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onAddToCartClick(item: PopularPackModel) {
        val cartItem = CartItem(
            name = item.name,
            imageResId = item.img,
            quantity = 1,
            pricePerItem = item.price.toDoubleOrNull() ?: 0.0
        )
        viewModel.insert(cartItem)

        val bundle = Bundle()
        val cartFragment = CartFragment()
        cartFragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            //parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, cartFragment) // Replace with your container ID
            .addToBackStack(null)
            .commit()
    }

    private fun implementOurNewItemsAdapter() {
        ourNewItemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            OurNewItemsAdapter = OurNewItemsAdapter(requireContext(),this@HomeFragment)
            adapter = OurNewItemsAdapter
            OurNewItemsAdapter.setNewList(OurNewItemsList)

        }
    }




}