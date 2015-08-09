package carlosmuvi.data.dropbox.filter;

import rx.functions.Func1;

/**
 * Created by carlos.
 */
public class NotNullFilter<T> implements Func1<T, Boolean> {

  @Override public Boolean call(T value) {
    return value != null;
  }
}
