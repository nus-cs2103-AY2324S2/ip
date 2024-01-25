package ChatbotRan;

import java.util.ArrayList;
import java.util.Scanner;

public class Ran {
    public static void main(String[] args) {
        System.out.println("Hello. I am ");
        String art = "__________                \n" +
                "\\______   \\_____    ____  \n" +
                " |       _/\\__  \\  /    \\ \n" +
                " |    |   \\ / __ \\|   |  \\\n" +
                " |____|_  /(____  /___|  /\n" +
                "        \\/      \\/     \\/ ";
        System.out.println(art);
        System.out.println("What would you like to do today?");
        boolean running = true;
        ArrayList<Task> tasks = new ArrayList<>(100);
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("____________________________________________________________");
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    running = false;
                    break;
                case "list":
                    if (tasks.isEmpty()) {
                        System.out.println("You haven't got any tasks.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("Task " + i + " : " + tasks.get(i).getContents());
                        }
                    }
                    break;
                default:
                    tasks.add(new Task(command));
                    System.out.println("Added task: " + command);
            }
        } while (running);

        System.out.println("Goodbye, please return soon.");
    }
}

class Task {
    private String contents;
    private boolean completed;

    public Task(String contents) {
        this.contents = contents;
        this.completed = false;
    }
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
