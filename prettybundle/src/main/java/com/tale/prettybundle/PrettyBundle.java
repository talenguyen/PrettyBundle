package com.tale.prettybundle;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Bundle "injection" utilities. Use this class to simplify binding bundle values.
 * Injecting bundle from your activity is as easy as:
 * <pre><code>
 * public class ExampleActivity extends Activity {
 *   {@literal @}Extra String stringExtra1;
 *   {@literal @}Extra String stringExtra2;
 *   {@literal @}Override protected void onCreate(Bundle savedInstanceState) {
 *     super.onCreate(savedInstanceState);
 *     PrettyBundle.inject(this);
 *   }
 * }
 * </code></pre>
 */
public final class PrettyBundle {
    public static final String INJECTOR_SUFFIX = "$$BundleInjector";
    public static final String ANDROID_PREFIX = "android.";
    public static final String JAVA_PREFIX = "java.";

    private PrettyBundle() {
        throw new AssertionError("No instances.");
    }

    private static final String TAG = "PrettyBundle";
    private static boolean debug = false;

    static final Map<Class<?>, Injector<Object>> INJECTORS =
            new LinkedHashMap<Class<?>, Injector<Object>>();

    private static final Injector<Object> NOP_INJECTOR = new Injector<Object>() {
        @Override public void inject(Object target, Bundle extras) {

        }
    };

    /**
     * Control whether debug logging is enabled.
     */
    public static void setDebug(boolean debug) {
        PrettyBundle.debug = debug;
    }

    public static void inject(Activity activity) {
        if (activity == null) {
            return;
        }

        inject(activity, activity.getIntent().getExtras());
    }

    public static void inject(Fragment fragment) {
        if (fragment == null) {
            return;
        }

        inject(fragment, fragment.getArguments());
    }

    public static void inject(android.support.v4.app.Fragment fragment) {
        if (fragment == null) {
            return;
        }

        inject(fragment, fragment.getArguments());
    }

    public static void inject(Service service, Intent intent) {
        if (service == null) {
            return;
        }

        inject(service, intent == null ? null : intent.getExtras());
    }

    private static void inject(Object target, Bundle extras) {
        Class<?> targetClass = target.getClass();
        try {
            if (debug) Log.d(TAG, "Looking up view injector for " + targetClass.getName());
            Injector<Object> injector = findInjectorForClass(targetClass);
            if (injector != null) {
                injector.inject(target, extras);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unable to inject views for " + target, e);
        }
    }

    private static Injector<Object> findInjectorForClass(Class<?> cls)
            throws IllegalAccessException, InstantiationException {
        Injector<Object> injector = INJECTORS.get(cls);
        if (injector != null) {
            if (debug) Log.d(TAG, "HIT: Cached in injector map.");
            return injector;
        }
        String clsName = cls.getName();
        if (clsName.startsWith(ANDROID_PREFIX) || clsName.startsWith(JAVA_PREFIX)) {
            if (debug) Log.d(TAG, "MISS: Reached framework class. Abandoning search.");
            return NOP_INJECTOR;
        }

        try {
            Class<?> injectorClass = Class.forName(clsName + INJECTOR_SUFFIX);
            //noinspection unchecked
            injector = (Injector<Object>) injectorClass.newInstance();
            if (debug) Log.d(TAG, "HIT: Class loaded injection class.");
        } catch (ClassNotFoundException e) {
            if (debug) Log.d(TAG, "Not found. Trying superclass " + cls.getSuperclass().getName());
            injector = findInjectorForClass(cls.getSuperclass());
        }
        INJECTORS.put(cls, injector);
        return injector;
    }

}
