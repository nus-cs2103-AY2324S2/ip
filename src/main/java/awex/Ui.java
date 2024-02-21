package awex;

import tasks.Task;

public class Ui {
    /**
     * Prints first message when user activates Awex.
     */
    public void greeting() {
        System.out.println("Hello! I'm AWEX!\nWhat can I do for you?");
    }

    /**
     * Prints last message after user deactivates Awex.
     */
    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints explainer message when user gives erroneous inputs.
     */
    public void allInstructions() {
        System.out.println("Input type must be one of:");
        System.out.println("  1. list");
        System.out.println("  2. mark <task number>");
        System.out.println("  3. unmark <task number>");
        System.out.println("  4. todo <task>");
        System.out.println("  5. deadline <task> /by <YYYY-MM-DD hh:mm>");
        System.out.println("  6. event <task> /from <YYYY-MM-DD hh:mm> /to <YYYY-MM-DD hh:mm>");
        System.out.println("  7. delete <task number>");
        System.out.println("Type 'bye' to exit.");
    }

    /**
     * Prints error message if list is empty.
     */
    public void emptyListMessage() {
        System.out.println("List is empty.");
    }

    /**
     * Prints tasks saved in list and their details.
     */
    public void showListMessage(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        int len = list.size();
        for (int i = 1; i <= len; i++) {
            System.out.println(i + "." + list.get(i - 1).showAll());
        }
    }

    /**
     * Prints explainer message when user gives erroneous inputs when marking, unmarking or deleting tasks.
     */
    public void wrongMarkDeleteFormatMessage(String str) {
        System.out.println("Format should be '" + str + " <task number>'");
    }

    /**
     * Prints explainer message when user provides inaccessible list index.
     */
    public void wrongIndexMessage(int i, int len) {
        if (i == 0) {
            System.out.println("Pick a value between 1 and " + len + ".");
        } else {
            System.out.println("List has only " + len + " tasks.");
        }
    }

    /**
     * Prints notification message when task deleted successfully.
     */
    public void deleteTaskMessage(int i, TaskList list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + list.remove(i - 1).showAll());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Prints notification message when task marked/unmarked successfully.
     */
    public void changeStatusMessage(String str, Task t) {
        if (str.equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
        } else if (str.equals("unmark")) {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + t.showAll());
    }

    /**
     * Prints explainer message when user provides erroneous inputs while making tasks.
     */
    public void failedTaskCreationMessage(String str) {
        if (str.equals("todo")) {
            System.out.println("Format should be 'todo <task>'");
        } else if (str.equals("deadline")) {
            System.out.println("Format should be 'deadline <task> /by <YYYY-MM-DD hh:mm>'");
        } else {
            System.out.println("Format should be 'event <task> /from <YYYY-MM-DD hh:mm> /to <YYYY-MM-DD hh:mm>'");
        }
    }

    /**
     * Prints notification message when task saved successfully.
     */
    public void newTaskAddedMessage(int i, Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.showAll());
        System.out.println("Now you have " + i + " tasks in the list.");
    }
}