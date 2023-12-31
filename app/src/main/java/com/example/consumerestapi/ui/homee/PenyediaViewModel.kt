package com.example.consumerestapi.ui.homee

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.consumerestapi.KontakAplikation
import com.example.consumerestapi.ui.kontak.viewmodel.DetailViewModel
import com.example.consumerestapi.ui.kontak.viewmodel.EditViewModel
import com.example.consumerestapi.ui.kontak.viewmodel.HomeViewModel
import com.example.consumerestapi.ui.kontak.viewmodel.InsertViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiMars().container.KontakRepository)
        }
        initializer {
            InsertViewModel(aplikasiMars().container.KontakRepository)
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                kontakRepository = aplikasiMars().container.KontakRepository
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                kontakRepository = aplikasiMars().container.KontakRepository
            )
        }
    }
}


fun CreationExtras.aplikasiMars(): KontakAplikation =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KontakAplikation)