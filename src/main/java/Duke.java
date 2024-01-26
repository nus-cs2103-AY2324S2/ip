import java.util.Scanner;
import java.util.ArrayList;

class DukeException extends Exception {
//    public DukeException (String s)
//    {
//        // Call constructor of parent Exception
//        super(s);
//    }


}
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String Greeting = "____________________________________________________________\n" +
                " Hello! I'm VICTOR\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String Ending =
                "____________________________________________________________\n"+
                        " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        String barrier = "____________________________________________________________";
        ArrayList<Task> todoList = new ArrayList<Task>();
        System.out.println(Greeting);
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            String[] inputList = userInput.split(" ", 2);
            if (userInput.equals("list")) {
                System.out.println(barrier);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println(i+1 + "." + todoList.get(i).toString());
                }
                System.out.println(barrier);
            } else if (inputList[0].equals("mark")) {
                try {
                    int position = Integer.parseInt(inputList[1]);
                    Task currentTask = todoList.get(position-1);
                    System.out.println(barrier);
                    System.out.println("Nice! I've marked this task as done:");
                    todoList.get(position-1).markAsDone();
                    System.out.println(currentTask.toString());
                    System.out.println(barrier);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(barrier);
                    System.out.println("Check how many items are in the list again. The number you gave is too high");
                    System.out.println("Can't mark an item not in the list");
                    System.out.println("The format to mark a task is: mark (task list number)");
                    System.out.println(barrier);
                } catch (NumberFormatException e) {
                    System.out.println(barrier);
                    System.out.println("Sorry, I'm only smart enough to find the task based on numbers.");
                    System.out.println("Please give a number. If you refuse, too bad, this is all I can do.");
                    System.out.println(barrier);
                }
            } else if (inputList[0].equals("unmark")) {
                try {
                    int position = Integer.parseInt(inputList[1]);
                    System.out.println(barrier);
                    System.out.println("OK, I've marked this task as not done yet:");
                    todoList.get(position-1).unmarkAsDone();
                    System.out.println(todoList.get(position-1).toString());
                    System.out.println(barrier);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(barrier);
                    System.out.println("Check how many items are in the list again. The number you gave is too high.");
                    System.out.println("Can't unmark an item not in the list");
                    System.out.println("The format to unmark a task is: unmark (task list number)");
                    System.out.println(barrier);
                } catch (NumberFormatException e) {
                    System.out.println(barrier);
                    System.out.println("Sorry, I'm only smart enough to find the task based on numbers.");
                    System.out.println("Please give a number. If you refuse, too bad, this is all I can do.");
                    System.out.println(barrier);
                }
            } else if (inputList[0].equals("todo")) {
                try {
                    Task userTask = new Todo(inputList[1]);
                    todoList.add(userTask);
                    System.out.println(barrier);
                    System.out.println(userTask.toString());
                    System.out.println("Now you have " + todoList.size() + " tasks in the list.");
                    System.out.println(barrier);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(barrier);
                    System.out.println("Sorry pal, but your description is empty.");
                    System.out.println("Please redo the command and remember to add a description of the action");
                    System.out.println("The format to schedule a todo task is: todo (Description)");
                    System.out.println(barrier);
                }
            } else if (inputList[0].equals("deadline")) {
                try {
                    String[] differentParts = inputList[1].split("/");
                    String[] deadLine = differentParts[1].split(" ", 2);
                    Task userTask = new Deadline(differentParts[0],deadLine[1]);
                    todoList.add(userTask);
                    System.out.println(barrier);
                    System.out.println(userTask.toString());
                    System.out.println("Now you have " + todoList.size() + " tasks in the list.");
                    System.out.println(barrier);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(barrier);
                    System.out.println("Oh, you forgot to indicate when is the deadline or maybe you forgot the description.");
                    System.out.println("Please redo the command and remember to add the necessary information.");
                    System.out.println("The format to schedule a deadline is: deadline (Description) /by (Deadline Date + time)");
                    System.out.println(barrier);
                }
            } else if (inputList[0].equals("event")) {
                try {
                    String[] differentParts = inputList[1].split("/");
                    String[] startDate = differentParts[1].split(" ", 2);
                    String[] endDate = differentParts[2].split(" ", 2);
                    Task userTask = new Event(differentParts[0],startDate[1],endDate[1]);
                    todoList.add(userTask);
                    System.out.println(barrier);
                    System.out.println(userTask.toString());
                    System.out.println("Now you have " + todoList.size() + " tasks in the list.");
                    System.out.println(barrier);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(barrier);
                    System.out.println("You forgot to provide either a description, an start date or an end date for this event.");
                    System.out.println("Sorry, but mind reading is not installed in me yet.");
                    System.out.println("Please redo the command and remember to add the necessary information.");
                    System.out.println("The format to schedule a event is: event (Description) /from (Start date + time) /to (End date + time)");
                    System.out.println(barrier);
                }

            } else if (inputList[0].equals("delete")) {
                try {
                    int position = Integer.parseInt(inputList[1]);
                    Task chosenTask = todoList.get(position-1);
                    System.out.println(barrier);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(chosenTask.toString());
                    todoList.remove(position-1);
                    System.out.println("Now you have " + todoList.size() + " tasks in the list.");
                    System.out.println(barrier);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(barrier);
                    System.out.println("The number you gave exceeds how many items is in the list.");
                    System.out.println("Can't delete an item not in the list. Please try again.");
                    System.out.println("The format to delete a task is: delete (task list number)");
                    System.out.println(barrier);
                } catch (NumberFormatException e) {
                    System.out.println(barrier);
                    System.out.println("Sorry, I'm only smart enough to find the task based on numbers.");
                    System.out.println("Please give a number. If you refuse, too bad, this is all I can do.");
                    System.out.println("The format to delete a task is: delete (task list number)");
                    System.out.println(barrier);
                }

            }
//            else {
//                Task userTask = new Task(inputList[0] + " " + inputList[1]);
//                todoList.add(userTask);
//                System.out.println(barrier);
//                System.out.println("added: " + inputList[0] + " " + inputList[1]);
//                System.out.println("Now you have " + todoList.size() + " task(s) in the list.");
//                System.out.println(barrier);
//            }
        }
        System.out.println(Ending);
        sc.close();

    }
}
