package com.tale.prettybundle;

import android.os.Bundle;

/**
 * Created by giang on 2/24/15.
 */
public enum ExtraBinder {

    STRING {
        @Override public String get(Bundle bundle, String key) {
            return bundle.getString(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            if (value instanceof String) {
                bundle.putString(key, (String) value);
            }
        }
    },
    NOP {
        @Override public <T> T get(Bundle bundle, String key) {
            return null;
        }

        @Override public void set(Bundle bundle, String key, Object value) {
        }
    };

    public abstract <T> T get(Bundle bundle, String key);

    public abstract void set(Bundle bundle, String key, Object value);

}
