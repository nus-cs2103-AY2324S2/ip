import java.util.Scanner;
import java.util.ArrayList;

public class Steven {

    public static void main(String args[]) {
        String line = "========\n";
        String bootMsg = ("This is Steven!\nHow can I advise?\n");
        System.out.println(line + bootMsg + line);
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
                        System.out.println(String.format("%d. %s", counter, t.toString()));
                        counter++;
                    }
                    break;
                case "unmark":
                case "mark":
                    String commandType = command.split(" ")[0];
                    try {
                        int index = Integer.valueOf(command.split(" ")[1]);
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
                        System.out.println("Hmm... Seems like you want me to mark, or unmark something, but you didn't provide a valid number for me to work off. Try again, perhaps?");
                    } catch (IndexOutOfBoundsException error) {
                        System.out.println("Ah, a pity... Seems like you don't have that many tasks. Try again, perhaps?");
                    }
                    break;
                case "bye":
                    exit = true;
                    break;
                default:
                    Task newTask = new Task(command);
                    taskList.add(newTask);
                    System.out.println("I've added this task to the list: " + command);
                    System.out.println("If you want to see what's on the list so far, just type \"list\"!");
            }
            System.out.println(line);
        }

        System.out.println("I'll see you soon then!\n" + line);
    }
}
