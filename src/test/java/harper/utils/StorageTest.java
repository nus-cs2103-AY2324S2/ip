package harper.utils;

import harper.exceptions.HarperFileLoadingException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    public void load_correctFileFormat_success() {
        Storage storage = new Storage("src/test", "test2.txt");

        assertDoesNotThrow(() -> storage.load());
        assertEquals(3, storage.load().size());
    }

    @Test
    public void load_incorrectFileFormat_exceptionThrown() {
        Storage storage = new Storage("src/test", "test3.txt");

        assertThrows(HarperFileLoadingException.class,
                () -> storage.load(),
                "Should throw HarperFileLoadingException");
    }
}
