package com.udacity.shoestore

import android.text.TextUtils
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.InverseMethod
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    // LiveData of shoe list items
    private val _shoeList = MutableLiveData<ArrayList<Shoe>>(arrayListOf())
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    val nameValue = MutableLiveData<String>("")
    val companyValue = MutableLiveData<String>("")
    val sizeValue = MutableLiveData<String>("")
    val descriptionValue = MutableLiveData<String>("")

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
    private fun refreshDetailSave() {
        _detailSave.value = false
    }

    // Verify if all data correctly inserted, create shoe object and call function to add it
    fun canAddShoe(): Boolean {
        val nameValue = nameValue.value
        val companyValue = companyValue.value
        val sizeValue = sizeValue.value
        val descriptionValue = descriptionValue.value

        if (!TextUtils.isEmpty(nameValue)
            && !TextUtils.isEmpty(companyValue)
            && !TextUtils.isEmpty(sizeValue)
            && !TextUtils.isEmpty(descriptionValue)
        ) {
            val shoe =
                Shoe(
                    nameValue!!,
                    sizeValue!!.toDouble(),
                    companyValue!!,
                    descriptionValue!!,
                    arrayListOf<String>()
                )
            addShoe(shoe)
            return true
        }
        return false
    }

    // Add new shoe to LiveData to update list
    private fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
        refreshDetailSave()
    }


}