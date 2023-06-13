package com.braula.finnapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braula.finnapp.domain.usecases.LoadAdsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdViewModel @Inject constructor(
    private var loadAdsUseCase: LoadAdsUseCase
): ViewModel() {

    private val adViewState = MutableStateFlow(AdViewState())
    fun adViewState(): StateFlow<AdViewState> = adViewState

    fun loadAds() {
        viewModelScope.launch {
            adViewState.update { current ->
                current.copy(
                    isLoading = true
                )
            }
            loadAdsUseCase.invoke().catch { throwable ->
                throwable.printStackTrace()
            }.collect { result ->
                adViewState.update { current ->
                    current.copy(
                        isLoading = false,
                        adList = result
                    )
                }
            }
        }
    }
}