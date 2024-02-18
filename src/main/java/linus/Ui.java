package linus;

import java.util.Scanner;

public class Ui {
    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
