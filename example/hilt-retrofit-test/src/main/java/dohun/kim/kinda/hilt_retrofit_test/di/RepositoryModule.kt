package dohun.kim.kinda.hilt_retrofit_test.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dohun.kim.kinda.hilt_retrofit_test.data.DefaultGithubRepository
import dohun.kim.kinda.hilt_retrofit_test.data.GithubRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindGithubRepository(githubRepository: DefaultGithubRepository): GithubRepository
}