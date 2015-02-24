package com.tale.prettybundle;

/**
 * Created by giang on 2/24/15.
 */
public class ExtraGetterProvider {
    public static ExtraGetter get(String dataType) {
        if ("java.lang.String".equals(dataType)) {
            return ExtraGetter.STRING;
        }
        return ExtraGetter.NOP;
    }
}
