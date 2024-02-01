package ui;

import task.Task;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String logo =
                "                     ____            _     _       \n" +
                "                    |  _ \\          | |   | |      \n" +
                "                    | |_) |_   _  __| | __| |_   _ \n" +
                "                    |  _ <| | | |/ _` |/ _` | | | |\n" +
                "                    | |_) | |_| | (_| | (_| | |_| |\n" +
                "                    |____/ \\__,_|\\__,_|\\__,_|\\__, |\n" +
                "                                              __/ |\n" +
                "                                             |___/ ";
        showLine();
        System.out.println(logo);
        showLine();
        System.out.println("Heya bud!\nHow can I help?");
        showLine();
    }

    public void showBye() {
        System.out.println("See ya!");
    }

    public void showLine() {
        System.out.println("______________________________________________________________________");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    public void showLoadingError() {
        System.out.println("Error while loading file! Creating new data file!");
    }

    public void showSavingError() {
        System.out.println("Error while saving to file!");
    }

    public void showAdd(Task task, int n) {
        System.out.println("I've added the following task:");
        System.out.println(task);
        System.out.println("You have " + n + " tasks");
    }

    public void showDelete(Task task, int n) {
        System.out.println("I've deleted the following task:");
        System.out.println(task);
        System.out.println("You have " + n + " tasks remaining!");
    }

    public void showList() {
        System.out.println("Here you go buddy!:");
    }

    public void showMark(Task task) {
        System.out.println("I've updated the following task:");
        System.out.println(task);
    }

    public void showFind() {
        System.out.println("Here are the matching tasks!");
    }
    
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }
}
