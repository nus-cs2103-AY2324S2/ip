import java.util.Scanner;

public class UserInterface {
    /*
    This class is for the the user interface.
    */
    int numList = 0;
    Scanner scan = new Scanner(System.in);
    String[] list = new String[100];
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

    public void addToList(String input) {
        list[numList] = input;
        numList++;
        System.out.println("added: " + input);
    }

    public void poll() {

        boolean polling = true;

        while (polling) {
            String input = scan.nextLine();
            switch (input) {
                case "bye":
                    polling = false;
                    break;
                
                case "list":
                    displayList();
                    break;
            
                default:
                    addToList(input);
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
