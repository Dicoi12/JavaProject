package com.example.ImcBeProj.repositories.interfaces;

import java.util.List;
import java.util.Optional;

public interface IGenericRepository<T,ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    T update(T entity);
}
