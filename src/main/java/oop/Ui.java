package oop;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private TaskList taskList;
    private Scanner scanner;
    private static final String line = "\t______________________________________________________";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void greet() {
        System.out.println(line);
        System.out.println("\t Hello! I'm Lemona" +
                "\n\t Would you like some vitamins?");
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("\t Bye. Don't forget to take a LEMONA!");
        System.out.println(line);
        scanner.close();
    }

    public void list(TaskList list) {
        if (list.size() == 0) {
            System.out.println(line);
            System.out.println("\t I think you haven't had enough vitamin E."
                    + "\n\t You do not have any tasks on the list yet!"
                    + "\n\t I suggest you take some LEMONA.");
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("\t " + (i + 1) + ". " + list.get(i).print());
            }
            System.out.println(line);
        }
    }

    public void showLoadingError() {
        System.out.println(line);
        System.out.println("\t Sorry, I think I haven't had enough vitamin C."
                + "\n\t There was an error loading file, so I had to make a new taskList for you!"
                + "\n\t I will need to go have some LEMONA.");
        System.out.println(line);
    }
}
