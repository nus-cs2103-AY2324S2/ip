package ben.tasks;

import ben.commands.DeadlineCommand;
import ben.exceptions.BenException;
import ben.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(File file) throws FileNotFoundException, BenException {
        this.tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] tokens = line.split(" \\| ");
            String taskType = tokens[0];
            boolean isDone = tokens[1].equals("1");
            String description = tokens[2];

            switch (taskType) {
                case "T":
                    this.tasks.add(new Todo(isDone, description));
                    break;

                case "D":
                    String by = tokens[3];

                    try {
                        LocalDate deadline = LocalDate.parse(by);
                        this.tasks.add(new Deadline(isDone, description, deadline));
                        break;
                    } catch (DateTimeParseException e) {
                        throw new BenException("Invalid deadline format");
                    }

                case "E":
                    String startDate = tokens[3];
                    String endDate = tokens[4];

                    try {
                        LocalDate dateFormattedStartDate = LocalDate.parse(startDate);
                        LocalDate dateFormattedEndDate = LocalDate.parse(endDate);
                        this.tasks.add(new Event(isDone, description, dateFormattedStartDate, dateFormattedEndDate));
                        break;
                    } catch (DateTimeParseException e) {
                        throw new BenException("Invalid deadline format");
                    }

                default:
                    break;
            }
        }
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public boolean isWithinBounds(int index) {
        return index < 0 || index > tasks.size() - 1;
    }

    public int size() {
        return this.tasks.size();
    }

    public void markTask(int index) {
        Task currTask = this.tasks.get(index);
        currTask.markTask();
    }

    public void unmarkTask(int index) {
        Task currTask = this.tasks.get(index);
        currTask.unmarkTask();
    }


    public String toString(int index) {
        Task currTask = this.tasks.get(index);
        return currTask.toString();
    }

    public void showTaskList() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currTask = this.tasks.get(i);
            Ui.show((i + 1) + ". " + currTask);
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int index) {
        Task deletedTask = this.tasks.remove(index);
        return deletedTask;
    }

    public String formatSave() {
        StringBuilder s = new StringBuilder();
        for (Task currTask : this.tasks) {
            s.append(currTask.saveTask())
                    .append(System.lineSeparator());
        }
        return s.toString();
    }
}
