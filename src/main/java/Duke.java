import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // This is the fixed storage of all the tasks
        Task[] tasks = new Task[100];
        int index = 0;

        String greetingMsg = "Hello! I'm PingMeBot\n" + "What can I do for you?";
        String exitMsg = "Bye. Hope to see you again soon!";
        System.out.println(greetingMsg);

        while (true) {
            String userInput = sc.nextLine();
            String[] words = userInput.split(" ");
            Parser Parser = new Parser(userInput);

            if (userInput.equals("bye")) {
                System.out.println("\n" + exitMsg);
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    int taskNumber = i + 1;
                    System.out.println(taskNumber + "." + tasks[i].toString());
                }
            } else if (words[0].equals("mark")) {
                try {
                    int taskNumber = Parser.markParser(index);
                    tasks[taskNumber].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " +  tasks[taskNumber].toString());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("I'm not sure which task you wish to mark. Please specify and try again!");
                } catch (myBotException e) {
                    System.out.println(e.getMessage());
                }

            } else if (words[0].equals("unmark")) {
                try {
                    int taskNum = Parser.unmarkParser(index);
                    // Ensuring that user can only un-mark tasks that are marked as completed
                    if (tasks[taskNum].getStatusIcon().equals(" ")) {
                        throw new myBotException("You cannot un-mark task which has not been marked!");
                    }
                    tasks[taskNum].uncheckingTask();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " +  tasks[taskNum].toString());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("I'm not sure which task you wish to un-mark. Please specify and try again!");
                } catch (myBotException e) {
                    System.out.println(e.getMessage());
                }

            } else if (words[0].equals("todo")) {
                try {
                    ToDos todo = Parser.todoParser();
                    tasks[index] = todo;
                    System.out.println("\n" + "Got it. I've added this task:");
                    System.out.println("  " + todo.toString());
                    index++;
                    System.out.println("Now you have " + index + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS! The command is incomplete. Please provide a task description!");
                }
            } else if (words[0].equals("deadline")) {
                try {
                    Deadline deadlineTask = Parser.deadlineParser();
                    tasks[index] = deadlineTask;
                    System.out.println("\n" + "Got it. I've added this task:");
                    System.out.println("  " + deadlineTask.toString());
                    index++;
                    System.out.println("Now you have " + index + " tasks in the list.");
                } catch (myBotException e) {
                    System.out.println(e.getMessage());
                }

            } else if (words[0].equals("event")){
                try {
                    Events events = Parser.eventsParser();
                    tasks[index] = events;
                    System.out.println("\n" + "Got it. I've added this task:");
                    System.out.println("  " + events.toString());
                    index++;
                    System.out.println("Now you have " + index + " tasks in the list.");
                } catch (myBotException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                System.out.println("OOPS! I'm sorry, but I don't know what that means :'(");
            }
        }
    }
}
