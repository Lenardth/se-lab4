package hu.bme.mit.spaceship;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_Empty() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }

    @Test
    void fire_All() {
        // Arrange
        TorpedoStore store = new TorpedoStore(3);

        // Act
        boolean result = store.fire(3);

        // Assert
        assertEquals(true, result);
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void fire_Partial() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2);

        // Act
        boolean result1 = store.fire(1);
        boolean result2 = store.fire(1);

        // Assert
        assertEquals(true, result1);
        assertEquals(true, result2);
        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }

    @Test
    void getTorpedoCount_AfterFire() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2);

        // Act
        store.fire(1);

        // Assert
        assertEquals(1, store.getTorpedoCount());
        store.fire(1);
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroAmount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_MoreThanAvailable() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
    }

    @Test
    void fire_NegativeAmount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
    }

    @Test
    void fire_TwiceExhaust_ThenFail() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result1 = store.fire(1);

        // Assert
        assertEquals(true, result1);
        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void newStore_ZeroCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0);

        // Assert
        assertEquals(0, store.getTorpedoCount());
    }
}
