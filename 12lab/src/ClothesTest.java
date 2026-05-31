import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ClothesTest {

    @Test
    void shouldThrowOnInvalidPriceInSetter() {
        Pants pants = new Pants("Штани", "Brand", 100, "M", "чорний", SizeCategory.M, 30, 100);
        assertThrows(InvalidFieldValueException.class, () -> pants.setPrice(-10));
    }

    @Test
    void shouldThrowOnInvalidNameInConstructor() {
        assertThrows(InvalidFieldValueException.class, () ->
                new Pants("", "Brand", 100, "M", "чорний", SizeCategory.M, 30, 100));
    }
}
