import java.util.Scanner;
import java.util.ArrayList;
public class AaronBot {
    private static ArrayList<String> taskList = new ArrayList<>();
    public static void main(String[] args) {
        boolean notBye = true;
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Hello, I am AaronBot, please talk to me I love my students very much :)");
        System.out.println("To add a task to the list, type 'add'.");
        System.out.println("To show the list, type 'show list'.");
        while (notBye) {
            notBye = chat(inputScanner.nextLine(), inputScanner);
        }
        inputScanner.close();
    }

    private static boolean chat(String userInput, Scanner scanner) {
        if (userInput.equalsIgnoreCase("add")) {
            System.out.println("What task would you like to add to the list?");
            boolean isAdded =  addToList(scanner.nextLine());
            if (isAdded) {
                System.out.println("This task is already in the list!");
                return true;
            } else {
                System.out.println("Task added :)");
                return true;
            }
        } else if (userInput.equalsIgnoreCase("show list")) {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1 + ". " + taskList.get(i)));
            }
            return true;
        } else {
            return echo(userInput);
        }
    }

    private static boolean echo(String echoString) {
        if (echoString.equalsIgnoreCase("what is your favorite module")) {
            System.out.println("CS1231S :)");
            return true;
        } else if (echoString.equalsIgnoreCase("bye")) {
            System.out.println("Ok byebye, HAND.");
            return false;
        } else {
            System.out.println(echoString + " student.");
            return true;
        }
    }

    private static boolean addToList(String newTask) {
        if (taskList.contains(newTask)) {
            return true;
        } else {
            taskList.add(newTask);
            return false;
        }
    } 
}
