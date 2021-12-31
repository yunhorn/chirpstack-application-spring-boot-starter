package com.yunhorn.core.chirpstack.helper;

/**
 * @author ljm
 * @date 2021/12/22 14:44
 */
public class GlobalHelper {

    public static final String CHIRPSTACK_SOURCE = "chirpStackSource";
    public static final String CHIRPSTACK_TARGET = "chirpStackTarget";

    public static final String CHIRPSTACK_USER_INFO_CACHE = "chirpStack:user:info:cache";

    public static final String CACHE_KEY_SEPARATOR = ":";

    public static final String HOUR = "hour";

    public static final String MINUTE = "minute";

    public static final String DAY = "day";

    public static String getCacheKey(String prefix,String... keys){
        return getKey(prefix,CACHE_KEY_SEPARATOR,keys);
    }

    public static String getKey(String prefix,String sep,String... keys){
        StringBuilder stringBuilder = new StringBuilder(prefix);
        for (String key : keys) {
            stringBuilder.append(sep);
            stringBuilder.append(key);
        }
        return stringBuilder.toString();
    }
}
