import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_PATH = "./data/duke.txt";
    public Duke(String filePath) {
        ui = new Ui();
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }
    public void run() {
        ArrayList<Task> list = new ArrayList<>();
        loadTasks(list);
        ui.showWelcomeMsg();
        Scanner scanner = new Scanner(System.in);
        loop:
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.startsWith("mark")) {
                int idx = Integer.parseInt(userInput.substring(5));
                Task taskToMark = list.get(idx - 1);
                taskToMark.markTask(); // method currently has both internal + ui, will extract ui first
                ui.showMarkedTask(taskToMark);
                saveTasks(list);
            } else if (userInput.startsWith("unmark")) {
                int idx = Integer.parseInt(userInput.substring(7));
                Task taskToUnmark = list.get(idx - 1);
                taskToUnmark.unmarkTask();
                ui.showUnmarkedTask(taskToUnmark);
                saveTasks(list);
            } else if (userInput.startsWith("todo")) {
                try {
                    if (userInput.length() < 6) {
                        throw new DukeException("Remember to add an actual task. Try again!");
                    }
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                    continue;
                }
                String todoTask = userInput.substring(5);
                ToDos newTodo = new ToDos(todoTask);
                list.add(newTodo);
                ui.showAddedTask(newTodo, list.size());
                saveTasks(list);
            } else if (userInput.startsWith("deadline")) {
                try {
                    // other improvements: no content, empty deadline
                    if (userInput.length() < 10) {
                        throw new DukeException("Remember to add an actual task. Try again!");
                    }
                    if (!(userInput.contains("/by"))) {
                        throw new DukeException("Remember to state the deadline after a '/by'!");
                    }
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                    continue;
                }
                int preIdx = userInput.indexOf("/by");
                int idx = preIdx + 4;
                String deadlineTask = userInput.substring(9, preIdx - 1);
                String deadlineBy = userInput.substring(idx);
                LocalDate deadlineDate = LocalDate.parse(deadlineBy);
                Deadline newDeadline = new Deadline(deadlineTask, deadlineDate);
                list.add(newDeadline);
                ui.showAddedTask(newDeadline, list.size());
                saveTasks(list);
            } else if (userInput.startsWith("event")) {
                try {
                    // other improvements: no content, empty start and end
                    if (userInput.length() < 7) {
                        throw new DukeException("Remember to add an actual task. Try again!");
                    }
                    if (!(userInput.contains("/from"))) {
                        throw new DukeException("Remember to state the start of your event after a '/from'!");
                    }
                    if (!(userInput.contains("/to"))) {
                        throw new DukeException("Remember to state the end of your event after a '/to'!");
                    }
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                    continue;
                }
                int preIdxFrom = userInput.indexOf("/from");
                int preIdxTo = userInput.indexOf("/to");
                int timeFromStart = preIdxFrom + 6;
                int timeFromEnd = preIdxTo - 1;
                int timeToStart = preIdxTo + 4;
                String eventTask = userInput.substring(6, preIdxFrom - 1);
                String eventFrom = userInput.substring(timeFromStart, timeFromEnd);
                LocalDate eventDateFrom = LocalDate.parse(eventFrom);
                String eventTo = userInput.substring(timeToStart);
                LocalDate eventDateTo = LocalDate.parse(eventTo);
                Event newEvent = new Event(eventTask, eventDateFrom, eventDateTo);
                list.add(newEvent);
                ui.showAddedTask(newEvent, list.size());
                saveTasks(list);
            } else if (userInput.startsWith("delete")) {
                int idx = Integer.parseInt(userInput.substring(7));
                Task removed = list.get(idx - 1);
                list.remove(idx - 1);
                ui.showDeletedTask(removed, list.size());
                saveTasks(list);
            } else if (userInput.startsWith("bye")) {
                ui.showGoodbyeMsg();
                scanner.close();
                break loop;
            } else if (userInput.startsWith("list")) {
                ui.showTaskList(list);
            } else {
                try {
                    throw new DukeException("To add a task, please start with any of these commands: 'todo', 'deadline' or 'event'!");
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                    continue;
                }

            }

        }
    }

    private static void saveTasks(ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : list) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred while saving tasks to file.");
            e.printStackTrace();
        }
    }

    private static void loadTasks(ArrayList<Task> list) {
        try {
            File file = new File("./data/duke.txt");
            // create parent directory if it doesn't exist.
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    System.err.println("Failed to create parent directories.");
                    return;
                }
            }

            // create file if it doesn't exist.
            if (!file.exists() && !file.createNewFile()) {
                System.err.println("Failed to create the file.");
                return;
            }
            Scanner scanner = new Scanner(file);
            // these are for existing tasks !!
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    break;
                }
                Task task = parseTask(line);
                if (task != null) {
                    list.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println("An error occurred while loading tasks from file.");
            e.printStackTrace();
        }
    }

    private static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task = null;

        switch (parts[0]) {
            case "T":
                task = new ToDos(parts[2]);
                break;
            case "D":
                // will receive date as Oct 15 2019
                // need to convert to LocalDate
                LocalDate deadlineBy = convertDate(parts[3]);
                task = new Deadline(parts[2], deadlineBy);
                break;
            case "E":
                // will receive date as Oct 15 2019 - Oct 16 2019
                String[] time = parts[3].split("\\s*-\\s*");
                LocalDate eventFrom = convertDate(time[0]);
                LocalDate eventTo = convertDate(time[1]);
                task = new Event(parts[2], eventFrom, eventTo);
                break;
        }
        if (task != null && parts[1].equals("1")) {
            task.markTask();
        }
        return task;
    }

    public static LocalDate convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
        return LocalDate.parse(date, formatter);
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
