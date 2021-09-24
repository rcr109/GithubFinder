package rcr.projects.mygitreps.domain.di

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

import rcr.projects.mygitreps.domain.ListUserRepositoriesUseCase

object DomainModule {
    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module{
        return module {
            factory { ListUserRepositoriesUseCase(get()) }
        }
    }

}