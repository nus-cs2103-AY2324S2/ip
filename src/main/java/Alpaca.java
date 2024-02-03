import java.util.Scanner;
import java.util.ArrayList;

import Tasks.Task;

import Actions.Deadline;
import Actions.Delete;
import Actions.Event;
import Actions.Mark;
import Actions.ToDo;
import Actions.Unmark;

import Exceptions.InvalidInput;
import Exceptions.ValueNotFound;

public class Alpaca {
    static String name = "Alpaca";
    static Scanner scanner;
    static ArrayList<Task> list = new ArrayList<Task>();

    private static void divider() {
        System.out.println("____________________________________________________________\n");
    }

    private static void greeting() {
        divider();
        System.out.println("Hihi! I'm " + Alpaca.name + "\nWhat can I do for you?");
        divider();
    }

    private static void bye() {
        System.out.println("cucu");
        divider();
    }

    private static void list() {
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

    private static void processInput() {
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
    
    public static void main(String[] args) {
        greeting();
        scanner = new Scanner(System.in);
        ReadData.read(list);
        processInput();
        ReadData.write(list);
        scanner.close();
    }
}