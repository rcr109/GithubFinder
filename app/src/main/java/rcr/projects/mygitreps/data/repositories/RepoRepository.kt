package rcr.projects.mygitreps.data.repositories

import kotlinx.coroutines.flow.Flow
import rcr.projects.mygitreps.data.model.Repo


interface RepoRepository {
    suspend fun listRepositories(user: String): Flow<List<Repo>>
}