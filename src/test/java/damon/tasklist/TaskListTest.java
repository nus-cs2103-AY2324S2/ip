package damon.tasklist;  //same package as the class being tested

import damon.task.Deadline;
import damon.task.Event;
import damon.task.Task;
import damon.task.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private ArrayList<Task> originArrayList;

    public TaskListTest() {
        ArrayList<Task> exampleTaskList = new ArrayList<Task>();
        exampleTaskList.add(new ToDo("return books"));
        this.originArrayList = exampleTaskList;
    }

    public TaskList getOriginalTaskList() {
        return new TaskList(this.originArrayList);
    }

    @Test
    public void addTaskTest() {
        Deadline exampleDeadline = new Deadline("submit assignment",
                LocalDate.parse("2024-02-21"));
        Event exampleEvent = new Event("go to the party",
                "Friday", "Saturday");

        ArrayList<Task> expectedArrayList = this.originArrayList;
        expectedArrayList.add(exampleDeadline);
        expectedArrayList.add(exampleEvent);
        TaskList expectedTaskList = new TaskList(expectedArrayList);

        TaskList actualTaskList = this.getOriginalTaskList();
        actualTaskList.addTask(exampleDeadline);
        actualTaskList.addTask(exampleEvent);

        //Solution below inspired by https://www.javatpoint.com/how-to-check-data-type-in-java
        assertEquals(expectedTaskList.getClass(), actualTaskList.getClass());

        assertEquals(expectedTaskList.getArrayList(), actualTaskList.getArrayList());
    }
}
