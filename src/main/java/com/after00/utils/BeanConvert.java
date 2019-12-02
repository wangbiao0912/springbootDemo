package com.after00.utils;

public interface BeanConvert<S, T> {
    T convert(S s);
}
