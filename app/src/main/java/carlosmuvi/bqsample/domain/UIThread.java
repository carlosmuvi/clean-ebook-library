package carlosmuvi.bqsample.domain;

import android.os.Handler;
import android.os.Looper;
import carlosmuvi.bqsample.executor.MainThread;
import javax.inject.Inject;

/**
 * Created by carlos.
 */
public class UIThread implements MainThread {

  private Handler handler;

  @Inject UIThread() {
    this.handler = new Handler(Looper.getMainLooper());
  }

  @Override public void post(Runnable runnable) {
    handler.post(runnable);
  }
}
