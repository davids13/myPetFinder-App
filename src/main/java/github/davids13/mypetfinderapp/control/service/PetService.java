package github.davids13.mypetfinderapp.control.service;

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

    @SuppressWarnings("unchecked")
    public List<Owner> getOwnersList() {
        final Stream<Owner> ownerStream = (Stream<Owner>) genericDAO.getAll(Owner.OWNER_FIND_ALL, Owner.class);
        if (ownerStream == null)
            return Collections.emptyList();

        return ownerStream.collect(Collectors.toList());
    }
}
