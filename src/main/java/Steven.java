import java.util.Scanner;
import java.util.ArrayList;

public class Steven {

    public static void main(String[] args) {
        String line = "========\n";
        String bootMsg = ("This is Steven!\nHow can I advise?\n");
        String blankFieldMsg = "Just to let you know, I can't accept a task with missing details.\nSteven's advice: Make sure you're leaving no blanks in your instructions!";
        System.out.print(line + bootMsg + line);
        ArrayList<Task> taskList = new ArrayList<Task>();
        boolean exit = false;
        while (!exit) {
            Scanner userInput = new Scanner(System.in);
            String command = userInput.nextLine();
            switch (command.split(" ")[0]) {
                case "list":
                    System.out.println("This is your list so far:");
                    int counter = 1;
                    for (Task t: taskList) {
                        System.out.printf("%d. %s%n", counter, t.toString());
                        counter++;
                    }
                    break;
                case "unmark":
                case "mark":
                    String commandType = command.split(" ")[0];
                    try {
                        int index = Integer.parseInt(command.split(" ")[1]);
                        if (commandType.equals("unmark")) {
                            if (!taskList.get(index - 1).getCompletionStatus()) {
                                System.out.println("Ah, hold on. Seems like this one's still incomplete. If you meant to mark this as complete instead, use \"mark\".\nDo note that this is the current status of the task:");
                            } else {
                                taskList.get(index - 1).toggleCompletion();
                                System.out.println("Sure, I'll mark this as incomplete for the time being.");
                            }
                        } else {
                            if (taskList.get(index - 1).getCompletionStatus()) {
                                System.out.println("Oh, this one's already cleared! Remember that you can use \"unmark\" to mark this as incomplete if that was your intention.\nRegardless, here is the current status of that task:");
                            } else {
                                taskList.get(index - 1).toggleCompletion();
                                System.out.println("As you wish, this will me marked as complete then!");
                            }
                        }
                        System.out.println(taskList.get(index - 1).toString());
                    } catch (NumberFormatException error) {
                        System.out.println("Hmm... Seems like you want me to mark, or unmark something, but you didn't provide a valid number for me to work off.\nSteven's Advice: Use a number instead.");
                    } catch (IndexOutOfBoundsException error) {
                        System.out.println("Ah, a pity... Seems like you don't have that many tasks.\nSteven's advice: Use a number which corresponds to a task number. If you need to know what number corresponds to what task, use \"list\".");
                    }
                    break;
                case "todo":
                    try {
                        String name = command.split(" ")[1];
                        if (name.isEmpty()) {
                            throw new EmptyFieldException();
                        }
                        taskList.add(new Todo(name));
                        System.out.print(line);
                        System.out.println("I see. I shall add the following to the list of tasks:");
                        System.out.println(taskList.get(taskList.size() - 1));
                        System.out.printf("Do bear in mind that you now have %d tasks in the list.%n", taskList.size());
                    } catch (EmptyFieldException | ArrayIndexOutOfBoundsException error) {
                        System.out.println(blankFieldMsg);
                    }
                    break;
                case "deadline":
                    try {
                        String due = command.split("/by ")[1];
                        String name = command.split(" ")[1].split("/by")[0];
                        if (name.isEmpty() || due.isEmpty()) {
                            throw new EmptyFieldException();
                        }
                        taskList.add(new Deadline(name, due));
                        System.out.print(line);
                        System.out.println("I see. I shall add the following to the list of tasks:");
                        System.out.println(taskList.get(taskList.size() - 1));
                        System.out.printf("Do bear in mind that you now have %d tasks in the list.%n", taskList.size());
                    } catch (EmptyFieldException | ArrayIndexOutOfBoundsException error) {
                        System.out.println(blankFieldMsg);
                    }
                    break;
                case "event":
                    try {
                        String start = command.split("/from ")[1].split("/to ")[0];
                        String end = command.split("/to ")[1];
                        String name = command.split(" ")[1].split("/by")[0];
                        if (name.isEmpty() || start.isEmpty() || end.isEmpty()) {
                            throw new EmptyFieldException();
                        }
                        taskList.add(new Event(name, start, end));
                        System.out.print(line);
                        System.out.println("I see. I shall add the following to the list of tasks:");
                        System.out.println(taskList.get(taskList.size() - 1));
                        System.out.printf("Do bear in mind that you now have %d tasks in the list.%n", taskList.size());
                    } catch (EmptyFieldException | ArrayIndexOutOfBoundsException error) {
                        System.out.println(blankFieldMsg);
                    }
                    break;
                case "bye":
                    exit = true;
                    break;
                    //TODO: add functionality to adding different types of things
                default:
                    System.out.println("Hm, this doesn't seem like something can do for you. Try something else?");
            }
            System.out.print(line);
        }

        System.out.println("I'll see you soon then!\n" + line);
    }
}
