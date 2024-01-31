package capone;

import java.util.Scanner;

public class Ui {


    public void printWelcomeMsg() {
        String logo = "░█▀▀░█▀█░█▀█░█▀█░█▀█░█▀▀░\n"
                + "░█░░░█▀█░█▀▀░█░█░█░█░█▀▀░\n"
                + "░▀▀▀░▀░▀░▀░░░▀▀▀░▀░▀░▀▀▀░";
        System.out.printf("Hello! I'm\n%s\nWhat can I do for you?\n%n", logo);
    }

    public void sendMessage(String string) {
        System.out.println(string);
    }
}
