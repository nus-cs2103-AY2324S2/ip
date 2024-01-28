import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class William {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();
        String filePath = "data/tasks.txt";

        Methods.openingTitle();
        try {
            tasks = FileSaving.loadFromFile(filePath);
        } catch (FileNotFoundException | WilliamException e) {
            System.out.println(e.getMessage() + "\n");
        }

        while (true) {
            String input = sc.nextLine();
            Commands command = null;
            String[] texts = Methods.retrieveTexts(input);
            try {
                command = Methods.retrieveCommand(texts[0]);
            } catch (WilliamException e) {
                System.out.println(e.getMessage() + "\n");
                continue;
            }

            switch (command) {
                case todo:
                    try {
                        Methods.checkAdditionalDetailEmpty(texts[1]);
                        Methods.addTask(new Todo(texts[1]), tasks);
                    } catch (WilliamException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case deadline:
                    try {
                        String[] deadlineDetails = Methods.splitBy(texts[1]);
                        Methods.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]), tasks);
                    } catch (WilliamException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case event:
                    try {
                        String[] eventDetails = Methods.splitToAndFrom(texts[1]);
                        Methods.addTask(new Event(eventDetails[0], eventDetails[1], eventDetails[2]), tasks);
                    } catch (WilliamException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case list:
                    Methods.printList(tasks);
                    break;
                case delete:
                    Methods.deleteFromList(texts[1], tasks);
                    break;
                case mark:
                    System.out.println("Nice! I've marked this task as done:");
                    Methods.markAndUnmark(texts[1], tasks);
                    break;
                case unmark:
                    System.out.println("OK, I've marked this task as not done yet:");
                    Methods.markAndUnmark(texts[1], tasks);
                    break;
                case bye:
                    System.out.println("Bye. Hope to see you again soon!");
                    try {
                        FileSaving.writeToFile(filePath, tasks);
                    } catch (IOException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                    sc.close();
                    return;
                default:
                    break;
            }
        }
    }
}
