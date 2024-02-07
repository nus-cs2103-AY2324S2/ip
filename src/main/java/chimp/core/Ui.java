package chimp.core;
import java.util.HashMap;

import chimp.task.Task;

public class Ui {
    private static final String divider = "____________________________________________________________\n";

    HashMap<String, String> getPhrases() {
        HashMap<String, String> phrases = new HashMap<>();
        String greet = " Hello! I'm Chimp\n" +
                " What can I do for you?\n";
        String bye = "Bye. Hope to see you again soon!\n";
        String mark = "Nice! I've marked this task as done: \n";
        String unmark = "OK, I've marked this task as not done yet: \n";
        String hoo = "HOO-HOO-HOO-HOO, I don't know what that means\n";
        String delete = "Noted. I've removed this task:\n";

        phrases.put("greet", greet);
        phrases.put("bye", bye);
        phrases.put("mark", mark);
        phrases.put("unmark", unmark);
        phrases.put("hoo", hoo);
        phrases.put("delete", delete);

        return phrases;
    }

    public void say(String key) {
        String phrase = getPhrases().get(key);
        System.out.println(divider);
        System.out.println(phrase);
        System.out.println(divider);
    }

    public void say(Task task, TaskList list) {
        System.out.println(divider);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(divider);
    }

    public void say(String key, Task task, TaskList list) {
        String phrase = getPhrases().get(key);
        System.out.println(divider);
        System.out.println(phrase);
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(divider);
    }

    public void printList(TaskList list) {
        System.out.println(divider);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println(divider);
    }
}