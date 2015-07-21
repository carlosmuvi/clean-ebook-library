package carlosmuvi.bqsample.di.components;

import android.app.Application;

import javax.inject.Singleton;

import carlosmuvi.bqsample.BqSampleApp;
import carlosmuvi.bqsample.di.ApplicationModule;
import dagger.Component;

/**
 * Created by carlos.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BqSampleApp app);

    Application application();

    //TODO uncomment when implemented
    //InteractorExecutor threadExecutor();

    //TODO uncomment when implemented
    //MainThread mainThread();
}