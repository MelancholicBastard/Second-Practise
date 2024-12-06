package com.melancholicbastard.myprofileapp

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    val buttonLD = MutableLiveData("Profile")

    var activity: MainActivity? = null
    fun putContext(activity: MainActivity) {
        this.activity = activity
    }

    fun transfer() {
        val intent = Intent(activity?.baseContext, ProfileActivity::class.java)
        activity?.startActivity(intent)
    }
}