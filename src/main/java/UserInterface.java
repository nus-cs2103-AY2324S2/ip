import java.util.Scanner;

public class UserInterface {
    /*
    This class is for the the user interface.
    */

    Scanner scan = new Scanner(System.in);
    Task[] list = new Task[100];
    int numList;
    String greeting = "Hi! My name is HAL9000";
    String exit = "Bye! See ya soon";

    public void greet() {
        System.out.println(greeting);
    }
    public void exit() {
        System.out.println(exit);
    }

    public void displayList() {
        for (int i = 0; i < numList; i++) {
            System.out.println(String.format("%d. %s", i+1 ,list[i]));
        }
    }

    public void addToList(Task input) {
        list[numList] = input;
        numList++;
        System.out.println("added: " + input);
    }

    public void markOnList(int i) {
        Task task = list[i - 1];
        task.markDone();
        System.out.println("Marked this :" + task);
    }

    public void unmarkOnList(int i) {
        Task task = list[i - 1];
        task.markUndone();
        System.out.println("Unmarked this :" + task);
    }

    public void processCommand(String input) {
        String[] splitString = input.split(" ");
        String firstWord = splitString[0];
        switch (firstWord) {
            case "list":
                displayList();
            break;
            case "mark":
                markOnList(Integer.parseInt(splitString[1]));
                break;
            case "unmark":
                unmarkOnList(Integer.parseInt(splitString[1]));
                break;
            default:
                Task task = new Task(input);
                addToList(task);
                break;
        }
    }

    public void poll() {

        boolean polling = true;

        while (polling) {
            String input = scan.nextLine();

            switch (input) {
                case "bye":
                    polling = false;
                    break;

                default:
                    processCommand(input);
                    break;
            }
        }

    }

    public void start() {
        greet();
        poll();
        exit();
    }
}
