import java.util.Scanner;
import java.util.ArrayList;

public class Steven {

    public static void main(String[] args) {
        String line = "========\n";
        String bootMsg = ("This is Steven!\nHow can I advise?\n");
        String blankFieldMsg = "Just to let you know, I can't accept a task with missing details.\nSteven's advice: Make sure you're leaving no blanks in your instructions!";
        System.out.print(line + bootMsg + line);
        System.out.println("Steven's advice: Don't know what commands I understand? Use \"help\"!");
        System.out.print(line);
        ArrayList<Task> taskList = new ArrayList<>();
        boolean exit = false;
        while (!exit) {
            Scanner userInput = new Scanner(System.in);
            while (userInput.hasNextLine()) {
                String command = userInput.nextLine();
                switch (command.split(" ")[0]) {
                    case "list":
                        System.out.println("This is your list so far:");
                        int counter = 1;
                        for (Task t : taskList) {
                            System.out.printf("%d. %s%n", counter, t.toString());
                            counter++;
                        }
                        break;
                    case "unmark":
                    case "mark":
                        String commandType = command.split(" ")[0];
                        try {
                            int index = Integer.parseInt(command.split(" ", 2)[1]);
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
                            String name = command.split(" ", 2)[1];
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
                            String name = command.split(" ", 2)[1].split("/by")[0];
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
                            String name = command.split(" ", 2)[1].split("/from")[0];
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
                    case "delete":
                        try {
                            int index = Integer.parseInt(command.split(" ", 2)[1]);
                            Task removedItem = taskList.get(index);
                            taskList.remove(index);
                            System.out.println(line + "Very well, the following item has been removed from the list:\n" + removedItem.toString() + "\n");
                            System.out.println("For the sake of completeness, this is the current list, do take note if any of your items have been moved around in order.");
                            int counter1 = 1;
                            for (Task t : taskList) {
                                System.out.printf("%d. %s%n", counter1, t.toString());
                                counter1++;
                            }
                        } catch (NumberFormatException error) {
                            System.out.println("Oh, I can't  delete that - I need a number of an item in the list to delete it.\nSteven's Advice: Use a number instead.");
                        } catch (IndexOutOfBoundsException error) {
                            System.out.println("Apologies, you don't have a task of this number, so I can't delete it.\nSteven's advice: Use a number which corresponds to a task number. If you need to know what number corresponds to what task, use \"list\".");
                        }
                        break;
                    case "help":
                        System.out.println("The following are commands that I recgonise, and their respective formats:");
                        System.out.println("bye - terminates the program");
                        System.out.println("list - provides a list of events that you have added prior");
                        System.out.println("mark (x) - marks the xth item on the list as complete. Note that an item marked complete cannot be marked complete again.");
                        System.out.println("unmark (x) - marks the xth item on the list as incomplete. Note than an item marked incomplete cannot be marked incomplete again.");
                        System.out.println("todo (item) - adds a todo item to the list.");
                        System.out.println("deadline (item) /by (date1) - adds a deadline item to the list which is due on date1.");
                        System.out.println("event (item) /from (date1) /to (date2) - adds an event item to the list which begins on date1 and ends on date2.");
                        System.out.println("delete (x) - delete the xth item from the list. Do note that this may affect the positioning of some of the items.");
                        break;
                    case "bye":
                        exit = true;
                        break;
                    default:
                        System.out.println("Hm, this doesn't seem like something can do for you. Try something else?\nSteven's advice: try typing \"help\" to see what commands are available.");
                }
                System.out.print(line);
            }
        }

        System.out.println("I'll see you soon then!\n" + line);
    }
}
