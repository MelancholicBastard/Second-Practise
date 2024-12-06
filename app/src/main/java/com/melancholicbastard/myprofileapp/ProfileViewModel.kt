package com.melancholicbastard.myprofileapp

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.melancholicbastard.myprofileapp.recycleview.Goal
import com.melancholicbastard.myprofileapp.recycleview.GoalsRepository


class ProfileViewModel : ViewModel() {

    val nameLD = MutableLiveData("Surname Name")
    val phoneLD = MutableLiveData("8 (800)-555-35-35")
    val mailLD = MutableLiveData("mail@yandex.ru")

    val imageUriLD = MutableLiveData<Uri>()

    private val profileStatusLD = MutableLiveData<List<String>>()
    val selectedProfileLD = MutableLiveData<String>()

    private val colorArrayLD = MutableLiveData<IntArray>()
    private val colorLD = MutableLiveData<Int>()

    val goalsRepositoryLD = MutableLiveData<ArrayList<Goal>>().apply {
        this.value = GoalsRepository().getGoals()
    }


    // получение позиции выбранного элемента в spinner
    fun onItemSelected(position: Int) {
        selectedProfileLD.value = profileStatusLD.value?.get(position)
    }
    // получение позиции текущего элемента в spinner
    private fun getItemPosition(): Int = profileStatusLD.value?.indexOf(selectedProfileLD.value) ?: 0


    fun initializeProfileStatuses(profileStates: List<String>) {
        profileStatusLD.value = profileStates
    }

    // изменение цвета в зависимости от текущего элемента
    fun getDependingColor(): Int {
        colorLD.value = colorArrayLD.value?.get(getItemPosition())
        return colorLD.value!!
    }

    fun initializeColors(color: IntArray) {
        colorArrayLD.value = color
    }

    // обновление uri взятого изображения
    fun updateImageUri(uri: Uri) {
        imageUriLD.value = uri
    }

    // создает интент на изображения
    fun chooseImage(): Intent {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        return intent
    }

    // создает итент на телефон
    fun clickPhone(): Intent {
        val uri = Uri.parse("tel: ${phoneLD.value}")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        return intent
    }

    // созает интент на почту
    fun clickMail(): Intent {
        val uri = Uri.parse("mailto:")
        val addresses = arrayOf<String>(mailLD.value!!)
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_TEXT, "Здравствуйте, ${nameLD.value}.")
        return intent
    }
}

