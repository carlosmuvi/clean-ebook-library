package carlosmuvi.bqsample.di.components;

import android.app.Application;
import carlosmuvi.bqsample.BqSampleApp;
import carlosmuvi.bqsample.di.ApplicationModule;
import carlosmuvi.bqsample.executor.InteractorExecutor;
import carlosmuvi.bqsample.executor.MainThread;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by carlos.
 */
@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  void inject(BqSampleApp app);

  Application application();

  InteractorExecutor threadExecutor();

  MainThread mainThread();
}