package carlosmuvi.bqsample.di.components;

import android.app.Activity;

import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.navigation.Navigator;
import dagger.Component;

/**
 * Created by carlos.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface GenericActivityComponent {

    Activity activityContext();

    //TODO uncomment when implemented
    //Navigator navigator();

}
