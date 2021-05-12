package github.davids13.mypetfinderapp.control.service;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;
import github.davids13.mypetfinderapp.entity.Owner;
import github.davids13.mypetfinderapp.entity.Pet;

import java.util.List;

//@Local
public interface IPetService {

    <T extends AbstractEntity> void create(final T object);

    <T extends AbstractEntity> void modify(final T object);

    <T extends AbstractEntity> void remove(final T object);

    Owner findOwner(final Integer id);

    List<Owner> findAllOwners();

    Pet findPet(final Integer id);

    List<Pet> findAllPets();
}
