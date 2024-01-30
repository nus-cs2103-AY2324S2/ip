package Actions;

import java.util.ArrayList;
import Tasks.Task;
import Exceptions.*;

public abstract class ToDo extends Template {
    protected static String trigger = "todo";

    protected static Boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "").replaceAll("[0-9]", "").equals(trigger);
    }

    public static Boolean run(String input, ArrayList<Task> list) throws ArrayIndexOutOfBoundsException, ValueNotFound, InvalidInput {
        if (isTrigger(input, trigger)) throw new ValueNotFound("You need to provide a value for todo");
        if (!isTriggerPrefix(input, trigger)) return false;
        Task task = new Tasks.ToDo(processParams(removePrefix(input, trigger)));
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + task.toString());
        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
        return true;
    }
}