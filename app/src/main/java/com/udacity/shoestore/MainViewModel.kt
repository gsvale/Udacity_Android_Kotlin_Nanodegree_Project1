package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    // LiveData of shoe list items
    private val _shoeList = MutableLiveData<ArrayList<Shoe>>(arrayListOf())
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    // LiveData of cancel action boolean
    private val _detailCancel = MutableLiveData<Boolean>()
    val detailCancel: LiveData<Boolean>
        get() = _detailCancel

    // LiveData of save action boolean
    private val _detailSave = MutableLiveData<Boolean>()
    val detailSave: LiveData<Boolean>
        get() = _detailSave

    // When click on cancel button
    fun onCancel() {
        _detailCancel.value = true
    }

    // When click on save button
    fun onSave() {
        _detailSave.value = true
    }

    // Refresh detail cancel value after click
    fun refreshDetailCancel() {
        _detailCancel.value = false
    }

    // Refresh detail save value after click
    fun refreshDetailSave() {
        _detailSave.value = false
    }

    // Add new shoe to LiveData to update list
    fun addShoe(shoe : Shoe){
        _shoeList.value?.add(shoe)
    }



}