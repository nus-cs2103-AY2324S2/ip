package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Paimon {
    public static void main(String[] args) {
        greeting();
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<String>();
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                printList(taskList);
            } else {
                taskList.add(userInput);
                System.out.println("Okay Traveller, I've added " + userInput + " to your list!");
            }
        }
        goodbye();
    }
    private static void printList(ArrayList<String> list) {
        int size = list.size();
        System.out.println("Here is  your list so far! \n----->");
        for (int i = 0; i < size; i++) {
            System.out.println(Integer.toString(i+1) + ": " + list.get(i));
        }
        System.out.println("----->");
    }
    private static void greeting() {
        System.out.println("-------------------->" + "\nGreetings Traveller!\nI'm Paimon, your friendly guide! What shall we do today?" + "\n-------------------->");
    }
    private static void goodbye() {
        System.out.println("-------------------->" + "\nSee you later Traveller!" + "\n-------------------->");
    }
}
