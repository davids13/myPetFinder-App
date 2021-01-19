package github.davids13.mypetfinderapp.control.dao;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.stream.Stream;

@Stateless // The server it's responsible for the transactions (EJB)
public class GenericDAO implements IGenericDAO {

    private static final String PERSISTENT_UNIT = "petFinder_PU";

    @PersistenceContext(unitName = PERSISTENT_UNIT)
    private EntityManager entityManager;

    // Generic CRUD methods
    //@Transactional Mark your CDI bean method as @Transactional and the EntityManager will enlist and flush at commit.
    public <T extends AbstractEntity> void save(final T object) {
        entityManager.persist(object);
    }

    public <T extends AbstractEntity> void delete(final T object) {
        entityManager.remove(object);
    }

    public <T extends AbstractEntity> void update(final T object) {
        entityManager.merge(object);
    }

    public <T extends AbstractEntity> T findById(final Class<T> clazz, final Integer id) {
        return entityManager.find(clazz, id);
    }

    public Stream<?> findAll(final String queryName, final Class<?> clazz) {
        TypedQuery<?> query = entityManager.createNamedQuery(queryName, clazz);
        return query.getResultStream();
    }
}
