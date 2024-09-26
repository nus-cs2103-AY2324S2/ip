package plato.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import plato.PlatoException;


class StorageTest {
    @Test
    public void load_wrongFile_failure() {
        String filePath = "testFakePath";
        Storage store = new Storage(filePath);
        assertThrows(PlatoException.class, store::loadFile);
    }

}
