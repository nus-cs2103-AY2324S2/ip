import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    public static final List<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) {

        String horiLine = "____________________________________________________________\n";

        String greet = "Hello! I'm Dino\n"
                + "What can I do for you?\n"
                + horiLine;
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();


        while(!input.equalsIgnoreCase("bye")) {
            System.out.println(horiLine);
            try {
                processInput(input);
            } catch(DukeException e) {
                System.out.println("Invalid Instruction: " + e.getMessage());
            }
            System.out.println(horiLine);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void processInput(String input) throws DukeException {
        String[] parsedInput = input.split(" ", 2);
        if (parsedInput[0].equalsIgnoreCase("list")) {
            listTasks();
        } else if (parsedInput[0].equalsIgnoreCase("mark")) {
            completeTask(parsedInput);
        } else if (parsedInput[0].equalsIgnoreCase("unmark")) {
            uncompleteTask(parsedInput);
        } else if (parsedInput[0].equalsIgnoreCase("delete")) {
            deleteTask(parsedInput);
        } else {
            addTask(parsedInput);
        }
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasksList.get(i));
        }
    }
    private static void addTask(String[] input) throws DukeException{
        if (input.length < 2) {
            throw new DukeException("Please enter instruction in the correct format" +
                    "\nCheck if you have provide the task description" +
                    "\nHere are valid instructions: list, mark, unmark, deadline, event, todo");
        }
        if (input[0].equalsIgnoreCase("deadline")) {
            String[] parsedInput = input[1].split("/", 2);
            if (parsedInput.length != 2) {
                throw new DukeException("Please enter task description and deadline \n correct format: deadline *task description* /by *deadline*");}
            tasksList.add(new Deadline(parsedInput[0], parsedInput[1].substring(3)));
        } else if (input[0].equalsIgnoreCase("event")) {
            try {
                String[] parsedInput = input[1].split("/", 3);
                tasksList.add(new Event(parsedInput[0], parsedInput[1].substring(5), parsedInput[2].substring(3)));
            } catch (Exception e){
                throw new DukeException("Please enter event description and time in the correct format\ncorrect format: event *event name* /from *date-time* /to *date-time*");
            }
        } else if (input[0].equalsIgnoreCase("todo")) {
            if (input[1] == null) { throw new DukeException("Please enter task description");}
            tasksList.add(new Task(input[1]));
        } else {
            throw new DukeException("Please enter correct instruction.\nHere are valid instructions: list, mark, unmark, deadline, event, todo");
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + tasksList.get(tasksList.size()-1).toString());
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
    }

    private static void completeTask(String[] inputTokens) throws DukeException{
        if (inputTokens.length != 2) { throw new DukeException("Please enter the task number that you want to mark as incomplete: ex. unmark 2"); }
        try {
            int index = Integer.parseInt(inputTokens[1]) - 1;
            tasksList.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasksList.get(index).toString());
        } catch (Exception e) {
            throw new DukeException("Please enter the valid task number");
        }

    }

    private static void uncompleteTask(String[] inputTokens) throws DukeException{
        if(inputTokens.length != 2) { throw new DukeException("Please enter the task number that you want to mark as incomplete: ex. mark 2"); }
        try {
            int index = Integer.parseInt(inputTokens[1]) - 1;
            tasksList.get(index).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet");
            System.out.println(tasksList.get(index).toString());
        } catch(Exception e) {
            throw new DukeException("Please enter the valid task number");
        }
    }

    private static void deleteTask(String[] inputTokens) throws DukeException{
        if(inputTokens.length != 2) { throw new DukeException("Please enter the task number that you want to delete: ex. delete 2"); }
        try {
            int index = Integer.parseInt(inputTokens[1]) - 1;
            String task = tasksList.get(index).toString();
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            tasksList.remove(index);
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        } catch(Exception e) {
            throw new DukeException("Please enter the valid task number");
        }
    }

}
