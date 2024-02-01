import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String botName = "Corgi";
    ArrayList<Task> tasks = new ArrayList<>();
    int numOfTasks = 0;
    private String filePath = "./taskList.txt";
    public Storage database;
    public Duke() throws IOException{
        this.database = new Storage(filePath);
        this.tasks = this.database.load();
        this.numOfTasks = this.tasks.size();
    }
    public void greet(){
        String greetMessage = String.format(
                "____________________________________________________________\n" +
                        " Hello! I'm %s\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n", botName);
        System.out.println(greetMessage);
    }
    public void goodbye(){
        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(exitMessage);
    }
    public void echo(String input){
        System.out.println("____________________________________________________________\n" +
                input +
                "\n____________________________________________________________\n");
    }
    public static String getTaskName(String task, String input) throws DukeException {
        if (task.equalsIgnoreCase("todo")) {
            if (input.length() < 6) {
                throw new DukeException("Please enter a task name for Todo!! >.<");
            }
            String taskName = input.substring(5);
            return taskName;
        } else if (task.equalsIgnoreCase("deadline")) {
            int endIndex = input.indexOf("/by");
            if (input.length() < 10 || endIndex == -1) {
                throw new DukeException("Please enter a task name for Deadline!! >.<");
            }
            String taskName = input.substring(9, endIndex);
            return taskName;
        }else if (task.equalsIgnoreCase("event")) {
            int endIndex = input.indexOf("/from");
            if (input.length() < 7 || endIndex == -1) {
                throw new DukeException("Please enter a task name for Event!! >.<");
            }
            String taskName = input.substring(6, endIndex);
            return taskName;
        } else {
            throw new DukeException("Please use one of the 3 tasks!! >.<");
        }
    }
    public static String getStartDate(String input) throws DukeException {
        int startIndex = input.indexOf("/from") + 6;
        int endIndex = input.indexOf("/to");

        if (startIndex == input.length() || startIndex == 5) {
            throw new DukeException("Please use the keyword /from for your event! >.<");
        } else if (startIndex > input.length()) {
            throw new DukeException("Please enter a start date for your event! >.<");
        } else if (endIndex == -1) {
            throw new DukeException("Please use the keyword /to for your event! >.<");
        } else if (startIndex > endIndex) {
            throw new DukeException("Please enter an end date for your event! >.<");
        }
        return input.substring(startIndex, endIndex);
    }
    public static String getEndDate(String task, String input) throws DukeException {
        String startWord = task.equalsIgnoreCase("deadline") ? "/by" : "/to";
        int startIndex = input.indexOf(startWord) + startWord.length() + 1;
        if (startIndex > input.length()) {
            throw new DukeException(String.format("Please enter an end date for your %s!", task));
        }
        return input.substring(startIndex);
    }
    public void addToList(String input) throws DukeException{
        Task addedTask = null;
        String[] inputArr = input.split(" ");
        String commandWord = inputArr[0];
        try {
            if (commandWord.equalsIgnoreCase("todo")) {
                addedTask = new Todo(getTaskName(commandWord, input));
            } else if (commandWord.equalsIgnoreCase("deadline")) {
                addedTask = new Deadline(getTaskName(commandWord, input), getEndDate(commandWord, input));
            } else if (commandWord.equalsIgnoreCase("event")) {
                addedTask = new Event(getTaskName(commandWord, input), getStartDate(input), getEndDate(commandWord, input));
            } else {
                throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            tasks.add(addedTask);
            numOfTasks++;
            String message = String.format(
                    "____________________________________________________________\n" +
                            " Got it. I've added this task:\n" +
                            "  [%s][%s] %s\n" +
                            " Now you have %d tasks in the list.\n" +
                            "____________________________________________________________\n",
                    addedTask.getTaskType(), addedTask.getStatusIcon(),
                    addedTask.toString(), numOfTasks);
            System.out.println(message);
        } catch (DukeException error) {
            String errorMessage = String.format(
                    "____________________________________________________________\n" +
                    "%s\n" +
                    "____________________________________________________________\n", error.toString());
            System.out.println(errorMessage);
        } catch (DateTimeException dte) {
            String errorMessage = String.format(
                    "____________________________________________________________\n" +
                            "Please follow the format for date and time (d/M/yyyy HHmm).\n" +
                            "____________________________________________________________\n");
            System.out.println(errorMessage);
        }
    }
    public void printList(){
        System.out.println("____________________________________________________________\n" +
                " Here are the tasks in your list:\n");
        for (int i = 0; i < numOfTasks; i++) {
            String currentTask = String.format("%d.[%s][%s] %s",
                    i + 1, tasks.get(i).getTaskType(),
                    tasks.get(i).getStatusIcon(), tasks.get(i).toString());
            System.out.println(currentTask);
        }
        System.out.println("____________________________________________________________\n");
    }
    public void markAsDoneOrUndone(int taskNum, boolean markDone){
        if(taskNum < 1 || taskNum > 100){
            System.out.println("Invalid task number entered.");
            return;
        }
        Task targetTask = tasks.get(taskNum - 1);
        if (markDone) {
            targetTask.setAsDone();
            String message = String.format(
                    "____________________________________________________________\n" +
                            " Nice! I've marked this task as done:\n" +
                            "  [%s][%s] %s\n" +
                    "____________________________________________________________\n",
                    targetTask.getTaskType(), targetTask.getStatusIcon(),
                    targetTask.getDescription());
            System.out.println(message);
        } else {
            targetTask.setAsNotDone();
            String message = String.format(
                    "____________________________________________________________\n" +
                            " OK, I've marked this task as not done yet:\n" +
                            "  [%s][%s] %s\n" +
                            "____________________________________________________________\n",
                    targetTask.getTaskType(), targetTask.getStatusIcon(),
                    targetTask.getDescription());
            System.out.println(message);
        }
    }
    public void deleteTask (int index) throws DukeException{
        if (numOfTasks < index){
            throw new DukeException("Invalid task index to delete!");
        }
        Task taskToBeDeleted = tasks.get(index - 1);
        tasks.remove(index - 1);
        numOfTasks--;
        String deleteMessage = String.format(
                "____________________________________________________________\n" +
                        " Noted. I've removed this task:\n" +
                        "  [%s][%s] %s\n" +
                        " Now you have %d tasks in the list.\n" +
                        "____________________________________________________________\n",
                taskToBeDeleted.getTaskType(), taskToBeDeleted.getStatusIcon(),
                taskToBeDeleted.toString(), numOfTasks);
        System.out.println(deleteMessage);
    }
    public static void main(String[] args) throws DukeException, IOException {
        Duke myChatBot = new Duke();
        myChatBot.greet();
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true){
            input = sc.nextLine();
            if(input.equalsIgnoreCase("bye")){
                break;
            } else if (input.equalsIgnoreCase("list")) {
                myChatBot.printList();
            } else if (input.length() >= 6 && input.substring(0,4).equalsIgnoreCase("mark")) {
                myChatBot.markAsDoneOrUndone(Character.getNumericValue(input.charAt(5)), true);
            } else if (input.length() >= 8 && input.substring(0,6).equalsIgnoreCase("unmark")) {
                myChatBot.markAsDoneOrUndone(Character.getNumericValue(input.charAt(7)), false);
            } else if (input.length() >= 8 && input.substring(0,6).equalsIgnoreCase("delete")) {
                String position = input.substring(7);
                Integer i = null;
                try {
                    i = Integer.parseInt(position);
                    myChatBot.deleteTask(i);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid index to delete!");
                } catch (DukeException de) {
                    System.out.println(de.toString());
                }
            } else {
                myChatBot.addToList(input);
            }
        }
        myChatBot.database.save(myChatBot.tasks);
        myChatBot.goodbye();
    }
}
