package jade.ui;

import java.util.Scanner;

public class Ui {
    private String line = "\t——————————————————————————————————————————\n";
    private String logo = "\t  ____   ___    ____     ______\n"
            + "\t  |  |  / _ \\  |  ___ \\ / |____/\n"
            + "\t  |  | | | | | | |  | | | |____\n"
            + "\t  |  | | |_| | | |  | | | |____|\n"
            + "\t|\\|  | | ___ | | |__| | | |____\n"
            + "\t \\___| |_| |_| |_____/  \\_|____\\\n";

    public Ui() {
    }

    public void showError(String errorMsg) { printMessage(errorMsg); }

    public void showLoadingError() { printMessage("\t[There's no file under current storage path, a new task file has been created.]"); }

    public void showLine() {
        System.out.print(line);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void launch() {
        System.out.printf("%s%s\tHello, I'm Jade, your task manager.\n\tFeel free to set reminders for your task by entering text using the following format:\n\t" +
                "1. todo {Task Description} -> e.g. todo read a book\n\t" +
                "2. deadline {Task Description} /by {yyyy-mm-dd} -> e.g. deadline read a book /by 2024-12-31\n\t" +
                "3. event {Task Description} /from {yyyy-mm-dd} /to {yyyy-mm-dd} -> e.g. read a book /from 2024-12-30 /to 2024-12-31\n%s", logo, line, line);
    }

    public void printMessage(String msg) {
        showLine();
        System.out.println(msg);
        showLine();
    }

}
