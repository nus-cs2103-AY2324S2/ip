package linus.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import linus.exceptions.LinusCeption;
import linus.exceptions.IncorrectFormatException;
import linus.exceptions.NumberOutOfBoundsException;

public class TaskListTest {

    @Test
    public void testDeleteFunction() throws LinusCeption {

        Task task1 = new ToDo("Eat food");
        Task task2 = new Deadline("Do homework", "19/03/2020 1900");
        Task task3 = new Event("CS2103 Exam", "25/05/2024 1400", "25/05/2024 1400");
        
        TaskList taskList1 = new TaskList();
        TaskList taskList2 = new TaskList();

        taskList1.addNewTask(task1);
        taskList1.addNewTask(task2);
        taskList1.addNewTask(task3);

        taskList2.addNewTask(task1);
        taskList2.addNewTask(task3);

        taskList1.delete("2");

        assertEquals(taskList1.toString(), taskList2.toString());
    }

    @Test
    public void testSaveFormat() {
        Task task1 = new ToDo("Eat food");
        Task task2 = new Deadline("Do homework", "19/03/2020 1900");
        Task task3 = new Event("CS2103 Exam", "25/05/2024 1400", "25/05/2024 1400");

        TaskList taskList = new TaskList();
        taskList.addNewTask(task1);
        taskList.addNewTask(task2);
        taskList.addNewTask(task3);

        task2.markAsDone();

        ArrayList<String> dataToTextList = taskList.saveFormat();

        String expectedDataToText = "T;;0;;Eat food"
                + "D;;1;;Do homework;;19/03/2020 1900"
                + "E;;0;;CS2103 Exam;;25/05/2024 1400;;25/05/2024 1400";

        String actualDataToText = "";
        for (String line : dataToTextList) {
            actualDataToText += line;
        }

        assertEquals(expectedDataToText, actualDataToText);
    }

    @Test 
    public void deletetTask_IncorrectFormatException() {
        Task task1 = new ToDo("Eat food");
        Task task2 = new Deadline("Do homework", "19/03/2020 1900");
        Task task3 = new Event("CS2103 Exam", "25/05/2024 1400", "25/05/2024 1400");

        TaskList taskList = new TaskList();
        taskList.addNewTask(task1);
        taskList.addNewTask(task2);
        taskList.addNewTask(task3);

        try {
            taskList.delete("Two");
        } catch (IncorrectFormatException e) {
            assertEquals("The number given is unrecognizable", e.getMessage());
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void getTask_NumberOutOfBoundsException() {
        Task task1 = new ToDo("Eat food");
        Task task2 = new Deadline("Do homework", "19/03/2020 1900");
        Task task3 = new Event("CS2103 Exam", "25/05/2024 1400", "25/05/2024 1400");

        TaskList taskList = new TaskList();
        taskList.addNewTask(task1);
        taskList.addNewTask(task2);
        taskList.addNewTask(task3);

        try {
            taskList.getTask("4");
        } catch (NumberOutOfBoundsException e) {
            assertEquals("The number is not in this list!", e.getMessage());
        } catch (Exception e) {
            fail();
        }

    }


}
