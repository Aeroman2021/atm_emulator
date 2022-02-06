package com.example.atmemulator.service.core;

public interface EntityConvertor<T,V,E> {
    V convertInputToEntity(T t);
    E convertEntityToOutputDto(V v);
}
