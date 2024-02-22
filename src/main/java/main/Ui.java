package main;

import Objects.Task;

/**
 * Class that has all the methods for printing output purposes
 */
public class Ui {

    public static String greetings() {
        return "Yo! I'm Poe\nWhat can I do for you bro\nEnter \"help\" for guide";
    }

    public static String bye() {
        return "Bye come again";
    }

    public static String success(Task t) {
        return "yippie added new task\n" + t.toString();
    }

    public static String error() {
        return "no input :(";
    }

    public static String deadlineError() {
        return "input deadline with this format (eg: deadline assignment /by 2024-05-19";
    }

    public static String eventError() {
        return "input event with this format (eg: event party /from 2024-05-19 /to 2024-05-20";
    }

    public static String help(){
        return "Add ToDo : todo name\n Add Deadline : deadline name /by date\n " +
                "Add Event : event name /from date /to date\n Mark a task : mark index\n " +
                "Unmark a task : unmark index\n Delete a task : delete index\n " +
                "Find a task : find name\n List out all tasks : list \n Exit : bye";
    }
}
