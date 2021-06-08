package br.com.aaf.libraryCore.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    fun addDisposable(d: Disposable) {
        disposables.add(d)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}
