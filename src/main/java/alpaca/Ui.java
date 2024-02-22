package alpaca;

import alpaca.actions.Deadline;
import alpaca.actions.Delete;
import alpaca.actions.Event;
import alpaca.actions.Find;
import alpaca.actions.Mark;
import alpaca.actions.ToDo;
import alpaca.actions.Unmark;
import alpaca.exceptions.InvalidInput;
import alpaca.exceptions.ValueNotFound;
import alpaca.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the Ui elements.
 **/
public class Ui {
    static final String NAME = "Alpaca";
    Scanner scanner;
    ArrayList<Task> list = new ArrayList<Task>();

    public String greet() {
        return "Hihi! I'm " + Ui.NAME + "\nWhat can I do for you?";
    }

    private String bye() {
        return "cucu";
    }

    private String list() {
        if (list.size() == 0) {
            return "Sorry, you haven't created any tasks yet :(";
        }
        assert !list.isEmpty(): "List is not supposed to be empty";
        String result = "";
        result += " Here are the tasks in your list:";
        int counter = 1;
        for (Task i : list) {
            result += "\n" + counter + "." + (i.toString());
            counter++;
        }
        return result;
    }

    public String processInput(String input) {
        if (input.toLowerCase().equals("bye")) {
            return bye();
        } else if (input.toLowerCase().equals("list")) {
            return list();
        }
        try {
            if (Deadline.check(input, list)) {
                return Deadline.run(input, list);
            }
            if (Delete.check(input, list)) {
                return Delete.run(input, list);
            }
            if (Event.check(input, list)) {
                return Event.run(input, list);
            }
            if (Find.check(input, list)) {
                return Find.run(input, list);
            }
            if (Mark.check(input, list)) {
                return Mark.run(input, list);
            }
            if (ToDo.check(input, list)) {
                return ToDo.run(input, list);
            }
            if (Unmark.check(input, list)) {
                return Unmark.run(input, list);
            }
        } catch (ArrayIndexOutOfBoundsException | ValueNotFound | InvalidInput e) {
            return e.getMessage();
        }
        return "Me no understand :(";
    }
}