import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
public class Solaire {
    private ArrayList<Task> toDoList = new ArrayList<>();
    private Scanner scn = new Scanner(System.in);
    
    enum UserCommands {
        GREET, BYE, MARK, UNMARK, LIST, TODO, EVENT, DEADLINE ,EXIT
    }
    public void startConversation() {
        greet();

        while(true) {
            String input = acceptInput();
            
            if (input.equals("bye")) {
                break;
            }
            processInput(input);
        }
        scn.close();
        waveBye();
    }

    private void lineBreak() {
        System.out.println("--------------------------------------------------\n");
    }
    
    private void greet() {
        String greetingMessage = "Oh hello there. I'm Solaire of Astora.\n"
        + "The sun is a wondrous body. Like a magnificent father!\n"
        + "If only I could be so grossly incandescent!\n";

        System.out.println(greetingMessage);
        lineBreak();
    }

    private void waveBye() {
        String farewellMessage = "Farewell!";
        System.out.println(farewellMessage);
        lineBreak();
    }

    private String acceptInput() {
        String input = this.scn.nextLine().toLowerCase();
        return input;
    }

    private void processInput(String input) {
        String[] inputCommand = input.split(" ", 2);
        try {
            UserCommands command = UserCommands.valueOf(inputCommand[0].toUpperCase());

        switch(command) {
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
                addToList(parseTaskInput(input));
                break;
            default:
                System.out.println("Invalid command. Please follow the correct format!");
                break;
        }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid command. Please follow the correct format!");
        }
    }         

    private void addToList(Task task) {
        if (task != null) {
            toDoList.add(task);
            System.out.println("Added " + task + " to your list");
            lineBreak();
        }
    }

    private Task parseTaskInput(String input) {
        Matcher deadlinePattern = Pattern.compile("^(?i)deadline\\s+(.+)\\s+/by\\s+(\\S+.*)").matcher(input);
        Matcher todoPattern = Pattern.compile("^(?i)todo\\s+(.+)$").matcher(input);
        Matcher eventPattern = Pattern.compile("^(?i)event\\s+(.+)\\s+/from\\s+(\\S+)\\s+/to\\s+(\\S+.*)$").matcher(input);

        if (deadlinePattern.matches()) {
            String taskName = deadlinePattern.group(1);
            String deadline = deadlinePattern.group(2);

            return new Deadline(taskName, deadline);
        } else if (todoPattern.matches()){
            String taskName = todoPattern.group(1);
            
            return new Todo(taskName);
        } else if (eventPattern.matches()) {
            String taskName = eventPattern.group(1);
            String from = eventPattern.group(2);
            String to = eventPattern.group(3);

            return new Event(taskName, from, to);
        } else {
            System.out.println("Incorrect format. Task not added");
            return null;
        }

        
    }

    private void showList() {
        System.out.println("Your list is as follows:\n " + "-------------------");
        for (Task item : toDoList) {
            System.out.println(toDoList.indexOf(item)+1 + ". " + item.toString());
        }
        lineBreak();
    }

    private void markDone(int id) {
        for (Task item : toDoList) {
            if (item.getId() == id) {
                item.markAsDone();
                System.out.println("Marked item number: " + item.getId());
                return;
            }
        }

        System.out.println("Couldn't find task associated with given id");
    }

    private void unmarkDone(int id) {
        for (Task item : toDoList) {
            if (item.getId() == id) {
                item.unmarkDone();
                System.out.println("Unmarked  item number: " + item.getId());
                return;
            }
        }

        System.out.println("Couldn't find task associated with given id");
    }


}