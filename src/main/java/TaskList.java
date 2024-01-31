import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(TwoArrayList tasksToAdd) throws TaskException {
        this.tasks = new ArrayList<>();
        int counter = 1;
        for (int i = 0; i < tasksToAdd.getList1().size(); i++) {
            addTask(tasksToAdd.getList1().get(i));
            changeTaskStatus(counter, tasksToAdd.getList2().get(i));
        }
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
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
            throw new TaskException("Sensei, Arona does not know what that means!.");
        } else if (splitInput.length == 1) {
            throw new TaskException("Sensei! Please provide some task description!");
        }
        System.out.println(splitInput[1]);
        String[] info = splitInput[1].split("/", 0);
        String description = info[0];
        for (String string : info) {
            System.out.println(string);
        }

        switch(type) {
            case "todo":
                tasks.add(new Todo(description));
                break;
            case "deadline":
                if (info.length < 2) throw new TaskException("Sensei! Please provide a deadline!");

                String by = info[1].replaceAll("by", "").trim();
                try {
//                    System.out.println(by);
                    LocalDate date = Parser.parseDate(by);
                    tasks.add(new Deadline(description, date));
                } catch (DateTimeParseException e) {
                    throw new TaskException("Sensei! Arona does not recognise this date format!");
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
                    throw new TaskException("Sensei! Arona does not recognise this date format!");
                }
                break;
        }

        System.out.println("Arona has added this task to sensei's task list!: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Arona has counted " + tasks.size() + " tasks in the list!");
    }

    public void DeleteTask(int taskNum) throws IndexOutOfBoundsException {
        if (taskNum > tasks.size()) throw new IndexOutOfBoundsException("Sensei, the task doesn't exist!");

        int index = taskNum - 1;
        Task task = tasks.get(index);
        tasks.remove(index);

        System.out.println("Arona has removed this task!: ");
        System.out.println(task.toString());
        System.out.println("Arona has counted " + tasks.size() + " tasks in the list!");
    }

    public void printTasks() {
        System.out.println("Sensei! Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) break;
            Task task = tasks.get(i);
            System.out.println(i + 1 + "." + task.toString());
        }
    }

    public void changeTaskStatus(int taskNum, boolean status) throws IndexOutOfBoundsException {
        if (taskNum > tasks.size()) throw new IndexOutOfBoundsException("Sensei! The task doesn't exist!");

        int index = taskNum - 1;
        Task task = tasks.get(index);

        if (task.getStatus() == status) {
            System.out.println("Sensei, the task has already been marked as " + (status ? "done!" : "not done!"));
            return;
        }

        task.setStatusIcon(status);

        if (status) {
            System.out.println("Congratulation, sensei! Arona has marked the task as done!:");
        } else {
            System.out.println("I understand, sensei! Arona has marked the task as not done!:");
        }
        System.out.println(task.toString());
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
