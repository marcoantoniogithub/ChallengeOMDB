package com.example.desafiomobile.ui.searchedMoviesList.presentation.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.desafiomobile.BR

data class MoviePresentationModel(
    private var _text: String
) : BaseObservable() {

    var text: String
        @Bindable get() = _text
        set(value) {
            _text = value
            notifyPropertyChanged(BR.text)
        }
}