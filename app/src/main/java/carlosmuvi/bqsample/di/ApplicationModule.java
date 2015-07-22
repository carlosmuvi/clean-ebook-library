package carlosmuvi.bqsample.di;

import android.app.Application;

import javax.inject.Singleton;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.domain.UIThread;
import carlosmuvi.bqsample.executor.InteractorExecutor;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import carlosmuvi.data.dropbox.DropboxDatasource;
import carlosmuvi.data.dropbox.mapper.DropboxBookMapper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos.
 */
@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    InteractorExecutor provideThreadExecutor(ThreadExecutor executor) {
        return executor;
    }

    @Provides
    @Singleton
    MainThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

}
