import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    boolean isRunning;
    ArrayList<String> todoList = new ArrayList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        this.isRunning = true;

        String introMessage = " /\\_/\\\n" +
                "\t( o.o )\n" +
                "\t > ^ <\n" +
                "\tNya-ice to meet you! I'm Toothless :D\n" +
                "\tWhat can I do for you?";
        printMessage(introMessage);

        while (isRunning) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                isRunning = false;
            } else if (userInput.equals("list")) {
                printList();
            }else {
                addToList(userInput);
            }
        }

        printMessage("Bye. Purr-lease chat again soon!");
    }

    public void printMessage(String message) {
        String line = "\t____________________________________________________________";
        System.out.println(line);
        System.out.println("\t" + message);
        System.out.println(line);
    }

    public void addToList(String task) {
        todoList.add(task);
        printMessage("added: " + task);
    }

    public void printList() {
        String listString = "";
        for (int i = 1; i <= todoList.size(); i++) {
            if (i > 1) {
                listString += "\t";
            }
            listString += i + ". " + todoList.get(i - 1);
            if (i < todoList.size()) {
                listString += "\n";
            }
        }
        printMessage(listString.strip());
    }
}
