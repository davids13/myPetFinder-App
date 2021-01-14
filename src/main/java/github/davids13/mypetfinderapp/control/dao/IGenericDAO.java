package github.davids13.mypetfinderapp.control.dao;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import java.util.stream.Stream;

//@Local
public interface IGenericDAO {

    <T extends AbstractEntity> void save(final T object);

    <T extends AbstractEntity> void delete(final T object);

    <T extends AbstractEntity> void update(final T object);

    <T extends AbstractEntity> T findById(final Class<T> clazz, final Integer id);

    Stream<?> findAll(final String queryName, final Class<?> clazz);
}
