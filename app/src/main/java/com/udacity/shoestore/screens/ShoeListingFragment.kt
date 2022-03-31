package com.udacity.shoestore.screens

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingBinding


class ShoeListingFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentShoeListingBinding.inflate(inflater, container, false )

        // Go to Shoe Detail screen with navigation
        binding.addNewShoeBtn.setOnClickListener {
            findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment())
        }

        // Observe shoe list livedata and update list properly
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { list ->
            if(!list.isNullOrEmpty()){

                // Add new TextView item to the List/Linearlayout
                for(item in list){

                    val newTextView = TextView(requireContext())
                    newTextView.text = item.name
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        newTextView.setTextAppearance(R.style.NormalTextStyle)
                    }
                    else{
                        newTextView.setTextAppearance(requireContext(), R.style.NormalTextStyle)
                    }
                    binding.listLayout.addView(newTextView)

                }

                // Show add new shoe button at the bottom
                val newShoeBtn = binding.listLayout[0]
                binding.listLayout.removeViewAt(0)
                binding.listLayout.addView(newShoeBtn)

            }
        })

        return binding.root
    }


}