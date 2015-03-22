package com.tale.prettybundleprocessor;

import com.tale.prettybundle.ExtraBinder;

/**
 * Created by giang on 2/24/15.
 */
public class ExtraBinderProvider {
    public static ExtraBinder get(String dataType) {
        if ("java.lang.String".equals(dataType)) {
            return ExtraBinder.STRING;
        } else if ("java.lang.CharSequence".equals(dataType)) {
            return ExtraBinder.CHAR_SEQUENCE;
        } else if ("int".equals(dataType) || "java.lang.Integer".equals(dataType)) {
            return ExtraBinder.INTEGER;
        } else if ("long".equals(dataType) || "java.lang.Long".equals(dataType)) {
            return ExtraBinder.LONG;
        } else if ("float".equals(dataType) || "java.lang.Float".equals(dataType)) {
            return ExtraBinder.FLOAT;
        } else if ("double".equals(dataType) || "java.lang.Double".equals(dataType)) {
            return ExtraBinder.DOUBLE;
        } else if ("boolean".equals(dataType) || "java.lang.Boolean".equals(dataType)) {
            return ExtraBinder.BOOLEAN;
        } else if ("byte".equals(dataType) || "java.lang.Byte".equals(dataType)) {
            return ExtraBinder.BYTE;
        } else if ("char".equals(dataType) || "java.lang.Character".equals(dataType)) {
            return ExtraBinder.CHAR;
        } else if ("short".equals(dataType) || "java.lang.Short".equals(dataType)) {
            return ExtraBinder.SHORT;
        } else if ("android.os.Parcelable".equals(dataType)) {
            return ExtraBinder.PARCELABLE;
        } else if ("int[]".equals(dataType) || "java.lang.Integer[]".equals(dataType)) {
            return ExtraBinder.INTEGER_ARRAY;
        } else if ("long[]".equals(dataType) || "java.lang.Long[]".equals(dataType)) {
            return ExtraBinder.LONG_ARRAY;
        } else if ("float[]".equals(dataType) || "java.lang.Float[]".equals(dataType)) {
            return ExtraBinder.FLOAT_ARRAY;
        } else if ("double[]".equals(dataType) || "java.lang.Double[]".equals(dataType)) {
            return ExtraBinder.DOUBLE_ARRAY;
        } else if ("boolean[]".equals(dataType) || "java.lang.Boolean[]".equals(dataType)) {
            return ExtraBinder.BOOLEAN_ARRAY;
        } else if ("byte[]".equals(dataType) || "java.lang.Byte[]".equals(dataType)) {
            return ExtraBinder.BYTE_ARRAY;
        } else if ("char[]".equals(dataType) || "java.lang.Character[]".equals(dataType)) {
            return ExtraBinder.CHAR_ARRAY;
        } else if ("short[]".equals(dataType) || "java.lang.Short[]".equals(dataType)) {
            return ExtraBinder.SHORT_ARRAY;
        } else if ("java.lang.String[]".equals(dataType)) {
            return ExtraBinder.STRING_ARRAY;
        } else if ("java.lang.CharSequence[]".equals(dataType)) {
            return ExtraBinder.CHAR_SEQUENCE_ARRAY;
        } else if ("android.os.Parcelable[]".equals(dataType)) {
            return ExtraBinder.PARCELABLE_ARRAY;
        }
        return ExtraBinder.NOP;
    }

}
