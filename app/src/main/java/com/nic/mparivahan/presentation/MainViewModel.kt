package com.nic.mparivahan.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nic.mparivahan.core.common.State
import com.nic.mparivahan.core.common.log
import com.nic.mparivahan.core.util.Resource
import com.nic.mparivahan.data.datasource.remote.dto.SecurityDto
import com.nic.mparivahan.data.datasource.remote.dto.SendOtpResult
import com.nic.mparivahan.data.mapper.toModel
import com.nic.mparivahan.data.network.TokenType
import com.nic.mparivahan.domain.model.NapixTokenModel
import com.nic.mparivahan.domain.model.SecurityModel
import com.nic.mparivahan.domain.usecase.ApiPortalUseCase
import com.nic.mparivahan.domain.usecase.CreateAccountUseCase
import com.nic.mparivahan.domain.usecase.GetFireBaseTokenUseCase
import com.nic.mparivahan.domain.usecase.GetStoredRequestResponseTokenUseCase
import com.nic.mparivahan.domain.usecase.GetStoredTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.RequestBody


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFireBaseTokenUseCase: GetFireBaseTokenUseCase ,
    private val getStoredTokenUseCase: GetStoredTokenUseCase ,
    private val createAccountUseCase: CreateAccountUseCase ,
    private val apiPortalUseCase: ApiPortalUseCase ,
    private val getStoredRequestResponseTokenUseCase: GetStoredRequestResponseTokenUseCase ,
) : ViewModel() {
    private val TAG = MainViewModel::class.simpleName.toString()
    // region --- Firebase token
    private val _tokenState = MutableStateFlow<Resource<NapixTokenModel>>(Resource.Loading)
    val tokenState: StateFlow<Resource<NapixTokenModel>> = _tokenState
    private val _storedToken = MutableStateFlow<NapixTokenModel?>(null)
    val storedToken: StateFlow<NapixTokenModel?> = _storedToken
    // endregion

    // region --- CreateAccount
    private val _createAccountState = MutableStateFlow<Resource<Any>>(Resource.Loading)
    val createAccountState: StateFlow<Resource<Any>> = _createAccountState
    private val _badRequest400Token = MutableStateFlow<SecurityModel?>(null)
    val badRequest400Token: StateFlow<SecurityModel?> = _badRequest400Token
    private val _apiPortalState = MutableStateFlow<String?>(null)
    val apiPortalState: StateFlow<String?> = _apiPortalState

    // endregion

    init {
        getToken()
    }

    private fun getToken(tokenType: TokenType = TokenType.DEFAULT) {
        viewModelScope.launch {
            getFireBaseTokenUseCase(tokenType)
                .onStart { _tokenState.value = Resource.Loading }
                .catch { e -> _tokenState.value = Resource.Error(e.message ?: "Unknown error", e) }
                .onCompletion { cause ->  observeToken(tokenType); }
                .collect { result -> _tokenState.value = result }
        }
    }

    private fun observeToken(tokenType: TokenType) {
        viewModelScope.launch {
            getStoredTokenUseCase(tokenType).collect { token ->
                _storedToken.value = token
            }
        }
    }

    val url = "https://delhigw.napix.gov.in/nic/parivahan/mparivahan/alertsapi/service/forwardOTPAlerts"
    val jsonString = """{ "smsAlert": { "smsEvent": "CTZ_SIG","smsMobile": "9081333419" } }"""

    fun <T> observeApiPortal() {
        val timeStamp = System.currentTimeMillis().toString()
        viewModelScope.launch {
            apiPortalUseCase<T>(
                url = url,
                jsonRequest = jsonString,
                timeStamp = timeStamp
            ).collect { result ->

                when (result) {
                    is Resource.Success<T> -> {

                        when (val data = result.data) {

                            is SendOtpResult -> {
                                // handle SendOtpResult
                                log(TAG, State.SUCCESS.value) { "observeApiPortal:Success ${result.data?.javaClass} ${result.data}" }

                                val json = result.data as String

                                val model = Gson().fromJson(json, SendOtpResult::class.java)

                                _apiPortalState.value = model.statusDesc
                            }

                            //is LoginResult -> { // handle LoginResult }

                            is List<*> -> {
                                data.forEach {
                                    // handle list
                                }
                            }

                        }
                    }
                    is Resource.Error -> log(TAG, State.ERROR.value) { "observeApiPortal:Error ${result.message}" }
                    is Resource.Loading -> {log(TAG, State.PROCESS.value) { "observeApiPortal: Loading" }}
                }

            }
        }
    }














    // region -- Create Account
    fun createAccount(url: String, request: RequestBody, timestamp: String) {
        Log.e(TAG, "createAccount:", )
        viewModelScope.launch {
            createAccountUseCase(url = url, request = request, timestamp = timestamp)
                .onStart {
                    _createAccountState.value = Resource.Loading
//                    Log.e(TAG, "createAccount: Loading", )
                }
                .catch { e ->
                    _createAccountState.value = Resource.Error(e.message ?: "Unknown error", e)
//                    Log.e(TAG, "createAccount: catch",e )
                }
                .onCompletion { cause ->
//                    observeBadRequest400Token()
//                    Log.e(TAG, "createAccount: onCompletion" )
                }
                .collect { resource -> 
                    _createAccountState.value = resource
                    Log.e(TAG, "createAccount: $resource", )
                }
        }
    }

    private fun observeBadRequest400Token() {
        viewModelScope.launch {
            getStoredRequestResponseTokenUseCase().collect { token ->
                _badRequest400Token.value = token
                log(TAG, State.PROCESS.value) { "observeBadRequest400Token: $token" }
            }
        }
    }


    // endregion
}