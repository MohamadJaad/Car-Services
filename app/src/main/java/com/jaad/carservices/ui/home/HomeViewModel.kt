package com.jaad.carservices.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jaad.carservices.ui.base.BaseViewModel
import com.jaad.carservices.ui.base.MvpView

class HomeViewModel(application: Application) : BaseViewModel<MvpView>(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun requestTow(map: HashMap<String, String>) {
        val db = Firebase.firestore
        db.collection("orders")
            .add(map)
            .addOnSuccessListener {
            getNavigator()?.showMessage("sent successfully")
        }.addOnFailureListener{
            getNavigator()?.showMessage("Some error !!")
        }

    }

    fun requestSpare(map: HashMap<String, String>) {
        val db = Firebase.firestore
        db.collection("spare")
            .add(map)
            .addOnSuccessListener {
                getNavigator()?.showMessage("Spare part will be delivered to you ")
            }.addOnFailureListener{
                getNavigator()?.showMessage("Some error !!")
            }
    }

    fun getTowOrders(){
        val db = Firebase.firestore
        db.collection("orders")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }

            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    companion object{
        private const val TAG = "HomeViewModel"
    }
}