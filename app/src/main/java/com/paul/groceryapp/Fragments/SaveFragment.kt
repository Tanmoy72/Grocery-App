package com.paul.groceryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.Adapters.SaveAdapter
import com.paul.groceryapp.Model.SaveItem
import com.paul.groceryapp.R
import com.paul.groceryapp.viewModel.SaveViewModel

class SaveFragment : Fragment(), SaveAdapter.OnSaveItemClickListener {
    private lateinit var saveViewModel: SaveViewModel

    private lateinit var text :TextView
    private lateinit var text1 :TextView
    private lateinit var startAddingTxt :TextView
    private lateinit var favListImage : ImageView

   /* private var saveItemsList: ArrayList<SaveModel> = arrayListOf(
        SaveModel("Bundile Pack", R.drawable.icons_basket, "500.00"),

    )*/

    private lateinit var saveAdapter: SaveAdapter
    private lateinit var saveRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_save, container, false)
        saveRecyclerView = view.findViewById(R.id.saveRecyclerView)
        saveRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        text = view.findViewById(R.id.emptyText)
        text1 = view.findViewById(R.id.emptyText1)
        startAddingTxt = view.findViewById(R.id.starAdding)
        favListImage = view.findViewById(R.id.favListImageView)

        saveViewModel = ViewModelProvider(requireActivity())[SaveViewModel::class.java]

        saveAdapter = SaveAdapter(mutableListOf(),this)
        saveRecyclerView.adapter = saveAdapter

        saveViewModel.allSaveItems.observe(viewLifecycleOwner) { items ->
            saveAdapter = SaveAdapter(items,this)
            saveRecyclerView.adapter = saveAdapter
            saveAdapter.notifyDataSetChanged()
            updateUI(items)
        }
        startAddingTxt.setOnClickListener {
            val homeFragment = MainFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, homeFragment)
                .addToBackStack(null)
                .commit()
        }


        return view
    }

    override fun onSaveItemDelete(item: SaveItem, position: Int) {
        saveViewModel.deleteByItemId(item.id)
    }

    fun updateUI(saveItemsList: List<SaveItem>) {
        if (saveItemsList.isEmpty()) {
            text.visibility = View.VISIBLE
            text1.visibility = View.VISIBLE
            startAddingTxt.visibility = View.VISIBLE
            favListImage.visibility = View.VISIBLE

        } else {
            text.visibility = View.GONE
            text1.visibility = View.GONE
            startAddingTxt.visibility = View.GONE
            favListImage.visibility = View.GONE
        }
    }


}