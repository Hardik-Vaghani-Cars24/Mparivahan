package com.nic.mparivahan.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.nic.mparivahan.R
import com.nic.mparivahan.core.util.Resource
import com.nic.mparivahan.data.datasource.remote.dto.SendOtpResult
import com.nic.mparivahan.domain.model.NapixTokenModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        observeToken()
        callApi()

    }

    private fun callApi() {
        lifecycleScope.launch {
            viewModel.observeApiPortal<SendOtpResult>()
        }
    }


    private fun observeToken() {

        lifecycleScope.launch {
//            delay(1000)
//            viewModel.storedToken.collect { result: NapixTokenModel? ->
//                val token = result?.access_token
//                findViewById<TextView>(R.id.textView).text = token
//            }
            viewModel.apiPortalState.collect { result ->
                findViewById<TextView>(R.id.textView).text = result.toString()
            }
        }
    }

}