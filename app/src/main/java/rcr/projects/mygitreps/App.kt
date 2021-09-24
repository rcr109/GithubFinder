package rcr.projects.mygitreps

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import rcr.projects.mygitreps.data.di.DataModule
import rcr.projects.mygitreps.domain.di.DomainModule
import rcr.projects.mygitreps.presentation.di.PresentationModule


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()

    }
}