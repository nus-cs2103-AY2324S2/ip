import java.util.Scanner;
public class Duke {
    private static final Task[] tasksArr = new Task[100];

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
        } else {
            addTask(parsedInput);
        }
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println("  " + (i + 1) + "." + tasksArr[i]);
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
            tasksArr[Task.getTaskCount()] = new Deadline(parsedInput[0], parsedInput[1].substring(3));
        } else if (input[0].equalsIgnoreCase("event")) {
            try {
                String[] parsedInput = input[1].split("/", 3);
                tasksArr[Task.getTaskCount()] = new Event(parsedInput[0], parsedInput[1].substring(5), parsedInput[2].substring(3));
            } catch (Exception e){
                throw new DukeException("Please enter event description and time in the correct format\ncorrect format: event *event name* /from *date-time* /to *date-time*");
            }
        } else if (input[0].equalsIgnoreCase("todo")) {
            if (input[1] == null) { throw new DukeException("Please enter task description");}
            tasksArr[Task.getTaskCount()] = new Task(input[1]);
        } else {
            throw new DukeException("Please enter correct instruction.\nHere are valid instructions: list, mark, unmark, deadline, event, todo");
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + tasksArr[Task.getTaskCount()-1].toString());
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
    }

    private static void completeTask(String[] inputTokens) throws DukeException{
        if (inputTokens.length != 2) { throw new DukeException("Please enter the task number that you want to mark as incomplete: ex. unmark 2"); }
        try {
            int index = Integer.parseInt(inputTokens[1]) - 1;
            tasksArr[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasksArr[index]);
        } catch (Exception e) {
            throw new DukeException("Please enter the valid task number");
        }

    }

    private static void uncompleteTask(String[] inputTokens) throws DukeException{
        if(inputTokens.length != 2) { throw new DukeException("Please enter the task number that you want to mark as incomplete: ex. unmark 2"); }
        try {
            int index = Integer.parseInt(inputTokens[1]) - 1;
            tasksArr[index].markAsUndone();
            System.out.println("OK, I've marked this task as not done yet");
            System.out.println(tasksArr[index]);
        } catch(Exception e) {
            throw new DukeException("Please enter the valid task number");
        }
    }

}
