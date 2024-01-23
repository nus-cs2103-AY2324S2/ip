import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // This is the fixed storage of all the tasks
        ArrayList<Task> tasks = new ArrayList<>();


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
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i) == null) {
                        break;
                    }
                    int taskNumber = i + 1;
                    System.out.println(taskNumber + "." + tasks.get(i).toString());
                }
            } else if (words[0].equals("mark")) {
                try {
                    int taskNumber = Parser.markParser(tasks.size());
                    if (tasks.get(taskNumber).getStatusIcon().equals("X")) {
                        throw new myBotException("You cannot mark task which has not been completed!");
                    }
                    tasks.get(taskNumber).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " +  tasks.get(taskNumber).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("I'm not sure which task you wish to mark. Please specify and try again!");
                } catch (myBotException e) {
                    System.out.println(e.getMessage());
                }

            } else if (words[0].equals("unmark")) {
                try {
                    int taskNum = Parser.unmarkParser(tasks.size());
                    // Ensuring that user can only un-mark tasks that are marked as completed
                    if (tasks.get(taskNum).getStatusIcon().equals(" ")) {
                        throw new myBotException("You cannot un-mark task which has not been marked!");
                    }
                    tasks.get(taskNum).uncheckingTask();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " +  tasks.get(taskNum).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("I'm not sure which task you wish to un-mark. Please specify and try again!");
                } catch (myBotException e) {
                    System.out.println(e.getMessage());
                }

            } else if (words[0].equals("todo")) {
                try {
                    ToDos todo = Parser.todoParser();
                    tasks.add(todo);
                    System.out.println("\n" + "Got it. I've added this task:");
                    System.out.println("  " + todo.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS! The command is incomplete. Please provide a task description!");
                }
            } else if (words[0].equals("deadline")) {
                try {
                    Deadline deadlineTask = Parser.deadlineParser();
                    tasks.add(deadlineTask);
                    System.out.println("\n" + "Got it. I've added this task:");
                    System.out.println("  " + deadlineTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (myBotException e) {
                    System.out.println(e.getMessage());
                }

            } else if (words[0].equals("event")){
                try {
                    Events events = Parser.eventsParser();
                    tasks.add(events);
                    System.out.println("\n" + "Got it. I've added this task:");
                    System.out.println("  " + events.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (myBotException e) {
                    System.out.println(e.getMessage());
                }
            } else if (words[0].equals("delete")) {
                try {
                    int taskNumber = Parser.deleteParser(tasks.size());
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " +  tasks.get(taskNumber).toString());
                    tasks.remove(taskNumber);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("I'm not sure which task you wish to delete. Please specify and try again!");
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
