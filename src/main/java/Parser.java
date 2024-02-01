import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exceptions.ExcessiveArgException;
import exceptions.IllogicalDateException;
import exceptions.IncompatibleMarkException;
import exceptions.InsufficientArgException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Parser {
    private static final String line = "========\n";
    private static final String corrupted = "Oh dear, looks like the file used to handle the data "
            + "I'm supposed to store is corrupted..."
            + "\nSteven's Advice: You may need to re-create the file!";
    private static final String formatError = "My, it would appear as though you didn't"
            + "format your instruction properly!\n";
    private static final String dateErr = "Ah, this one might be slightly complicated - "
            + "I need your date in the format of \"yyyy-mm-dd\", "
            + "and I'm quite strict with this, unfortunately."
            + "\nSteven's advice: Follow the format, append your days/months with zero as necessary! "
            + "For example, \"03\" is accepted for the month of March, but not \"3\"!";
    private final UserInput userInput;
    private TaskList taskList;
    Parser(UserInput userInput, TaskList taskList) {
        this.userInput = userInput;
        this.taskList = taskList;
    }

    public boolean processInput() {
        switch (userInput.getInputName()) {
        case "list":
            System.out.println("This is your list so far:");
            taskList.printList();
            break;
        case "unmark":
            try {
                if (userInput.arg1Empty()) {
                    throw new InsufficientArgException();
                }
                if (!userInput.arg2Empty() || !userInput.arg3Empty()) {
                    throw new ExcessiveArgException();
                }
                int index = Integer.parseInt(userInput.getArg1()) - 1;
                taskList.markList(index);
                System.out.println("I see. In that case, the following task will be unmarked:");
                System.out.println(taskList.get(index).toString());
            } catch (InsufficientArgException | ExcessiveArgException error) {
                System.out.println(formatError + "Steven's advice: The format of \"mark\" is as follows:"
                        + "\nmark (x) - x is an number corresponding with the index of an item in the list.");
            } catch (NumberFormatException error) {
                System.out.println("Hmm... Seems like you want me to mark, something, but you didn't provide a valid "
                        + "number for me to work off.\nSteven's Advice: Use a number instead.");
            } catch (IndexOutOfBoundsException error) {
                System.out.println("Ah, a pity... Seems like you don't have that many tasks.\nSteven's advice: "
                        + "Use a number which corresponds to a task number. If you need to know what number corresponds"
                        + " to what task, use \"list\".");
            } catch (IOException e) {
                System.out.println(corrupted);
            } catch (IncompatibleMarkException e) {
                System.out.println("Ah, hold on. Seems like this one's still incomplete. I can't unmark this."
                        + "\nSteven's advice: You might have wanted to use\"mark\" instead!");
            }
            break;
        case "mark":
            try {
                if (userInput.arg1Empty()) {
                    throw new InsufficientArgException();
                }
                if (!userInput.arg2Empty() || !userInput.arg3Empty()) {
                    throw new ExcessiveArgException();
                }
                int index = Integer.parseInt(userInput.getArg1()) - 1;
                taskList.unmarkList(index);
                System.out.println("As you wish, this task will be marked:");
                System.out.println(taskList.get(index).toString());
            } catch (InsufficientArgException | ExcessiveArgException error) {
                System.out.println(formatError + "Steven's advice: The format of \"unmark\" is as follows:"
                        + "\nunmark (x) - x is an number corresponding with the index of an item in the list.");
            } catch (NumberFormatException error) {
                System.out.println("Hmm... Seems like you want me to unmark something, but you didn't provide a "
                        + "valid number for me to work off.\nSteven's Advice: Use a number instead.");
            } catch (IndexOutOfBoundsException error) {
                System.out.println("Ah, a pity... Seems like you don't have that many tasks.\nSteven's advice: "
                        + "Use a number which corresponds to a task number. If you need to know what number "
                        + "corresponds to what task, use \"list\".");
            } catch (IOException e) {
                System.out.println(corrupted);
            } catch (IncompatibleMarkException e) {
                System.out.println("Wait a moment, this one's already complete! I can't mark it as such again!."
                        + "\nSteven's advice: You might have wanted to use\"unmark\" instead!");
            }
            break;
        case "todo":
            try {
                if (userInput.arg1Empty()) {
                    throw new InsufficientArgException();
                }
                if (!userInput.arg2Empty() || !userInput.arg3Empty()) {
                    throw new ExcessiveArgException();
                }
                String name = userInput.getArg1();
                taskList.addToList(new Todo(name));
                System.out.print(line);
                System.out.println("I see. I shall add the following to the list of tasks:");
                System.out.println(taskList.get(taskList.size() - 1));
                System.out.printf("Do bear in mind that you now have %d tasks in the list.%n", taskList.size());
            } catch (InsufficientArgException | ExcessiveArgException error) {
                System.out.println(formatError + "Steven's advice: The format of \"Todo\" is as follows:\nTodo (item) "
                        + "- item is the name of an item that you want to add to the list as a todo.");
            } catch (IOException e) {
                System.out.println(corrupted);
            }
            break;
        case "deadline":
            try {
                if (userInput.arg1Empty() || userInput.arg2Empty()) {
                    throw new InsufficientArgException();
                }
                if (!userInput.arg3Empty()) {
                    throw new ExcessiveArgException();
                }
                LocalDate due = LocalDate.parse(userInput.getArg2());
                taskList.addToList(new Deadline(userInput.getArg1(), due));
                System.out.print(line);
                System.out.println("I see. I shall add the following to the list of tasks:");
                System.out.println(taskList.get(taskList.size() - 1));
                System.out.printf("Do bear in mind that you now have %d tasks in the list.%n", taskList.size());
            } catch (InsufficientArgException | ExcessiveArgException error) {
                System.out.println(formatError + "Steven's advice: The format of \"Deadline\" is as follows:"
                        + "\nDeadline (item) /by (date) - item is the name of an item that you want to add to the "
                        + "list as a deadline. date must be a date.");
            } catch (IOException e) {
                System.out.println(corrupted);
            } catch (DateTimeParseException error) {
                System.out.println(dateErr);
            }
            break;
        case "event":
            try {
                if (userInput.arg1Empty() || userInput.arg2Empty() || userInput.arg3Empty()) {
                    throw new InsufficientArgException();
                }
                LocalDate start = LocalDate.parse((userInput.getArg2()));
                LocalDate end = LocalDate.parse(userInput.getArg3());
                if (end.isBefore(start)) {
                    throw new IllogicalDateException();
                }
                taskList.addToList(new Event(userInput.getArg1(), start, end));
                System.out.print(line);
                System.out.println("I see. I shall add the following to the list of tasks:");
                System.out.println(taskList.get(taskList.size() - 1));
                System.out.printf("Do bear in mind that you now have %d tasks in the list.%n", taskList.size());
            } catch (InsufficientArgException error) {
                System.out.println(formatError + "Steven's advice: The format of \"Event\" is as follows:\nDeadline "
                        + "(item) /from (date1) /to (date2) - item is the name of an item that you want to add to the "
                        + "list as a deadline. date1 amd date2 must be a dates.");
            } catch (IOException e) {
                System.out.println(corrupted);
            } catch (DateTimeParseException error) {
                System.out.println(dateErr);
            } catch (IllogicalDateException error) {
                System.out.println("Now hold on, this doesn't make sense! How can you have an event start earlier "
                        + "than it ends?\nSteven's advice: Make sure your first date is before the second!");
            }
            break;
        case "delete":
            try {
                if (userInput.arg1Empty()) {
                    throw new InsufficientArgException();
                }
                if (!userInput.arg2Empty() || !userInput.arg3Empty()) {
                    throw new ExcessiveArgException();
                }
                int index = Integer.parseInt(userInput.getArg1());
                Task removedItem = taskList.get(index - 1);
                taskList.removeFromList(index - 1);
                System.out.println(line + "Very well, the following item has been removed from the"
                        + "list:\n" + removedItem.toString() + "\n");
                System.out.println("For the sake of completeness, this is the current list, do take note if any of "
                        + "your items have been moved around in order.");
                taskList.printList();
            } catch (InsufficientArgException | ExcessiveArgException error) {
                System.out.println(formatError + "Steven's advice: The format of \"Delete\" is as follows:\n"
                        + "delete (x) - x is an number corresponding with the index of an item in the list.");
            } catch (NumberFormatException error) {
                System.out.println("Oh, I can't  delete that - I need a number of an item in the list to delete it."
                        + "\nSteven's Advice: Use a number instead.");
            } catch (IndexOutOfBoundsException error) {
                System.out.println("Apologies, you don't have a task of this number, so I can't delete it."
                        + "\nSteven's advice: Use a number which corresponds to a task number. If you need to know"
                        + "what number corresponds to what task, use \"list\".");
            } catch (IOException e) {
                System.out.println(corrupted);
            }
            break;
        case "find":
            try {
                if (userInput.arg1Empty()) {
                    throw new InsufficientArgException();
                }
                if (!userInput.arg2Empty() || !userInput.arg3Empty()) {
                    throw new ExcessiveArgException();
                }
                String matchingList = taskList.findTasks(userInput.getArg1());
                System.out.println(String.format(line + "Ah, so you're looking for tasks with the word %s? Sure! "
                        + "here they are!", userInput.getArg1()));
                System.out.println(matchingList);
            } catch (InsufficientArgException | ExcessiveArgException error) {
                System.out.println(formatError + "Steven's advice: The format of \"find\" is as follows:\n"
                        + "find (item) - item is the name of the task, or part of the task");
            }
            break;
        case "help":
            System.out.println("The following are UserInputs that I recgonise, and their respective formats:");
            System.out.println("bye - terminates the program");
            System.out.println("list - provides a list of events that you have added prior");
            System.out.println("mark (x) - marks the xth item on the list as complete. Note that an item marked"
                    + "complete cannot be marked complete again.");
            System.out.println("unmark (x) - marks the xth item on the list as incomplete. Note than an item marked "
                    + "incomplete cannot be marked incomplete again.");
            System.out.println("todo (item) - adds a todo item to the list.");
            System.out.println("deadline (item) /by (date1) - adds a deadline item to the list which is due on "
                    + "date1. The format for both dates must be \"yyyy-mm-dddd\".");
            System.out.println("event (item) /from (date1) /to (date2) - adds an event item to the list which begins "
                    + "on date1 and ends on date2. The format for both dates must be \"yyyy-mm-dddd\".");
            System.out.println("delete (x) - delete the xth item from the list. Do note that this may affect the "
                    + "positioning of some of the items.");
            System.out.println("find (item) - finds any items in the list and prints them out, giving "
                    + "their indexes.");
            break;
        case "bye":
            return true;
        default:
            System.out.println("Hm, this doesn't seem like something can do for you. Try something else?"
                    + "\nSteven's advice: try typing \"help\" to see what user inputs are available.");
            break;
        }
        System.out.print(line);
        return false;
    }
}
