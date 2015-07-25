package carlosmuvi.bqsample.di;

import android.app.Application;
import carlosmuvi.bqsample.domain.UIThread;
import carlosmuvi.bqsample.executor.InteractorExecutor;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by carlos.
 */
@Module public class ApplicationModule {
  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton Application provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton InteractorExecutor provideThreadExecutor(ThreadExecutor executor) {
    return executor;
  }

  @Provides @Singleton MainThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }
}
