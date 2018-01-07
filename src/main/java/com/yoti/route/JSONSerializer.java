package com.yoti.route;

public interface JSONSerializer {
    String toJson(Object src);

    <T> T fromJson(String json, Class<T> classOfT);
}
