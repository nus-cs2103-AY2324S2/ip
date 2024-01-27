import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private static final FileHandler fileHandler = new FileHandler(FILE_PATH);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        List<Task> tasks = fileHandler.loadTasks();

        String chatBotName = "Nicky";
        pw.println("____________________________________________________________");
        pw.println("Hello! I'm " + chatBotName + "\nWhat can I do for you?");
        pw.println("____________________________________________________________");
        pw.flush();

        while (true) {
            try {
                String currentResponse = br.readLine();
                pw.println("____________________________________________________________");

                if (currentResponse.equals("bye")) {
                    break;
                } else if (currentResponse.startsWith("mark ")) {
                    handleMark(currentResponse, tasks, pw);
                } else if (currentResponse.startsWith("unmark ")) {
                    handleUnmark(currentResponse, tasks, pw);
                } else if (currentResponse.equals("list")) {
                    listTasks(tasks, pw);
                } else if (currentResponse.startsWith("delete ")) {
                    handleDelete(currentResponse, tasks, pw);
                } else if (currentResponse.startsWith("tasks on ")) {
                    LocalDateTime specifiedDateTime = LocalDateTime.parse(currentResponse.substring(9).trim());
                    listTasksOnDate(tasks, specifiedDateTime, pw);
                } else {
                    handleTaskCreation(currentResponse, tasks, pw);
                }
            } catch (DukeException e) {
                pw.println(e.getMessage());
            } catch (Exception e) {
                pw.println("An error occurred: " + e.getMessage());
            }
            pw.println("____________________________________________________________");
            pw.flush();
            fileHandler.saveTasks(tasks);
        }

        br.close();
        pw.println("Bye. Hope to see you again soon!");
        pw.println("____________________________________________________________");
        pw.flush();
        pw.close();
    }

    private static void listTasksOnDate(List<Task> tasks, LocalDateTime dateTime, PrintWriter pw) {
        tasks.stream()
                .filter(task -> task instanceof Deadline && ((Deadline) task).by.equals(dateTime))
                .forEach(pw::println);
    }

    private static void handleDelete(String response, List<Task> tasks, PrintWriter pw) throws DukeException{
        try {
            int index = Integer.parseInt(response.substring(7).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("Task number " + (index + 1) + " does not exist.");
            }
            Task removedTask = tasks.remove(index);
            String taskWord = tasks.size() == 1 ? " task" : " tasks";
            pw.println("Noted. I've removed this task:\n  " + removedTask);
            pw.println("Now you have " + tasks.size() + taskWord + " in the list.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number to delete.");
        }
    }

    private static void handleMark(String response, List<Task> tasks, PrintWriter pw) throws DukeException {
        try {
            int index = Integer.parseInt(response.substring(5).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("Task number " + (index + 1) + " does not exist.");
            }
            Task task = tasks.get(index);
            if (task.isDone()) {
                pw.println("This task is already marked as done:\n  " + task);
            } else {
                task.markAsDone();
                pw.println("Nice! I've marked this task as done:\n  " + task);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number to mark.");
        }
    }

    private static void handleUnmark(String response, List<Task> tasks, PrintWriter pw) throws DukeException {
        try {
            int index = Integer.parseInt(response.substring(7).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("Task number " + (index + 1) + " does not exist.");
            }
            Task task = tasks.get(index);
            if (!task.isDone()) {
                pw.println("This task is already marked as not done:\n  " + task);
            } else {
                task.markAsNotDone();
                pw.println("OK, I've marked this task as not done yet:\n  " + task);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number to unmark.");
        }
    }

    private static void handleTaskCreation(String response, List<Task> tasks, PrintWriter pw) throws DukeException {
        String[] commandParts = response.split(" ", 2);
        String taskType = commandParts[0];

        if(!taskType.equals("todo") && !taskType.equals("deadline") && !taskType.equals("event")) {
            throw new DukeException("Please enter a valid task type.");
        }

        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new DukeException("The description of a " + taskType + " cannot be empty.");
        }

        String description = commandParts[1].trim();
        switch (taskType) {
        case "todo":
            tasks.add(new Todo(description));
            break;
        case "deadline":
            String[] deadlineParts = description.split(" /by ");
            if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                throw new DukeException("The deadline time or task name is missing.");
            }
            tasks.add(new Deadline(deadlineParts[0].trim(), DateTimeUtil.parseDateTime(deadlineParts[1].trim())));
            break;
        case "event":
            String[] eventParts = description.split(" /from ", 2);
            if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
                throw new DukeException("The event time details or event times are missing.");
            }

            String eventDescription = eventParts[0].trim();
            if (eventDescription.isEmpty()) {
                throw new DukeException("The event description is missing.");
            }

            String[] timeParts = eventParts[1].split(" /to ", 2);
            if (timeParts.length < 2) {
                throw new DukeException("Incomplete event time details. Both start and end times are required.");
            }

            String startTimeString = timeParts[0].trim();
            String endTimeString = timeParts[1].trim();
            if (startTimeString.isEmpty()) {
                throw new DukeException("The event start time is missing.");
            }
            if (endTimeString.isEmpty()) {
                throw new DukeException("The event end time is missing.");
            }

            LocalDateTime startTime = DateTimeUtil.parseDateTime(startTimeString);
            LocalDateTime endTime = DateTimeUtil.parseDateTime(endTimeString);
            tasks.add(new Event(eventDescription, startTime, endTime));
            break;
        default:
            throw new DukeException("Please enter a valid task type.");
        }

        String taskWord = tasks.size() == 1 ? " task" : " tasks";
        pw.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        pw.println("Now you have " + tasks.size() + taskWord + " in the list.");
    }

    private static void listTasks(List<Task> tasks, PrintWriter pw) {
        if (tasks.isEmpty()) {
            pw.println("Your task list is empty.");
        } else {
            String taskWord = tasks.size() == 1 ? "task" : "tasks";
            pw.println("Here are the " + taskWord + " in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                pw.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}