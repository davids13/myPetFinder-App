package github.davids13.mypetfinderapp.control.mapping;

import github.davids13.mypetfinderapp.control.service.PetService;
import github.davids13.mypetfinderapp.entity.Owner;
import github.davids13.mypetfinderapp.entity.Pet;

import javax.inject.Inject;

public class MyPetFinderMapper {

    @Inject
    private PetService petService;

    public Owner mappingToOwner(final PayloadBody payloadBody) {
        if (payloadBody == null) {
            return null;
        }

        final Pet pet = mappingToPet(payloadBody);

        final Owner owner = new Owner();
        owner.setFirstName(payloadBody.getFirstName());
        owner.setLastName(payloadBody.getLastName());
        owner.setEmail(payloadBody.getEmail());
        owner.setPhone(payloadBody.getPhone());
        //owner.setPet(Collections.singletonList(pet));

        petService.create(owner);
        return owner;
    }

    public Pet mappingToPet(final PayloadBody payloadBody) {
        if (payloadBody == null) {
            return null;
        }

        final Pet pet = new Pet();
        pet.setPetName(payloadBody.getPetName());
        pet.setPetDescription(payloadBody.getPetDescription());
        pet.setOwner(payloadBody.getOwner());

        return pet;
    }

    public Owner mappingUpdate(final Owner ownerPayload, final Integer id) {
        if (ownerPayload == null || id == null) {
            return null;
        }

        final Owner owner = new Owner();
        owner.setFirstName(ownerPayload.getFirstName());
        owner.setLastName(ownerPayload.getLastName());
        owner.setPhone(ownerPayload.getPhone());
        owner.setPhone(ownerPayload.getPhone());
        owner.setId(id);

        return owner;
    }

}
