package storage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {
    @Test
    public void readAsDateTest() {
        Storage storage = new Storage("./data/duke.txt");
        LocalDate localDate = LocalDate.of(2024,2,4);

        assertEquals(storage.readAsDate("2024-02-04"), localDate);
    }
}