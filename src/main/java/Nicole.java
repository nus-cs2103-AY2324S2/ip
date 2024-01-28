import userrequests.Ui;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;

public class Nicole {

    /**
     * Initialises the chatbot and triggers user interactions
     *
     */
    public Nicole() {
        try {
            new Ui();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new File("./data").mkdirs();
        new File("./data/tasks.txt");
        new Nicole();
    }
}
