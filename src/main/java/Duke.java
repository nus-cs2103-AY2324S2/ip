package main.java;

import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String bot_name = "Paimon";
        System.out.println("-------------------->" + "\nGreetings Traveller!\nI'm " +  bot_name + ", your friendly guide! What shall we do today?" + "\n-------------------->");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (Objects.equals(userInput, "bye")) {
                break;
            }
            System.out.println("> " + userInput);
        }
        System.out.println("-------------------->" + "\nSee you later Traveller!" + "\n-------------------->");
    }
}
