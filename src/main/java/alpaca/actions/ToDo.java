package alpaca.actions;

import java.util.ArrayList;

import alpaca.tasks.Task;
import alpaca.exceptions.ValueNotFound;
import alpaca.exceptions.InvalidInput;

public abstract class ToDo extends Template {
    protected static String trigger = "todo";

    protected static Boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "").replaceAll("[0-9]", "").equals(trigger);
    }

    public static Boolean run(String input, ArrayList<Task> list) throws ArrayIndexOutOfBoundsException, ValueNotFound, InvalidInput {
        if (isTrigger(input, trigger)) throw new ValueNotFound("You need to provide a value for todo");
        if (!isTriggerPrefix(input, trigger)) return false;
        Task task = new alpaca.tasks.ToDo(removePrefix(input, trigger));
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + task.toString());
        numOfTasks(list);
        return true;
    }
}