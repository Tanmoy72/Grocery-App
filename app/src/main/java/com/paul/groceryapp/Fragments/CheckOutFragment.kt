package com.paul.groceryapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.Adapters.AddressAdapter
import com.paul.groceryapp.Adapters.PaymentAdapter
import com.paul.groceryapp.CategoryAdapter
import com.paul.groceryapp.Model.AddressModel
import com.paul.groceryapp.Model.PaymentModel
import com.paul.groceryapp.R


class CheckOutFragment : Fragment() {
    private var addressItemsList: ArrayList<AddressModel> = arrayListOf(
        AddressModel("Kolkata"),
        AddressModel("Howrah")
    )
    private lateinit var addressAdapter: AddressAdapter
    private lateinit var addressRecyclerView:RecyclerView

    private var paymentItemsList: ArrayList<PaymentModel> = arrayListOf(
        PaymentModel(R.drawable.master_card,"Master Card"),
        PaymentModel(R.drawable.paypal,"PayPal"),
        PaymentModel(R.drawable.cash_on_delivery,"Cash On Delivery"),
    )
    private lateinit var paymentAdapter: PaymentAdapter
    private lateinit var paymentRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_check_out, container, false)
        addressRecyclerView =view.findViewById(R.id.addressRecyclerView)

        paymentRecyclerView =view.findViewById(R.id.paymentRecyclerView)

        val backBtn = view.findViewById<ImageView>(R.id.backCheckOutBtn)
        backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        val payNowBtn = view.findViewById<TextView>(R.id.payNowTxt)
        payNowBtn.setOnClickListener {
             val successFragment = OrderSuccessFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, successFragment)
                .addToBackStack(null)
                .commit()
        }

        implementCategoryAdapter()
        implementPaymentAdapter()
        return view
    }

    private fun implementCategoryAdapter() {
        addressRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            addressAdapter =AddressAdapter(requireContext())
            adapter = addressAdapter
            addressAdapter.setAddressList(addressItemsList)
        }
    }

    private fun implementPaymentAdapter() {
        paymentRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            paymentAdapter = PaymentAdapter(requireContext())
            adapter = paymentAdapter
            paymentAdapter.setPaymentList(paymentItemsList)
        }
    }


}