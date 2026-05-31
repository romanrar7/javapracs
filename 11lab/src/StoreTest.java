import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StoreTest {

    @Test
    void shouldThrowWhenDeletingMissingItem() {
        Store store = new Store("Test", "Addr");
        Pants pants = new Pants("Джинси", "Levi", 500, "M", "синій", SizeCategory.M, 32, 100);

        assertThrows(ObjectNotFoundException.class, () -> store.delete(pants));
    }

    @Test
    void shouldThrowWhenInvalidQuantityOnAdd() {
        Store store = new Store("Test", "Addr");
        Pants pants = new Pants("Джинси", "Levi", 500, "M", "синій", SizeCategory.M, 32, 100);

        assertThrows(InvalidFieldValueException.class, () -> store.addNewClothes(pants, 0));
    }
}
