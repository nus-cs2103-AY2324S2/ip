import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TalkingJohn {
    private final String filePath;
    private final List<String> invalidInputs = Arrays.asList("todo", "deadline", "event", "mark", "unmark", "delete");

    private final List<Task> taskArr = new ArrayList<>();

    public TalkingJohn(String filePath) {
        this.filePath = filePath;
    }

    private void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskArr) {
                writer.write(task.toString() + "\n");
            }
            System.out.println("Tasks saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String description = line.substring(7);
                String letter = line.substring(1, 2);
                String markCheck = line.substring(4, 5);

                Task task = null;

                if (letter.equals("T")) {
                    task = new Todo(description);

                    if (markCheck.equals("X")) {
                        task.mark();
                    }
                } else if (letter.equals("D")) {
                    int openingParenthesisIndex = description.indexOf('(');
                    int endingParenthesisIndex = description.indexOf(')');

                    String content = description.substring(0, openingParenthesisIndex);
                    String timing = description.substring(openingParenthesisIndex + 2, endingParenthesisIndex);

                    task = new Deadline(content, timing);

                    if (markCheck.equals("X")) {
                        task.mark();
                    }
                } else if (letter.equals("E")) {
                    int openingParenthesisIndex = description.indexOf('(');
                    int endingParenthesisIndex = description.indexOf(')');

                    String content = description.substring(0, openingParenthesisIndex);
                    String timing = description.substring(openingParenthesisIndex + 2, endingParenthesisIndex);

                    String[] timings = timing.split("to:", 2);

                    task = new Event(content, timings[0], "  " + timings[1]);

                    if (markCheck.equals("X")) {
                        task.mark();
                    }
                } else {
                    System.out.println("INVALID STORAGE DATA");
                }
                taskArr.add(task);
            }
            System.out.println("Tasks loaded from " + filePath);
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }


    public int convertInt(String input) {
        return Integer.parseInt(input.split(" ", 2)[1]);
    }

    public void greeting() {
        System.out.println("Hello, I am TalkingJohn\nWhat can I do for you?\n");
    }

    public void goodbye() {
        System.out.println("Bye, hope to see you soon");
    }

    public void emptyInput(String input) {
        System.out.println("OOPS!!! " + input + " cannot be empty.");
    }

    public void printAddTask(Task task) {
        System.out.println("Got it. I've added this task:\n" +
                task + "\nNow you have " + taskArr.size() + " tasks in the list.");
    }

    public void invalidInput() {
        System.out.println("HMM? This is an activity planner. Please repeat \uD83E\uDD72");
    }

    public void run() {
        loadTasksFromFile();
        try (Scanner scanner = new Scanner(System.in)) {
            greeting();

            while (true) {
                String input = scanner.hasNextLine() ? scanner.nextLine() : "";

                if (Objects.equals(input, "bye")) {
                    goodbye();
                    saveTasksToFile();
                    break;
                }

                if (invalidInputs.contains(input)) {
                    emptyInput(input);
                    continue;
                }

                if (Objects.equals(input, "list")) {
                    if (taskArr.size() > 0) {
                        for (int i = 0; i < taskArr.size(); i++) {
                            System.out.println((i + 1) + ". " + taskArr.get(i) + "\n");
                        }
                    } else {
                        emptyInput("list");
                    }
                } else if (input.startsWith("delete") && input.length() > 6) {
                    try {
                        int i = convertInt(input);
                        Task deleted = taskArr.remove(i - 1);
                        System.out.println("Noted. I've removed this task:\n" + deleted + "\nNow you have " + taskArr.size() + " tasks in the list.");
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! Invalid delete expression.");
                    }
                } else if (input.startsWith("mark") && input.length() > 4) {
                    try {
                        int i = convertInt(input);
                        Task taskToMark = taskArr.get(i - 1);
                        taskToMark.mark();
                        System.out.println("Nice! I've marked this task as done:\n" + taskToMark);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! Invalid mark expression.");
                    }
                } else if (input.startsWith("unmark") && input.length() > 6) {
                    try {
                        int i = convertInt(input);
                        Task taskToUnmark = taskArr.get(i - 1);
                        taskToUnmark.unMark();
                        System.out.println("OK, I've marked this task as not done yet:\n" + taskToUnmark);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! Invalid unmark expression.");
                    }
                } else if (input.startsWith("todo") && input.length() > 4 && input.charAt(4) == ' ') {
                    String whatToDo = input.split(" ", 2)[1];
                    Todo toDo = new Todo(whatToDo);
                    taskArr.add(toDo);
                    printAddTask(toDo);
                } else if (input.startsWith("deadline") && input.length() > 8) {
                    try {
                        String[] parts = input.split(" ", 2);
                        String buffer = parts[1];
                        String[] secPart = buffer.split("/", 2);
                        Deadline deadline = new Deadline(secPart[0], secPart[1]);
                        taskArr.add(deadline);
                        printAddTask(deadline);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OH NO! It seems like the format is wrong. Did you include at least 1 '/' in the description?");
                    }
                } else if (input.startsWith("event") && input.length() > 5) {
                    try {
                        String[] parts = input.split(" ", 2);
                        String buffer = parts[1];
                        String[] secPart = buffer.split("/", 3);
                        Event event = new Event(secPart[0], secPart[1], secPart[2]);
                        taskArr.add(event);
                        printAddTask(event);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OH NO! It seems like the format is wrong. Did you include at least 2 '/' in the description?");
                    }
                } else {
                    invalidInput();
                }
            }
        }
    }

    public static void main(String[] args) {
        TalkingJohn chatBot = new TalkingJohn("./data/TalkingJohn.txt");
        chatBot.run();
    }
}
