package com.tale.prettybundle;

import android.os.Bundle;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by giang on 2/24/15.
 */
public enum ExtraBinder {

    STRING {
        @Override public String get(Bundle bundle, String key) {
            return bundle.getString(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putString(key, (String) value);
        }
    },
    CHAR_SEQUENCE {
        @Override public CharSequence get(Bundle bundle, String key) {
            return bundle.getCharSequence(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putCharSequence(key, (CharSequence) value);
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
    PARCELABLE {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getParcelable(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putParcelable(key, (Parcelable) value);
        }
    },
    STRING_ARRAY {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getStringArray(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putStringArray(key, (String[]) value);
        }
    },
    CHAR_SEQUENCE_ARRAY {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getCharSequenceArray(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putCharSequenceArray(key, (CharSequence[]) value);
        }
    },
    INTEGER_ARRAY {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getIntArray(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putIntArray(key, (int[]) value);
        }
    },
    LONG_ARRAY {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getLongArray(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putLongArray(key, (long[]) value);
        }
    },
    FLOAT_ARRAY {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getFloatArray(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putFloatArray(key, (float[]) value);
        }
    },
    DOUBLE_ARRAY {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getDoubleArray(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putDoubleArray(key, (double[]) value);
        }
    },
    BOOLEAN_ARRAY {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getBooleanArray(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putBooleanArray(key, (boolean[]) value);
        }
    },
    BYTE_ARRAY {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getByteArray(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putByteArray(key, (byte[]) value);
        }
    },
    CHAR_ARRAY {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getCharArray(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putCharArray(key, (char[]) value);
        }
    },
    SHORT_ARRAY {
        @Override public <T> T get(Bundle bundle, String key) {
            return (T) bundle.getShortArray(key);
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putShortArray(key, (short[]) value);
        }
    },
    PARCELABLE_ARRAY {
        @Override public <T> T get(Bundle bundle, String key) {
            final Class<?> dataTypeClass = getDataTypeClass();
            final Parcelable[] parcelables = bundle.getParcelableArray(key);

            final String dataTypeClassName = dataTypeClass.getCanonicalName();

            if (dataTypeClassName.equals("android.os.Parcelable[]")) {
                // Check if class of data type is Parcelable we can cast the array to return
                return (T) parcelables;
            }
            if (parcelables != null && parcelables.length > 0) {
                // We must convert data type from Parcelable array to T array base on dataTypeClass which was registered in code generated.
                return (T) Arrays.asList(parcelables).toArray((Object[]) Array.newInstance(dataTypeClass, parcelables.length));
            }
            return null;
        }

        @Override public void set(Bundle bundle, String key, Object value) {
            bundle.putParcelableArray(key, (Parcelable[]) value);
        }
    },
    NOP {
        @Override public <T> T get(Bundle bundle, String key) {
            return null;
        }

        @Override public void set(Bundle bundle, String key, Object value) {
        }
    };

    protected Class<?> dataTypeClass;

    public void setDataTypeClass(Class<?> clazz) {
        this.dataTypeClass = clazz;
    }

    public Class<?> getDataTypeClass() {
        return dataTypeClass;
    }

    public abstract <T> T get(Bundle bundle, String key);

    public abstract void set(Bundle bundle, String key, Object value);

}
