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
            String line = sc.nextLine();
            int space = line.indexOf(' ');
            String command = space == -1 ? line : line.substring(0, space);
            switch (command) {
                case "mark":
                    if (space == -1) {
                        System.out.println("Missing task number");
                    }
                    Integer taskNo = parseNumber(line, space);

                    if (taskNo == null || taskNo < 1) {
                        System.out.println("Invalid task number.");
                    } else if (taskNo > tasks.size()) {
                        System.out.println("No task by that number.");
                    } else {
                        tasks.get(taskNo - 1).setCompleted(true);
                        System.out.println("Alright. I have marked this task as complete: ");
                        System.out.println(tasks.get(taskNo - 1));
                    }
                    break;
                case "unmark":
                    if (space == -1) {
                        System.out.println("Missing task number");
                    }
                    taskNo = parseNumber(line, space);

                    if (taskNo == null || taskNo < 1) {
                        System.out.println("Invalid task number.");
                    } else if (taskNo > tasks.size()) {
                        System.out.println("No task by that number.");
                    } else {
                        tasks.get(taskNo - 1).setCompleted(false);
                        System.out.println("If that's the case, I'll set that task as incomplete.");
                        System.out.println(tasks.get(taskNo - 1));
                    }
                    break;
                default:
                    switch (line) {
                        case "bye":
                            running = false;
                            break;
                        case "list":
                            if (tasks.isEmpty()) {
                                System.out.println("You haven't got any tasks.");
                            } else {
                                for (int i = 0; i < tasks.size(); i++) {
                                    System.out.println("Task " + (i + 1) + ":" + tasks.get(i));
                                }
                            }
                            break;
                        default:
                            tasks.add(new Task(line));
                            System.out.println("Added task: " + line);
                    }


            }
        } while (running);

        System.out.println("Goodbye, please return soon.");
    }


    private static Integer parseNumber(String line, int spacePos) {
        try {
            return Integer.parseInt(line.substring(spacePos + 1));
        } catch (NumberFormatException e) {
            return null;
        }
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

    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.contents;
    }
}
