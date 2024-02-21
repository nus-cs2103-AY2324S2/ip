package storage;

import exception.TobiasException;
import org.junit.jupiter.api.Test;
import task.Event;
import task.Task;
import task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StorageTest {
    @Test
    public void corrupted_saved_file() {
        // Instead of D for deadline, the file is corrupted and adds a G instead
        String testStoredCommand = "G|isdone1|descmarathon |from15-03-2024 1000|to15-03-2024 1600";
        try {
            TaskList testList = new TaskList();
            Storage.localToList(testStoredCommand, testList);
        } catch (TobiasException e) {
            assert(e.getMessage().equals("   Saved file is corrupted!"));
        }
    }

    @Test
    public void correct_stored_data_is_loaded() {
        // Should store an event command that is :
        // isDone: true,
        // description : marathon,
        // from & to : 15/03/2024 10 am to 15/03/2024 4pm
        String testStoredCommand = "E|isdone1|descmarathon |from15-03-2024 1000|to15-03-2024 1600";
        try {
            TaskList testList = new TaskList();
            LocalDateTime from = LocalDateTime.parse("15-03-2024 1000", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            LocalDateTime to = LocalDateTime.parse("15-03-2024 1600", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            Task expected = new Event("marathon ", true, from, to);

            Storage.localToList(testStoredCommand, testList);

            assert(expected.equals(testList.getTask(0)));

        } catch (TobiasException e) {
            e.printMessage();
        }
    }
}
