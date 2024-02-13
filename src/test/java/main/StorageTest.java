package main;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void loadFromFile_exceptionThrown() {
        try {
            Storage.loadFromFile("./asdfjkl.txt");
        } catch (Exception e) {
            assertEquals("./asdfjkl.txt (No such file or directory)", e.getMessage());
        }
    }
}
