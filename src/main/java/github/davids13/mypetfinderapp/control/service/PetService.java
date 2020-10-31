package github.davids13.mypetfinderapp.control.service;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;
import github.davids13.mypetfinderapp.control.dao.GenericDAO;
import github.davids13.mypetfinderapp.entity.Owner;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PetService {

    @Inject
    private GenericDAO genericDAO;

    public <T extends AbstractEntity> void create(final T t) {
        genericDAO.save(t);
    }

    // Owner
    public Owner find(final Integer id) {
        if (id == null) {
            return null;
        }

        return genericDAO.findById(Owner.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Owner> findAll() {
        final Stream<Owner> ownerStream = (Stream<Owner>) genericDAO.findAll(Owner.OWNER_FIND_ALL, Owner.class);
        if (ownerStream == null)
            return Collections.emptyList();

        return ownerStream.collect(Collectors.toList());
    }

}
