package br.com.alura.helloapp.di.module



import android.content.Context
import androidx.datastore.core.DataStore
import br.com.alura.helloapp.preferences.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import androidx.datastore.preferences.core.Preferences

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {
    @Provides
    fun provideDatStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}