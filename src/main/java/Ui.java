import java.util.Scanner;

class Ui {
    private final String name;

    Ui(String name) {
        this.name = name;
    }

    void greeting() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        separatePrompt();
    }

    String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    void separatePrompt() {
        System.out.println("_____________________________________________________________________________");
    }
}
