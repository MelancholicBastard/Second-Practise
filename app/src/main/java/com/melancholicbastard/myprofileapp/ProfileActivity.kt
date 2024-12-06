package com.melancholicbastard.myprofileapp


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.melancholicbastard.myprofileapp.databinding.ActivityProfileBinding
import com.melancholicbastard.myprofileapp.recycleview.RecyclerViewAdapter

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    lateinit var viewModelka: ProfileViewModel

    // на основе выбранного изображеия извлекается uri и обновляется во viewModel
    private var launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { intent ->
        val uri = intent.data?.data
        uri?.let { it ->
            viewModelka.updateImageUri(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        setContentView(binding.root)
        viewModelka = ViewModelProvider(this)[ProfileViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel2 = viewModelka

        // передача данных репозиториев во viewModel
        val colors = resources.getIntArray(R.array.color_array)
        viewModelka.initializeColors(colors)

        val profileStatus = resources.getStringArray(R.array.profile_statuses).toList()
        viewModelka.initializeProfileStatuses(profileStatus)

        // создание адаптера для spinner
        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            profileStatus
        )
        // передача адаптера в spinner
        binding.SP.adapter = spinnerAdapter
        // прописывание методов при взаимодействии со спинером
        binding.SP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // позиция выбранного элемента передается в viewModel
                viewModelka.onItemSelected(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // обновление recyclerView при изменении репозитория
        viewModelka.goalsRepositoryLD.observe(this) {goals ->
            binding.RV.adapter = RecyclerViewAdapter(goals)
        }


        // завершение активности без передачи данных
        binding.BT2.setOnClickListener {
            finish()
        }

        // при изменении текущего объекта spinner
        viewModelka.selectedProfileLD.observe(this) {item ->
            item?.let {
                // изменение цвета imageView
                val color = viewModelka.getDependingColor()
                binding.IV.setBackgroundColor(color) }
        }

        // после обновления uri изображения, по нему изменяется само изображение
        viewModelka.imageUriLD.observe(this) { uri ->
            uri?.let { binding.ShapedIV.setImageURI(it) }
        }
        // неявный переход для выбора изображения
        binding.ShapedIV.setOnClickListener{
            val intent = viewModelka.chooseImage()
            launcher.launch(intent)
        }

        // неявный переход в приложение телефон
        binding.TV4.setOnClickListener{
            val intent = viewModelka.clickPhone()
            startActivity(intent)
        }

        // неявный переход в приложение почта
        binding.TV3.setOnClickListener {
            val intent = viewModelka.clickMail()
            startActivity(intent)
        }


    }
}