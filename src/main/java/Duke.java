import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Duke {

    private static final String FILE_PATH = "./data/duke.txt";
    public static  void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        loadTasks(list);
        String line = "__________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Floofy");
        System.out.println("What can I do for you?");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        loop:
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.startsWith("mark")) {
                System.out.println(line);
                int idx = Integer.parseInt(userInput.substring(5));
                list.get(idx - 1).markTask();
                System.out.println(line);
                saveTasks(list);
            } else if (userInput.startsWith("unmark")) {
                System.out.println(line);
                int idx = Integer.parseInt(userInput.substring(7));
                list.get(idx - 1).unmarkTask();
                System.out.println(line);
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
                System.out.println(line);
                String todoTask = userInput.substring(5);
                ToDos newTodo = new ToDos(todoTask);
                list.add(newTodo);
                newTodo.addTask(list.size());
                System.out.println(line);
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
                System.out.println(line);
                int preIdx = userInput.indexOf("/by");
                int idx = preIdx + 4;
                String deadlineTask = userInput.substring(9, preIdx - 1);
                String deadlineBy = userInput.substring(idx);
                LocalDate deadlineDate = LocalDate.parse(deadlineBy);
                Deadline newDeadline = new Deadline(deadlineTask, deadlineDate);
                list.add(newDeadline);
                newDeadline.addTask(list.size());
                System.out.println(line);
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
                System.out.println(line);
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
                newEvent.addTask(list.size());
                System.out.println(line);
                saveTasks(list);
            } else if (userInput.startsWith("delete")) {
                System.out.println(line);
                int idx = Integer.parseInt(userInput.substring(7));
                Task removed = list.get(idx - 1);
                list.remove(idx - 1);
                removed.deleteTask(list.size());
                System.out.println(line);
                saveTasks(list);
            } else if (userInput.startsWith("bye")) {
                System.out.println(line);
                System.out.println("BYE BYE! Come back soon~~ YOUR WISH IS MY COMMAND <33");
                System.out.println(line);
                scanner.close();
                break loop;
            } else if (userInput.startsWith("list")) {
                System.out.println(line);
                for (int i = 0; i < list.size(); i++) {
                    String numberedOutput = String.format("%d. %s", i + 1, list.get(i).toString());
                    System.out.println(numberedOutput);
                }
                System.out.println(line);
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
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
