package com.tale.prettybundle;

/**
 * Created by giang on 2/24/15.
 */
public class ExtraBinderProvider {
    public static ExtraBinder get(String dataType) {
        if ("java.lang.String".equals(dataType)) {
            return ExtraBinder.STRING;
        }
        return ExtraBinder.NOP;
    }
}
