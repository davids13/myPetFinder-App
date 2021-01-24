package github.davids13.mypetfinderapp.control.dao;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Objects;
import java.util.stream.Stream;

@Stateless // The server it's responsible for the transactions (EJB)
public class GenericDAO implements IGenericDAO {

    private static final String PERSISTENT_UNIT = "petFinder_PU";

    @PersistenceContext(unitName = PERSISTENT_UNIT)
    private EntityManager entityManager;

    // Generic CRUD methods

    /***
     *
     * @param object is the object to perform save operation
     * @param <T> accepts any class that extends AbstractEntity class
     */
    //@Transactional Mark your CDI bean method as @Transactional and the EntityManager will enlist and flush at commit.
    public <T extends AbstractEntity> void save(final T object) {
        entityManager.persist(object);
    }

    /***
     *
     * @param object is the object to perform delete operation
     * @param <T> accepts any class that extends AbstractEntity class
     */
    public <T extends AbstractEntity> void delete(final T object) {
        entityManager.remove(object);
    }

    /***
     *
     * @param object is the object to perform the update operation
     * @param <T> accepts any class that extends AbstractEntity class
     */
    public <T extends AbstractEntity> void update(final T object) {
        entityManager.merge(object);
    }

    /***
     *
     * @param clazz to be searched
     * @param id to search in DB
     * @param <T> accepts any class that extends AbstractEntity class
     * @return an entity from DB with specific ID
     */
    public <T extends AbstractEntity> T findById(final Class<T> clazz, final Integer id) {
        return entityManager.find(clazz, id);
    }

    /***
     *
     * @param queryName to perform JPQL query
     * @param clazz to searched
     * @return all records for a specific entity from DB
     */
    public Stream<?> findAll(final String queryName, final Class<?> clazz) {
        TypedQuery<?> query = entityManager.createNamedQuery(queryName, clazz);
        return query.getResultStream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericDAO that = (GenericDAO) o;
        return Objects.equals(entityManager, that.entityManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityManager);
    }

    @Override
    public String toString() {
        return String.format("GenericDAO{entityManager=%s}", entityManager);
    }
}
