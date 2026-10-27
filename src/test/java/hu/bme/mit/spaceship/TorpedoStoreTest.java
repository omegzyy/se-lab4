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

    // firing one torpedo should take one away from the store
    @Test
    void fire_Success_DecreasesCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act
        store.fire(1);

        // Assert
        assertEquals(4, store.getTorpedoCount());
    }

    // failure rate 1.0 means it always fails, so no torpedo should be used up
    @Test
    void fire_AlwaysFails() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 1.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(false, result);
        assertEquals(5, store.getTorpedoCount());
    }

    // cant fire zero torpedoes
    @Test
    void fire_Zero_Throws() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    // cant fire more torpedoes than the store has
    @Test
    void fire_MoreThanAvailable_Throws() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(2));
    }

    // after firing the last torpedo the store should be empty
    @Test
    void isEmpty_AfterLastTorpedo() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act
        store.fire(1);

        // Assert
        assertEquals(true, store.isEmpty());
    }
}
