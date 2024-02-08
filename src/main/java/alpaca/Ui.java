package alpaca;
import java.util.Scanner;
import java.util.ArrayList;

import alpaca.tasks.Task;

import alpaca.actions.Deadline;
import alpaca.actions.Delete;
import alpaca.actions.Event;
import alpaca.actions.Mark;
import alpaca.actions.ToDo;
import alpaca.actions.Unmark;

import alpaca.exceptions.InvalidInput;
import alpaca.exceptions.ValueNotFound;

public class Ui {
    final static String name = "Alpaca";
    Scanner scanner;
    ArrayList<Task> list = new ArrayList<Task>();

    private void divider() {
        System.out.println("____________________________________________________________\n");
    }

    private void greeting() {
        divider();
        System.out.println("Hihi! I'm " + Ui.name + "\nWhat can I do for you?");
        divider();
    }

    private void bye() {
        System.out.println("cucu");
        divider();
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
        divider();
        if (input.toLowerCase().equals("bye")) {
            bye();
            return;
        } else if (input.toLowerCase().equals("list")) list();
        else {
            trying : try {
                if (Mark.run(input, list)) break trying;
                if (Unmark.run(input, list)) break trying;
                if (Deadline.run(input, list)) break trying;
                if (Event.run(input, list)) break trying;
                if (ToDo.run(input, list)) break trying;
                if (Delete.run(input, list)) break trying;
                System.out.println("Me no understand :(");
            } catch (ArrayIndexOutOfBoundsException | ValueNotFound | InvalidInput e) {
                System.out.println(e.getMessage());
            }
        }
        divider();
        processInput();
    }
    
    public void run() {
        greeting();
        scanner = new Scanner(System.in);
        ReadData.read(list);
        processInput();
        ReadData.write(list);
        scanner.close();
    }
    
    public static void main(String[] args) {
    }
}