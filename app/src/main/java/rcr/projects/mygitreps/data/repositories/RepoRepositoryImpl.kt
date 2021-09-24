package rcr.projects.mygitreps.data.repositories


import kotlinx.coroutines.flow.flow
import rcr.projects.mygitreps.core.RemoteException
import rcr.projects.mygitreps.data.services.GitHubService
import retrofit2.HttpException

class RepoRepositoryImpl(private val service: GitHubService) : RepoRepository {
    override suspend fun listRepositories(user: String) = flow {
        try {
            val repoList = service.listRepositories(user)
            emit(repoList)
        }catch (e: HttpException){
            throw RemoteException(e.message ?: "Não foi possível realizar a busca.")
        }

    }
}