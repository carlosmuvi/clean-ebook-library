package carlosmuvi.bqsample.executor;

import rx.Scheduler;

/**
 * Created by carlos.
 */
public interface MainThread {
  Scheduler getScheduler();
}
