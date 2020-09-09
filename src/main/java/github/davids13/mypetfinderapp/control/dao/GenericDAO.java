package github.davids13.mypetfinderapp.control.dao;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.stream.Stream;

@Stateless
public class GenericDAO {

    private static final String PERSISTENT_UNIT = "petFinder_PU";

    @PersistenceContext(unitName = PERSISTENT_UNIT)
    private EntityManager entityManager;

    // Generic CRUD methods
    public <T extends AbstractEntity> void save(final T object) {
        entityManager.persist(object);
    }

    public Stream<?> getAll(final String queryName, final Class<?> clazz) {
        TypedQuery<?> query = entityManager.createNamedQuery(queryName, clazz);
        return query.getResultStream();
    }
}
