import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ClothesTest {
    @Test
    void invalidPriceInSetter() {
        Clothes c = new Clothes("Сорочка", "H&M", 200, "M", "білий");
        assertThrows(IllegalArgumentException.class, () -> c.setPrice(-1));
    }

    @Test
    void invalidConstructor() {
        assertThrows(IllegalArgumentException.class, () ->
                new Clothes("", "H&M", 200, "M", "білий"));
    }
}
