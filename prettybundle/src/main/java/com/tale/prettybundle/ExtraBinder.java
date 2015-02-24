package com.tale.prettybundle;

import android.os.Bundle;
import android.os.Parcelable;

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
    INTEGER {
        @Override public Integer get(Bundle bundle, String key) {
            return bundle.getInt(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putInt(key, (Integer) value);
        }
    },
    LONG {
        @Override public Long get(Bundle bundle, String key) {
            return bundle.getLong(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putLong(key, (Long) value);
        }
    },
    FLOAT {
        @Override public Float get(Bundle bundle, String key) {
            return bundle.getFloat(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putFloat(key, (Float) value);
        }
    },
    DOUBLE {
        @Override public Double get(Bundle bundle, String key) {
            return bundle.getDouble(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putDouble(key, (Double) value);
        }
    },
    PARCELABLE {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getParcelable(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putParcelable(key, (Parcelable) value);
        }
    },
    BOOLEAN {
        @Override public Boolean get(Bundle bundle, String key) {
            return bundle.getBoolean(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putBoolean(key, (Boolean) value);
        }
    },
    BYTE {
        @Override public Byte get(Bundle bundle, String key) {
            return bundle.getByte(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putByte(key, (Byte) value);
        }
    },
    CHAR {
        @Override public Character get(Bundle bundle, String key) {
            return bundle.getChar(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putChar(key, (Character) value);
        }
    },
    SHORT {
        @Override public Short get(Bundle bundle, String key) {
            return bundle.getShort(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putShort(key, (Short) value);
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
