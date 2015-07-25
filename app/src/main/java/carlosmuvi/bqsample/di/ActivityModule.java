package carlosmuvi.bqsample.di;

import android.app.Activity;
import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.navigation.BqSampleNavigator;
import carlosmuvi.bqsample.navigation.Navigator;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos.
 */

@Module public class ActivityModule {
  private final Activity activityContext;

  public ActivityModule(Activity activityContext) {
    this.activityContext = activityContext;
  }

  @Provides @ActivityScope Activity getActivityContext() {
    return this.activityContext;
  }

  @Provides @ActivityScope Navigator provideNavigator(BqSampleNavigator navigator) {
    return navigator;
  }
}
