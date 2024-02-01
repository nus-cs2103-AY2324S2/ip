package numerator.task;

import numerator.Ui;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();


    public void markAsDone(int idx) throws IndexOutOfBoundsException {
        Task t = this.taskList.get(idx);
        t.markAsDone();

    }

    public void markLastAsDone() {
        if (this.taskList.isEmpty()) {
            return;
        }
        Task t = this.taskList.get(this.taskList.size() - 1);
        t.markAsDone();
    }

    public void markAsUndone(int idx) throws IndexOutOfBoundsException {
        Task t = this.taskList.get(idx);
        t.markAsNotDone();

    }

    public Task removeTask(int idx) throws IndexOutOfBoundsException {
        Task t = this.taskList.get(idx);
        this.taskList.remove(idx);
        return t;
    }

    public Task addToDo(String taskDesc) {
        Task t = new ToDo(taskDesc);
        this.taskList.add(t);
        return t;
    }

    public Task addEvent(String taskDesc, String from, String to) {
        Task t = new Event(taskDesc, from, to);
        this.taskList.add(t);
        return t;
    }

    public Task addDeadline(String taskDesc, String by) {
        Task t = new Deadline(taskDesc, by);
        this.taskList.add(t);
        return t;
    }


    public void printTask(int idx) {
        String s = this.taskList.get(idx).toString() + "\n";
        Ui.printMessage(s);
    }

    public void printAddTask(Task task) {
        Ui.printMessage("Got it, I've added this task:");
        Ui.printMessage(task.toString() + "\n");
        String s = String.format("Now you have %d tasks in the list\n", taskList.size());
        Ui.printMessage(s);


    }

    public String getSavedTasksString() {
        return String.join("\n", this.taskList
                .stream()
                .map(Task::getSaveString)
                .toArray(String[]::new));
    }

    public String getSizeAsString() {
        return String.valueOf(this.taskList.size());
    }

    @Override
    public String toString() {
        if (this.taskList.isEmpty()) {
            return "Congrats! Your list is empty. Go enjoy your day!";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

        IntStream.iterate(1, x -> x + 1)
                .limit(this.taskList.size())
                .forEachOrdered(i -> {
                    String s = String.format("%d. %s\n", i, this.taskList.get(i - 1));
                    sb.append(s);
                });
        return sb.toString().stripTrailing();

    }
}
