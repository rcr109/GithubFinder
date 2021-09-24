package rcr.projects.mygitreps.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import rcr.projects.mygitreps.presentation.MainViewModel

object PresentationModule {
        fun load() {
            loadKoinModules(viewModelModule())
        }

        private fun viewModelModule(): Module {
            return module {
                viewModel { MainViewModel(get()) }
            }
        }
}