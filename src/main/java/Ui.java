import java.util.Scanner;
public class Ui {
    private static final String line = "    _______________________________________________________________";
    private Scanner scanner;
    private Storage taskList;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.taskList = new Storage();
    }

    public static void printLine() {
        System.out.println(line);
    }

    public void greet() {
        printLine();
        System.out.println(
                "    Hello! I'm Tes\n" +
                "    What can I do for you?"
                );
        printLine();
    }

    public String nextCommand() {
        return scanner.nextLine();
    }

    public void close() {
        printLine();
        System.out.println(
                "    Bye. Hope to see you again soon!"
                );
        printLine();
    }

    public void addTask(String command) {
        printLine();
        System.out.println(
                "    added: " + command
                );
        printLine();
        this.taskList.storeTask(command);
    }

    public void listTask() {
        System.out.println(line);
        for (int i = 1; i <= this.taskList.getSize(); i++) {
            System.out.println("    " + i + ". "  + this.taskList.getTask(i));
        }
        System.out.println(line);
    }

}
