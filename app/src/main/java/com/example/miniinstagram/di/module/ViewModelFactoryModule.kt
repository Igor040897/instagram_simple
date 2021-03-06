package com.example.miniinstagram.di.module

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.miniinstagram.data.Repository
import dagger.Module
import dagger.Provides
import java.lang.reflect.InvocationTargetException

@Module
class ViewModelFactoryModule {
    @Provides
    fun bindViewModelFactory(application: Application, repository: Repository): ViewModelFactory {
        return ViewModelFactory.getInstance(application, repository)
    }
}


class ViewModelFactory private constructor(
    private val application: Application,
    private val repository: Repository
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                return modelClass.getConstructor(Application::class.java, Repository::class.java)
                    .newInstance(application, repository)
            } catch (e: NoSuchMethodException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }

        }
        return super.create(modelClass)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(application: Application, repository: Repository): ViewModelFactory {
            return instance
                ?: synchronized(ViewModelFactory::class.java) {
                instance
                    ?: ViewModelFactory(
                        application,
                        repository
                    )
                    .also { instance = it }
            }
        }
    }
}