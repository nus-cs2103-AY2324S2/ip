package Actions;

import java.util.ArrayList;
import Tasks.Task;
import Exceptions.*;

public abstract class Deadline extends Template {
    protected static String trigger = "deadline";

    protected static Boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "").replaceAll("[0-9]", "").equals(trigger);
    }

    public static Boolean run(String input, ArrayList<Task> list) throws ArrayIndexOutOfBoundsException, ValueNotFound, InvalidInput {
        if (isTrigger(input, trigger)) throw new ValueNotFound("You need to provide a value for deadline");
        if (!isTriggerPrefix(input, trigger)) return false;
        Task task = new Tasks.Deadline(processParams(removePrefix(input, trigger)));
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + task.toString());
        numOfTasks(list);
        return true;
    }
}