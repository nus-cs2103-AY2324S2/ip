package tsundere.ui;

import java.util.Scanner;

import tsundere.exception.GeneralException;

public class Ui {
    private static final Scanner sc = new Scanner(System.in);

    public Ui() {
        greet();
    }

    private void greet() {
        System.out.println("_________________________________________________________________________________\n"
                + "Don't get the wrong idea!\n" + "I'm not doing this to help you or anything!\n"
                + "_________________________________________________________________________________");
    }


    private void exit() {
        System.out.println("_________________________________________________________________________________\n"
                + "Don't forget about me!\n" + "You better come back soon!\n"
                +"_________________________________________________________________________________");
    }


    public void chat() {

        String str = sc.nextLine();

        while (!str.equals("bye")) {
            System.out.println("_________________________________________________________________________________");

            Parser action = new Parser(str);
            try {
                action.execute();
            } catch (GeneralException e) {
                System.out.println(e);
            }
            System.out.println("_________________________________________________________________________________");
            str = sc.nextLine();
        }
        exit();
    }
}
