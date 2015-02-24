package com.tale.prettybundle;

/**
 * Created by giang on 2/24/15.
 */
public class ExtraBinderProvider {
    public static ExtraBinder get(String dataType) {
        if ("java.lang.String".equals(dataType)) {
            return ExtraBinder.STRING;
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
        } else if ("android.os.Parcelable".equals(dataType)) {
            return ExtraBinder.PARCELABLE;
        }
        return ExtraBinder.NOP;
    }

}
