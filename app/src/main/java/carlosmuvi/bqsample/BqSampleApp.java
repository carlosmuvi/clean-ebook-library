package carlosmuvi.bqsample;


import android.app.Application;

import carlosmuvi.bqsample.di.ApplicationModule;
import carlosmuvi.bqsample.di.components.ApplicationComponent;
import carlosmuvi.bqsample.di.components.DaggerApplicationComponent;

public class BqSampleApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent component() {
        return applicationComponent;
    }
}

