package main.java;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Paimon {
    public static void main(String[] args) {
        greeting();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        Pattern digitPattern = Pattern.compile("\\d+");
        while (true) {
            String userInput = scanner.nextLine();
            Matcher patternMatcher = digitPattern.matcher(userInput);
            int maxIndex = taskList.size() - 1;
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                printTaskList(taskList);
            } else if (userInput.startsWith("mark ") && patternMatcher.find()){
                int index = Integer.parseInt(patternMatcher.group(0));
                if (index < taskList.size() && index >= 0) {
                    taskList.get(index).setTaskState(true);
                    System.out.println("Okay Traveller, I've marked this task as done! \n" + taskList.get(index).getTask());
                } else {
                    System.out.println("Sorry Traveller, your input is invalid. Please use a number within 0 and " + maxIndex);
                }
            } else if (userInput.startsWith("unmark ") && patternMatcher.find()){
                int index = Integer.parseInt(patternMatcher.group(0));
                if (index < taskList.size() && index >= 0) {
                    taskList.get(index).setTaskState(false);
                    System.out.println("Okay Traveller, I've marked this task as not done! \n" + taskList.get(index).getTask());
                } else {
                    System.out.println("Sorry Traveller, your input is invalid. Please use a number within 0 and " + maxIndex);
                }
            } else {
                Task newTask = new Task(userInput);
                taskList.add(newTask);
                System.out.println("Okay Traveller, I've added " + userInput + " to your list!");
            }
        }
        goodbye();
    }



    private static void printTaskList(ArrayList<Task> list) {
        int size = list.size();
        System.out.println("Here is  your list so far! \n----->");
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i).getTask());
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

