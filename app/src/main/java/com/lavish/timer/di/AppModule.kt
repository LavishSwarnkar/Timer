package com.lavish.timer.di

import com.lavish.timer.feature.timer.TimerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { TimerViewModel() }
}