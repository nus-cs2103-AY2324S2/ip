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

    private void divide() {
        System.out.println("____________________________________________________________\n");
    }

    private void greet() {
        divide();
        System.out.println("Hihi! I'm " + Ui.NAME + "\nWhat can I do for you?");
        divide();
    }

    private void bye() {
        System.out.println("cucu");
        divide();
    }

    private void list() {
        if (list.size() == 0) {
            System.out.println("Sorry, you haven't created any tasks yet :(");
            return;
        }
        System.out.println(" Here are the tasks in your list:");
        int counter = 1;
        for (Task i : list) {
            System.out.println(counter + "." + (i.toString()));
            counter++;
        }
    }

    private void processInput() {
        String input = scanner.nextLine();
        divide();
        if (input.toLowerCase().equals("bye")) {
            bye();
            return;
        } else if (input.toLowerCase().equals("list")) {
            list();
        } else {
            trying: try {
                if (Deadline.run(input, list)) {
                    break trying;
                }
                if (Delete.run(input, list)) {
                    break trying;
                }
                if (Event.run(input, list)) {
                    break trying;
                }
                if (Find.run(input, list)) {
                    break trying;
                }
                if (Mark.run(input, list)) {
                    break trying;
                }
                if (ToDo.run(input, list)) {
                    break trying;
                }
                if (Unmark.run(input, list)) {
                    break trying;
                }
                System.out.println("Me no understand :(");
            } catch (ArrayIndexOutOfBoundsException | ValueNotFound | InvalidInput e) {
                System.out.println(e.getMessage());
            }
        }
        divide();
        processInput();
    }

    /**
     * Handles the start and end of a run with all the necessary resource handling.
     **/
    public void run() {
        greet();
        scanner = new Scanner(System.in);
        ReadData.read(list);
        processInput();
        ReadData.write(list);
        scanner.close();
    }
}