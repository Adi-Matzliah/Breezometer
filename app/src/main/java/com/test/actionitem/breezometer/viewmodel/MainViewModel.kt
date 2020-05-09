package com.test.actionitem.breezometer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.actionitem.breezometer.data.RemoteRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val remoteRepo: RemoteRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _airQuality = MutableLiveData(0)
            val airQuality: LiveData<Int> get() = _airQuality

    private var _categoryQuality = MutableLiveData("")
            val categoryQuality: LiveData<String> get() = _categoryQuality

    private var _qualityColor = MutableLiveData("#80000000") // first set to transparent
            val qualityColor: LiveData<String> get() = _qualityColor

    private var _isBtnRefreshClicked = MutableLiveData(false)
            val isRefreshBtnIsClicked : LiveData<Boolean> get() = _isBtnRefreshClicked

    fun onBtnRefreshClicked() {
        _isBtnRefreshClicked.value = true
        fetchAirQuality()
        _isBtnRefreshClicked.value = false
    }

    private fun fetchAirQuality() {
        compositeDisposable += remoteRepo.getAQI()
            .flatMap { response ->
                response.data?.indexes?.baqi?.let {
                    it.aqi.let { aqi ->
                        _airQuality.postValue(aqi)
                    }
                    it.category?.let { category ->
                        _categoryQuality.postValue(category)
                    }
                    it.color?.let { color ->
                        _qualityColor.postValue(color)
                    }
                }
                Single.just(response)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe({ Timber.d("AQI is done.") },
                { e -> Timber.e(e) })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
