package com.tale.prettybundle;

import android.os.Bundle;

/**
 * Created by tale on 2/18/15.
 */
public interface Injector<T> {
    public void inject(T target, Bundle extras);
}
