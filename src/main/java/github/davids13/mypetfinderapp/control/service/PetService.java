package github.davids13.mypetfinderapp.control.service;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;
import github.davids13.mypetfinderapp.control.dao.GenericDAO;
import github.davids13.mypetfinderapp.entity.Owner;
import github.davids13.mypetfinderapp.entity.Pet;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PetService {

    @Inject
    private GenericDAO genericDAO;

    public <T extends AbstractEntity> void create(final T object) {
        genericDAO.save(object);
    }

    public <T extends AbstractEntity> void modify(final T object) {
        genericDAO.update(object);
    }

    public <T extends AbstractEntity> void remove(final T object) {
        genericDAO.delete(object);
    }

    public Owner findOwner(final Integer id) {
        if (id == null) {
            return null;
        }

        return genericDAO.findById(Owner.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Owner> findAllOwners() {
        final Stream<Owner> ownerStream = (Stream<Owner>) genericDAO.findAll(Owner.OWNER_FIND_ALL, Owner.class);
        if (ownerStream == null)
            return Collections.emptyList();

        return ownerStream.collect(Collectors.toList());
    }

    public Pet findPet(final Integer id) {
        if (id == null) {
            return null;
        }

        return genericDAO.findById(Pet.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Pet> findAllPets() {
        final Stream<Pet> petStream = (Stream<Pet>) genericDAO.findAll(Pet.PET_FIND_ALL, Pet.class);
        if (petStream == null)
            return Collections.emptyList();

        return petStream.collect(Collectors.toList());
    }
}
