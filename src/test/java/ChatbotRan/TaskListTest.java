package ChatbotRan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class TaskIoStub extends TaskIo {
    ArrayList<Task> tasks;
    public TaskIoStub(ArrayList<Task> tasks) {
        super("data/ran.txt");
        this.tasks = tasks;
    }

    @Override
    public void writeTasks(ArrayList<Task> tasks) {
    }

    @Override
    public ArrayList<Task> findTasks() {
        return tasks;
    }
}
class TaskListTest {

    @Test
    void get() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("task1"));
        TaskList tl = new TaskList(new TaskIoStub(tasks));
        assertEquals(tl.get(0), tasks.get(0));
    }

    @Test
    void remove() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("task1"));
        TaskList tl = new TaskList(new TaskIoStub(tasks));
        tl.remove(tasks.get(0));
assertThrows(RuntimeException.class, () -> tl.get(0));
    }

    @Test
    void set() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("task1"));
        TaskList tl = new TaskList(new TaskIoStub(tasks));
        Task t2 = new Todo("task2");
        tl.set(0,t2);
        assertEquals(tl.get(0),t2);
    }
}