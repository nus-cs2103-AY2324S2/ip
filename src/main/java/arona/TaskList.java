package arona;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(TwoArrayList tasksToAdd, Ui ui) throws TaskException {
        tasks = new ArrayList<>();
        this.ui = ui;
        for (int i = 0; i < tasksToAdd.getList1().size(); i++) {
            addTask(tasksToAdd.getList1().get(i));
            changeTaskStatus(i + 1, tasksToAdd.getList2().get(i));
        }
    }

    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public void addTask(String input) throws TaskException {
        String[] splitInput = input.split(" ", 2);
        String type = splitInput[0];

        if (splitInput.length == 0) {
            throw new TaskException("Sensei! Please enter some tasks!");
        } else if (!containsEnumValue(TaskEnum.class, type)) {
            throw new TaskException("Sensei, Arona does not know what that means!");
        } else if (splitInput.length == 1) {
            throw new TaskException("Sensei! Please provide some task description!");
        }

        String[] info = splitInput[1].split("/");
        String description = info[0];
        if (description.equals("")) throw new TaskException("Sensei! Please provide some task description!");

        switch(type) {
        case "todo":
            tasks.add(new Todo(description));
            break;
        case "deadline":
            if (info.length < 2) throw new TaskException("Sensei! Please provide a deadline!");

            String by = info[1].replaceAll("by", "").trim();
            try {
                LocalDate date = Parser.parseDate(by);
                tasks.add(new Deadline(description, date));
            } catch (DateTimeParseException e) {
                throw new TaskException("Sensei! The date format has to be in yyyy-mm-dd!");
            }
            break;
        case "event":
            if (info.length < 3) throw new TaskException("Sensei! Please provide an event begin and deadline.");

            String from = info[1].replaceAll("from", "").trim();
            by = info[2].replaceAll("to", "").trim();

            try {
                LocalDate dateFrom = Parser.parseDate(from);
                LocalDate dateBy = Parser.parseDate(by);
                tasks.add(new Event(description, dateFrom, dateBy));
            } catch (DateTimeParseException e) {
                throw new TaskException("Sensei! The date format has to be in yyyy-mm-dd!");
            }
            break;
        }
        ui.taskAdded(tasks);
    }

    public void DeleteTask(int taskNum) throws IndexOutOfBoundsException {
        if (taskNum > tasks.size()) throw new IndexOutOfBoundsException("Sensei, the task doesn't exist!");

        int index = taskNum - 1;
        Task task = tasks.get(index);
        tasks.remove(index);
        ui.taskDeleted(task, tasks.size());
    }

    public void changeTaskStatus(int taskNum, boolean status) throws IndexOutOfBoundsException {
        if (taskNum > tasks.size()) throw new IndexOutOfBoundsException("Sensei! The task doesn't exist!");

        int index = taskNum - 1;
        Task task = tasks.get(index);

        if (task.getStatus() == status) {
            ui.isMarked(status);
            return;
        }

        task.setStatusIcon(status);

        if (status) {
            ui.markTask();
        } else {
            ui.unmarkTask();
        }
//        ui.printTasks(tasks);
    }

    public void findTasks(String fullCommand) {
        ArrayList<Task> newTasksList = new ArrayList<>();
        String keyword = Parser.ExtractDescription(fullCommand);
        System.out.println(keyword);
        for (Task task : tasks) {
            String description = task.getDescription();
            boolean contains = description.contains(keyword);
            if (contains) {
                newTasksList.add(task);
            }
        }
        ui.printTasks(newTasksList);
    }

    private static <E extends Enum<E>> boolean containsEnumValue(Class<E> enumClass, String value) {
        for (Enum<E> enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
