import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskList {
    private static final int MAX_TASKS = 100;
    private static final String FILE_PATH = "./data/duke/.txt";
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public LocalDateTime parseDate(String dateString) throws ChatBotException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDateTime.parse(dateString, dateTimeFormatter);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(dateString, dateFormatter).atStartOfDay();
            } catch (DateTimeParseException e2) {
            }
        }
        throw new ChatBotException("Oops! Invalid date and time format. " +
                "Please enter in yyyy-MM-dd HHmm format, you may leave HHmm empty.");
    }

    public void splitTask(String task) throws ChatBotException {
        String[] splitTask = task.split(" ", 2);
        if (splitTask.length <= 1) {
            throw new ChatBotException("Oops! You have not entered a task.");
        }
        String taskType = splitTask[0];
        String taskName = splitTask[1];

        if (taskType.equalsIgnoreCase("todo")) {
            this.tasks.add(new ToDo(taskName));
            return;
        }

        if (taskType.equalsIgnoreCase("deadline")) {
            String[] splitDeadline = taskName.split(" /by ");
            if (splitDeadline.length <= 1) {
                throw new ChatBotException("Oops! Please enter the date/day in this format: '/by date/day'.");
            }
            String action = splitDeadline[0];
            LocalDateTime due = parseDate(splitDeadline[1]);
            this.tasks.add(new Deadline(action, due));
            return;
        }

        if (taskType.equalsIgnoreCase("event")) {
            String[] splitEvent = taskName.split(" /from | /to ");
            if (splitEvent.length <= 2) {
                throw new ChatBotException("Oops! Please enter the date/day/time in this format:" +
                        " '/from date/day/time " + "/to date/day/time'.");
            }
            String eventName = splitEvent[0];
            LocalDateTime eventStart = parseDate(splitEvent[1]);
            LocalDateTime eventEnd = parseDate(splitEvent[2]);
            this.tasks.add(new Event(eventName, eventStart, eventEnd));
            return;
        }
        throw new ChatBotException("Oops! Please enter task type of 'Todo/Event/Deadline.");
    }

    public void addTask(String task) throws ChatBotException {
        if (this.tasks.size() < MAX_TASKS) {
            try {
                splitTask(task);
                saveTasksFromFile();
                printDivider();
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + this.tasks.get(this.tasks.size() - 1));
                System.out.println("Now you have " + this.tasks.size() + " task" +
                        (this.tasks.size() == 1 ? "" : "s") + " in the list");
                printDivider();
            } catch (ChatBotException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new ChatBotException("Oops! The task list is full.");
        }
    }

    public void deleteTask(int number) throws ChatBotException {
        if (number < 0) {
            throw new ChatBotException("Oops! Number entered cannot be negative.");
        }
        if (this.tasks.size() == 0) {
            throw new ChatBotException("Oops! There are no tasks in the list.");
        }
        if (number > this.tasks.size()) {
            throw new ChatBotException("Oops! Number entered does not exist in the list.");
        }

        Task taskDeleted = this.tasks.remove(number - 1);
        saveTasksFromFile();
        printDivider();
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + taskDeleted.toString());
        System.out.println("Now you have " + this.tasks.size() + " task" +
                (this.tasks.size() == 1 ? "" : "s") + " in the list");
        printDivider();
    }

    public void markTask(int index) throws ChatBotException {
        if (index < 0) {
            throw new ChatBotException("Oops! Number entered cannot be negative.");
        }
        if (index == 0 || index > this.tasks.size()) {
            throw new ChatBotException("Oops! Invalid task number.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        saveTasksFromFile();
        printDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + currTask.toString());
        printDivider();
    }

    public void unmarkTask(int index) throws ChatBotException {
        if (index < 0) {
            throw new ChatBotException("Oops! Number entered cannot be negative.");
        }
        if (index == 0 || index > this.tasks.size()) {
            throw new ChatBotException("Oops! Invalid task number.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsNotDone();
        saveTasksFromFile();
        printDivider();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + currTask.toString());
        printDivider();
    }

    public void listTasks() throws ChatBotException {
        if (this.tasks.size() == 0) {
            throw new ChatBotException("Oops! The task list is currently empty.");
        } else {
            printDivider();
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                System.out.println((i + 1) + "." + currTask.toString());
            }
        }
        printDivider();
    }

    public Task splitTasksFromFile(String tasks) throws ChatBotException {
        String[] splitFileTask = tasks.split("\\|");
        if (splitFileTask.length < 3 || splitFileTask.length > 4) {
            throw new ChatBotException("Oops! Task format is invalid.");
        }
        String type = splitFileTask[0];
        boolean isDone = splitFileTask[1].trim().equals("1");
        String description = splitFileTask[2].trim();
        String dateOrTime = splitFileTask.length > 3 ? splitFileTask[3].trim() : null;
        switch (type) {
        case "T":
            ToDo todo = new ToDo(description);
            if (isDone) {
                todo.markAsDone();
            }
            this.tasks.add(todo);
            return todo;
        case "D":
            if (dateOrTime == null) {
                throw new ChatBotException("Oops! Deadline format is invalid.");
            }
            LocalDateTime due = LocalDateTime.parse(dateOrTime);
            Deadline deadline = new Deadline(description, due);
            if (isDone) {
                deadline.markAsDone();
            }
            this.tasks.add(deadline);
            return deadline;
        case "E":
            String[] time = dateOrTime.split("to");
            if (time.length < 2) {
                throw new ChatBotException("Oops! Event format is invalid.");
            }
            LocalDateTime eventStart = LocalDateTime.parse(time[0]);
            LocalDateTime eventEnd = LocalDateTime.parse(time[1]);
            Event event = new Event(description, eventStart, eventEnd);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            throw new ChatBotException("Oops! Unknown task type");
        }
    }

    public void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                splitTasksFromFile(taskString);
            }
        } catch (IOException e) {
            System.out.println("Oops! There was an error loading the file.");
        } catch (ChatBotException e) {
            System.out.println("Oops! There was an error parsing the file.");
        }
    }

    public void saveTasksFromFile() {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < this.tasks.size(); i++) {
                Task task = this.tasks.get(i);
                fileWriter.write(task.toStringForFile() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Oops! There was an error saving the file.");
        }
    }
}