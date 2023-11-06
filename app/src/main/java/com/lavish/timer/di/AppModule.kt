package com.lavish.timer.di

import com.lavish.timer.feature.timer.TimerViewModel
import com.lavish.timer.helper.NotificationHelper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { TimerViewModel(get()) }
    single { NotificationHelper(androidContext()) }
}