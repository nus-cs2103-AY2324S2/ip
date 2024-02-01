package dune;

import dune.task.TaskList;

import java.util.Scanner;

public class Parser {

    private static String[] taskTypes = new String[] {"todo", "deadline", "event"};

    public boolean parse(Scanner scanner, TaskList taskList, Ui ui, Storage storage) {
        String text = scanner.nextLine();  // Read user input
        if (text.equals("bye")) {
            ui.printGoodbye();
            return false;
        } else if (text.startsWith("delete")) {
            taskList.deleteTask(text.substring(6), storage);
            storage.saveTasks(taskList);
            return true;
        } else if (text.startsWith("list")) {
            taskList.print();
            return true;
        } else if (text.startsWith("mark")) {
            // abstract this into a method
            try {
                if (text.equals("mark")) {
                    throw new DuneException("Give an index to mark");
                }
                String remaining = "";
                // remove all leading and trailing spaces
                remaining = text.substring(4).trim();
                // parseInt might throw NumberFormatException
                int index = Integer.parseInt(remaining);
                // Index... exception
                taskList.getTask(index - 1).complete();
                storage.saveTasks(taskList);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.getTask(index - 1));

            } catch (IndexOutOfBoundsException i) {
                System.out.println("Give a valid index to mark");
            } catch (NumberFormatException n) {
                System.out.println("Remaining characters do not match an integer");
            } catch (DuneException d) {
                System.out.println(d);
            } finally {
                return true;
            }
        }

        createNewTaskAttempt(text, taskList, storage);
        return true;
    }

    public void createNewTaskAttempt(String text, TaskList taskList, Storage storage) {
        // System.out.println(text);
        boolean addedTask = false;
        String check;
        for (int i = 0; i < taskTypes.length; i++) {
            check = taskTypes[i];
            if (text.startsWith(check)) {
                addedTask = true;
                try {
                    if (text.equals(check)) {
                        throw new DuneException("The description of a to-do cannot be empty.");
                    }
                } catch (DuneException d) {
                    System.out.println(d);
                    continue;
                }
                taskList.addTask(i, text.substring(check.length()).trim(), storage);
                // how to save to hard-disk?
                break;
            }
        }
        if (!addedTask) {
            {
                try {
                    // make it more informative LOL
                    throw new DuneException("Tasks can only be of types todo, deadline, or event.");
                } catch (DuneException d) {
                    System.out.println(d);
                }
            }
        }
    }
}
