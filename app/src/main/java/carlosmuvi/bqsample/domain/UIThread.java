package carlosmuvi.bqsample.domain;

import carlosmuvi.bqsample.executor.MainThread;
import javax.inject.Inject;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by carlos.
 */
public class UIThread implements MainThread {

  @Inject UIThread() {
  }

  @Override public Scheduler getScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
