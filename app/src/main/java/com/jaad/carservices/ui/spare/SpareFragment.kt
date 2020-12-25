package com.jaad.carservices.ui.spare

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaad.carservices.databinding.FragmentSpareBinding
import com.jaad.carservices.ui.MapActivity
import com.jaad.carservices.ui.base.BaseFragment
import com.jaad.carservices.ui.home.HomeViewModel

class SpareFragment : BaseFragment<HomeViewModel>() {

    private lateinit var binding: FragmentSpareBinding
    override fun getViewModelClass() = HomeViewModel::class.java
    private val REQUEST_CODE_LOCATION = 101
    private var map = HashMap<String, String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpareBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun setUp(view: View?) {

        viewModel.setNavigator(this)

        binding.ccp.registerCarrierNumberEditText(binding.etPhone)

        binding.tvLocation.setOnClickListener {
            val intent = Intent(context, MapActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_LOCATION)
        }

        binding.btnConfirm.setOnClickListener {

            if (TextUtils.isEmpty(binding.etName.text) || TextUtils.isEmpty(binding.etPhone.text)
                || TextUtils.isEmpty(binding.etCarDetails.text)
                || TextUtils.isEmpty(binding.etSpare.text) || TextUtils.isEmpty(binding.tvAddress.text)

            ) {
                showMessage("Complete missing fields")
            } else {

                map["name"] = binding.etName.text.toString()
                map["phone"] = binding.etPhone.text.toString()
                map["details"] = binding.etCarDetails.text.toString()
                map["spare"] = binding.etSpare.text.toString()

                viewModel.requestSpare(map)

            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (data != null) {

            if (requestCode == REQUEST_CODE_LOCATION) {

                showMessage("Address saved")

                map["latitude"] = data.getStringExtra("lat").toString()
                map["longitude"] = data.getStringExtra("lng").toString()
                map["address"] = data.getStringExtra("address").toString()

                binding.tvAddress.text = data.getStringExtra("address").toString()

            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}