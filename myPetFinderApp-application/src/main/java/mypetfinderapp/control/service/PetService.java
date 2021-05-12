package github.davids13.mypetfinderapp.control.service;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;
import github.davids13.mypetfinderapp.control.dao.IGenericDAO;
import github.davids13.mypetfinderapp.entity.Owner;
import github.davids13.mypetfinderapp.entity.Pet;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PetService implements IPetService {

    @Inject
    private IGenericDAO iGenericDAO;

    public <T extends AbstractEntity> void create(final T object) {
        iGenericDAO.save(object);
    }

    public <T extends AbstractEntity> void modify(final T object) {
        iGenericDAO.update(object);
    }

    public <T extends AbstractEntity> void remove(final T object) {
        iGenericDAO.delete(object);
    }

    public Owner findOwner(final Integer id) {
        if (id == null) {
            return null;
        }

        return iGenericDAO.findById(Owner.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Owner> findAllOwners() {
        final Stream<Owner> ownerStream = (Stream<Owner>) iGenericDAO.findAll(Owner.OWNER_FIND_ALL, Owner.class);
        if (ownerStream == null)
            return Collections.emptyList();

        return ownerStream.collect(Collectors.toList());
    }

    public Pet findPet(final Integer id) {
        if (id == null) {
            return null;
        }

        return iGenericDAO.findById(Pet.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Pet> findAllPets() {
        final Stream<Pet> petStream = (Stream<Pet>) iGenericDAO.findAll(Pet.PET_FIND_ALL, Pet.class);
        if (petStream == null)
            return Collections.emptyList();

        return petStream.collect(Collectors.toList());
    }
}
