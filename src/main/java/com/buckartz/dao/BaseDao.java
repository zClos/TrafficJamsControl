package com.buckartz.dao;

import java.io.Serializable;

public interface BaseDao<T> {

    void add(T t);

    T get(Class clazz, Serializable id);

    void update(T t);

    void delete(T t);

}
