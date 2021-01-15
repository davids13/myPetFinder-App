package github.davids13.mypetfinderapp.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OwnerTest {

    @Test
    @DisplayName("Validate Owner fields expected success")
    void testEntityFieldsOK() {
        final Owner owner = new Owner();
        final Owner ownerCompare = generateOwnerFull();
        Assertions.assertEquals(owner, ownerCompare);
    }

    public Owner generateOwnerFull() {
        final Owner owner = new Owner();
        return owner;
    }
}