package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class TaskListParserTest {
    @Test
    public void parse_validFile_returnsTaskList()
            throws IOException, InvalidDataFormatException, DukeDateTimeParseException {
        // Arrange
        File file = new File("./data/duke.txt");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        // Add some tasks to the file
        FileWriter writer = new FileWriter(file, false);
        writer.write("T | 0 | read book\n");
        writer.write("D | 0 | return book | 2024-02-01 18:00\n");
        writer.write("E | 0 | project meeting | 2024-02-01 14:00 | 2024-02-01 16:00\n");
        writer.close();

        // Act
        TaskList taskList = TaskListParser.parse(file);

        // Assert
        assertEquals(3, taskList.size());
        assertEquals("[T][ ] read book", taskList.get(0).toString());
        assertEquals("[D][ ] return book (due: Thu, Feb 1 2024 18:00)", taskList.get(1).toString());
        assertEquals("[E][ ] project meeting (at: Thu, Feb 1 2024 14:00 - Thu, Feb 1 2024 16:00)",
                taskList.get(2).toString());
    }

    @Test
    public void parse_invalidFile_throwsInvalidDataFormatException() throws IOException {
        // Arrange
        File file = new File("./data/duke.txt");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        // Add some tasks to the file
        FileWriter writer = new FileWriter(file, false);
        writer.write("E | 0 | project meeting | 2020-08-26 14:00 | 2020-08-26 16:00\n");
        writer.write("E | 0 | project meeting | 2020-08-26 14:00\n"); // Missing end time
        writer.write("D | 0 | return book \n"); // Missing due time
        writer.write("T \n"); // Random invalid line
        writer.close();

        // Act & Assert
        assertThrows(InvalidDataFormatException.class, () -> TaskListParser.parse(file));
    }

    @Test
    public void serialize_validTaskList_returnsSerializedString() throws DukeDateTimeParseException {
        // Arrange
        TaskList taskList = new TaskList();
        // Add tasks to the task list
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Deadline("return book", "2020-08-25 18:00"));
        taskList.addTask(new Event("project meeting", "2020-08-26 14:00", "2020-08-26 16:00"));

        // Act
        String serializedString = TaskListParser.serialize(taskList);

        // Assert
        assertEquals("T | 0 | read book\n" + "D | 0 | return book | 2020-08-25 18:00\n"
                + "E | 0 | project meeting | 2020-08-26 14:00 | 2020-08-26 16:00\n", serializedString);
    }
}
