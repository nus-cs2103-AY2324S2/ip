package chimp.core;

import java.util.HashMap;

import chimp.task.Task;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________\n";

    static HashMap<String, String> phrases = new HashMap<>();
    {
        String greet = " Hello! I'm Chimp\n" +
                " What can I do for you?\n";
        String bye = "Bye. Hope to see you again soon!\n";
        String mark = "Nice! I've marked this task as done: \n";
        String unmark = "OK, I've marked this task as not done yet: \n";
        String hoo = "HOO-HOO-HOO-HOO, I don't know what that means\n";
        String delete = "Noted. I've removed this task:\n";
        String find = "Here are the matching tasks in your list:\n";

        phrases.put("greet", greet);
        phrases.put("bye", bye);
        phrases.put("mark", mark);
        phrases.put("unmark", unmark);
        phrases.put("hoo", hoo);
        phrases.put("delete", delete);
        phrases.put("find", find);
    }

    /**
     * Prints the phrase associated with the given key from the getPhrases hashmap.
     *
     * @param key the key to retrieve the phrase from the hashmap
     */
    public void say(String key) {
        String phrase = phrases.get(key);
        System.out.println(DIVIDER);
        System.out.println(phrase);
        System.out.println(DIVIDER);
    }

    /**
     * Prints the given new task and the number of tasks in the list.
     * 
     * @param task The task to be printed.
     * @param list The task list containing the tasks.
     */
    public void say(Task task, TaskList list) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Prints a message to the console with information about a task and the current
     * task list size.
     * 
     * @param key  the key to retrieve the phrase from the phrases map
     * @param task the task to be displayed
     * @param list the current task list
     */
    public void say(String key, Task task, TaskList list) {
        String phrase = phrases.get(key);
        System.out.println(DIVIDER);
        System.out.println(phrase);
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public void say(String key, Task[] matches, TaskList list) {
        String phrase = phrases.get(key);
        System.out.println(DIVIDER);
        System.out.println(phrase);
        for (int i = 0; i < matches.length; i++) {
            System.out.println((i + 1) + ". " + matches[i].toString());
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints all tasks for the "list" command.
     * 
     * @param list The task list to be printed.
     */
    public void printList(TaskList list) {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println(DIVIDER);
    }
}