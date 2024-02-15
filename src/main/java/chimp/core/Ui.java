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
    public String say(String key) {
        StringBuilder sb = new StringBuilder();
        String phrase = phrases.get(key);

        sb.append(DIVIDER);
        sb.append(phrase);
        sb.append(DIVIDER);

        return sb.toString();
    }

    /**
     * Prints the given new task and the number of tasks in the list.
     * 
     * @param task The task to be printed.
     * @param list The task list containing the tasks.
     */
    public String say(Task task, TaskList list) {
        StringBuilder sb = new StringBuilder();

        sb.append(DIVIDER);
        sb.append("Got it. I've added this task:");
        sb.append(task);
        sb.append("Now you have " + list.size() + " tasks in the list.");
        sb.append(DIVIDER);

        return sb.toString();
    }

    /**
     * Prints a message to the console with information about a task and the current
     * task list size.
     * 
     * @param key  the key to retrieve the phrase from the phrases map
     * @param task the task to be displayed
     * @param list the current task list
     */
    public String say(String key, Task task, TaskList list) {
        String phrase = phrases.get(key);
        StringBuilder sb = new StringBuilder();

        sb.append(DIVIDER);
        sb.append(phrase);
        sb.append(task);
        sb.append("Now you have " + list.size() + " tasks in the list.");
        sb.append(DIVIDER);

        return sb.toString();
    }

    public String say(String key, Task[] matches, TaskList list) {
        String phrase = phrases.get(key);
        StringBuilder sb = new StringBuilder();

        sb.append(DIVIDER);
        sb.append(phrase);
        for (int i = 0; i < matches.length; i++) {
            sb.append((i + 1)).append(". ").append(matches[i].toString()).append("\n");
        }
        sb.append(DIVIDER);

        return sb.toString();
    }

    /**
     * Prints all tasks for the "list" command.
     * 
     * @param list The task list to be printed.
     */
    public String printList(TaskList list) {
        StringBuilder sb = new StringBuilder();

        sb.append(DIVIDER);
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append((i + 1)).append(". ").append(list.get(i)).append("\n");
        }
        sb.append(DIVIDER);

        return sb.toString();
    }
}