package rcr.projects.mygitreps.domain

import kotlinx.coroutines.flow.Flow
import rcr.projects.mygitreps.core.UseCase
import rcr.projects.mygitreps.data.model.Repo

import rcr.projects.mygitreps.data.repositories.RepoRepository
import rcr.projects.mygitreps.data.services.GitHubService

class ListUserRepositoriesUseCase(
    private val repository: RepoRepository
    ) : UseCase<String, List<Repo>>() {
    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }

}