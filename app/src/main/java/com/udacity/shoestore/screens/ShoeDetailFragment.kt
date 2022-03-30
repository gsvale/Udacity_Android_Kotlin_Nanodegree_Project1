package com.udacity.shoestore.screens

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        // Set ViewModel to binding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Observe cancel variable, and update it then navigate to shoe list screen
        viewModel.detailCancel.observe(viewLifecycleOwner, Observer { isCancel ->
            if (isCancel) {
                viewModel.refreshDetailCancel()
                findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())
            }
        })

        // Observe save variable, update it, create new shoe and update
        // shoe list livedata and then navigate to shoe list screen
        viewModel.detailSave.observe(viewLifecycleOwner, Observer { isSave ->
            if (isSave) {
                val name = binding.nameEt.text.toString()
                val company = binding.companyEt.text.toString()
                val size = binding.sizeEt.text.toString()
                val description = binding.descriptionEt.text.toString()
                if (!TextUtils.isEmpty(name)
                    && !TextUtils.isEmpty(company)
                    && !TextUtils.isEmpty(size)
                    && !TextUtils.isEmpty(description)
                ) {
                    val newShoe =
                        Shoe(name, size.toDouble(), company, description, arrayListOf<String>())
                    viewModel.addShoe(newShoe)
                    viewModel.refreshDetailSave()
                    findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())
                }
            }
        })


        return binding.root
    }


}