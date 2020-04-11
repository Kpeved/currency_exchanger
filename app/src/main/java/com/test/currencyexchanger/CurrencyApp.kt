package com.test.currencyexchanger

import android.app.Application
import com.test.core_network.di.CoreNetworkModule
import com.test.feature_exchange.di.ExchangeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class CurrencyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger()
            androidContext(this@CurrencyApp)
            modules(
                listOf(
                    AppModule.createAppModule(),
                    CoreNetworkModule.createCoreNetworkModule(),
                    *ExchangeModule.createExchangeModules().toTypedArray()
                )
            )
        }
    }
}