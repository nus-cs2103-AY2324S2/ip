import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Solaire {
    private ArrayList<Task> toDoList = new ArrayList<>();
    private Scanner scn = new Scanner(System.in);

    enum UserCommands {
        GREET, BYE, MARK, UNMARK, LIST, TODO, EVENT, DEADLINE, DELETE
    }

    public void startConversation() {
        greet();

        while (true) {
            String input = acceptInput();

            if (input.equals("bye")) {
                break;
            }
            processInput(input);
        }
        waveBye();
    }

    private void lineBreak() {
        System.out.print("--------------------------------------------------\n");
    }

    private void greet() {
        String greetingMessage = "Oh hello there. I'm Solaire of Astora.\n"
                + "The sun is a wondrous body. Like a magnificent father!\n"
                + "If only I could be so grossly incandescent!\n";

        System.out.print(greetingMessage);
        lineBreak();
    }

    private void waveBye() {
        String farewellMessage = "Farewell!\n";
        System.out.print(farewellMessage);
        lineBreak();
        scn.close();
    }

    private String acceptInput() {
        String input = this.scn.nextLine().toLowerCase();
        return input;
    }

    private void processInput(String input) {
        String[] inputCommand = input.split(" ", 2);
        try {
            UserCommands command = UserCommands.valueOf(inputCommand[0].toUpperCase());

            switch (command) {
                case GREET:
                    greet();
                    break;
                case BYE:
                    waveBye();
                    break;
                case MARK:
                    markDone(Integer.parseInt(inputCommand[1]));
                    break;
                case UNMARK:
                    unmarkDone(Integer.parseInt(inputCommand[1]));
                    break;
                case LIST:
                    showList();
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    processTaskCommand(input);
                    break;
                case DELETE:
                    processRemoveFromList(input);
                    break;
                default:
                    System.out.print("Unsupported command pattern\n");
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("I am not yet familiar with these commands");
        }

    }

    private void processTaskCommand(String input) {
        try {
            addToList(parseTaskInput(input));
        } catch (SolaireException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addToList(Task task) {
        if (task != null) {
            toDoList.add(task);
            System.out.println("Added " + task + " to your list");
            lineBreak();
        }
    }

    private void processRemoveFromList(String input) {
        try {
            String[] inputCommand = input.split(" ", 2);
            if (inputCommand.length < 2) {
                throw new SolaireException("Please specify the ID of the task you wish to delete\n");
            } else {
                Integer targetTaskId = Integer.parseInt(inputCommand[1]);
                Task taskToDelete = toDoList.get(targetTaskId - 1);
                toDoList.remove(taskToDelete);
                System.out.println("Removed" + taskToDelete + " from your list");
            }
        } catch (SolaireException e) {
            System.out.println(e.getMessage());
        }

    }

    private Task parseTaskInput(String input) throws SolaireException {
        if (input.startsWith("deadline")) {
            Matcher deadlinePattern = Pattern.compile("^(?i)deadline\\s+(.+)\\s+/by\\s+(\\S+.*)").matcher(input);
            if (deadlinePattern.matches()) {
                String taskName = deadlinePattern.group(1);
                String deadline = deadlinePattern.group(2);

                return new Deadline(taskName, deadline);
            } else {
                throw new SolaireException("Incorrect format: follow deadline format as such: \n"
                        + "deadline <description> /by <time>");
            }
        } else if (input.startsWith("todo")) {
            String[] inputTodo = input.split(" ", 2);
            if (inputTodo.length < 2 || inputTodo[1].trim().replaceAll("^\\s+", "").isEmpty()) {
                throw new SolaireException("The todo task description cannot be empty! Please use this format: \n" +
                        "todo <description>");
            }
            return new Todo(inputTodo[1]);
        } else if (input.startsWith("event")) {
            Matcher eventPattern = Pattern.compile("^(?i)event\\s+(.+)\\s+/from\\s+(\\S+)\\s+/to\\s+(\\S+.*)$")
                    .matcher(input);
            if (eventPattern.matches()) {
                String taskName = eventPattern.group(1);
                String from = eventPattern.group(2);
                String to = eventPattern.group(3);

                return new Event(taskName, from, to);
            } else {
                throw new SolaireException("Incorrect format; follow event format as such:\n"
                        + "event <description> /from <start> /to <end>");
            }
        } else {
            throw new SolaireException("Unable to determine task type");
        }
    }

    private void showList() {
        System.out.print("Your list is as follows:\n " + "-------------------\n");
        for (Task item : toDoList) {
            System.out.println(toDoList.indexOf(item) + 1 + ". " + item.toString());
        }
        lineBreak();
    }

    private void markDone(int id) {
        for (Task item : toDoList) {
            if (item.getId() == id) {
                item.markAsDone();
                System.out.print("Marked item number: " + item.getId() + "\n");
                return;
            }
        }

        System.out.print("Couldn't find task associated with given id\n");
    }

    private void unmarkDone(int id) {
        for (Task item : toDoList) {
            if (item.getId() == id) {
                item.unmarkDone();
                System.out.print("Unmarked  item number: " + item.getId() + "\n");
                return;
            }
        }

        System.out.print("Couldn't find task associated with given id\n");
    }

}