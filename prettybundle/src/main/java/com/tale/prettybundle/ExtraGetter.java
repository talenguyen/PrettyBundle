package com.tale.prettybundle;

import android.os.Bundle;

/**
 * Created by giang on 2/24/15.
 */
public enum ExtraGetter {

    STRING {
        @Override public String get(Bundle bundle, String key) {
            return bundle.getString(key);
        }
    },
    NOP {
        @Override public Object get(Bundle bundle, String key) {
            return null;
        }
    };

    public abstract Object get(Bundle bundle, String key);

}
