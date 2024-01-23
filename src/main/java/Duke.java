import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣴⠶⠶⣦⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⡾⠟⠋⠉⠀⠀⠀⠀⠀⠀⠈⠉⠛⠿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣧⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣷⡀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⢸⡏⠀⠀⠀⠀⠀⣠⣾⠟⠛⠛⠛⠻⢶⣄⠀⠀⠀⠀⠀⠀⣴⡾⠛⠛⢿⣧⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⣾⠁⠀⠀⠀⢀⣾⠏⠀⠀⠀⠀⠀⠀⠀⠙⣷⡀⠀⠀⠀⢸⡟⠀⠀⠀⠈⣿⡆⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⣼⠏⠀⠀⠀⠀⠀⣠⣤⡀⠀⠘⣷⣤⣶⢶⣿⣇⠀⣖⣶⡆⠘⣿⡀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⢠⣿⠀⠀⠀⠀⠀⠸⣧⣿⣿⠀⣼⠏⠁⠀⠀⠀⠹⣧⠙⠛⠁⠀⣿⣧⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠈⣿⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⢻⣿⣶⠶⣶⣶⣿⠏⠀⠀⠀⣰⣿⡏⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⢰⡟⠀⠀⠀⠀⠹⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠋⠉⠀⠀⠀⣠⣾⠋⢹⡆⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⢀⣿⠃⠀⠀⠀⠀⠀⠙⢷⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠟⠻⢷⣄⢻⣦⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⢠⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⣻⣶⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣷⡙⢷⣄⠀\n" +
            "⠀⠀⠀⠀⢠⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⠃⠈⢻⣆\n" +
            "⠀⠀⠀⢠⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⣦⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠏⠀⠀⠀⣿\n" +
            "⠀⠀⠀⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡟⠀⠀⠀⢠⣿\n" +
            "⠀⠀⢰⡟⠀⠀⠀⠀⠀⠀⠠⣤⣄⠀⠀⠀⠀⠀⠀⠀⠀⠹⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⣾⠃\n" +
            "⠀⠀⢸⣷⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣦⣀⠀⠀⠀⠀⠀⠀⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡇⠀⣠⣾⠏⠀\n" +
            "⢀⣴⡿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡿⠻⢷⣄⡀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⠿⣿⠃⠀⠀\n" +
            "⣿⡏⠀⢹⣧⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠙⠿⣦⣤⣤⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⢿⣦⠀⠀\n" +
            "⠙⢿⣦⣀⠻⣧⡀⠀⠀⠀⠀⠀⢠⣼⣷⣄⠀⣀⣀⡀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⠏⢰⡿⢶⡆\n" +
            "⠀⠀⠉⠙⠛⠿⠿⣦⣀⢠⣶⣶⡿⠁⠀⠹⣿⠋⠉⢿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡿⠃⠀⠀⣠⡾⠃\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠈⠻⢿⣿⡦⠀⠀⠀⠀⠀⠀⠀⢸⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣿⣋⣀⣤⣴⠟⠋⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣦⣀⠀⠀⠀⠀⣰⡿⠁⠀⠀⠀⠀⠀⢀⣀⣠⣴⠿⠛⠉⠙⠛⠉⠉⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠶⠶⠟⠛⠛⠛⠛⠛⠛⠛⠛⠋⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠘⠦⣠⠀⠀⠠⠀⡀⠀⠀⣘⢦⠀⢤⡄⠀⠈⠡⡄⡀⠀⡀⣄⠈⠀⠀⠐⠀⠀⠀⠈⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠐⠶⠠⠄⠠⠀⠀⠤⠀⠤⠄⠀⡰⡉⠈⠱⠘⠀⡄⣀⠀⠀⠀⠠⠀⠭⠰⠶⠤⠆⠰⡶⠘⢤⠄⠀⢀⠀";

    private static ArrayList<Task> storage = new ArrayList<>();
    private static void greeting() {
        System.out.println("Hello! I'm Pengu\n" + "What can I do for you? \n" + Duke.logo +
                "\nDid you know that the noise penguins make are called \"honks\"");
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon! **HONK HONK**");
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void addTask(String description) {
        Task newTask = new Task(description);
        Duke.storage.add(newTask);
    }

    private static void listTasks() {
        int storageSize = Duke.storage.size();
        System.out.println("*Honk!* Pengu has listed your current tasks below:");
        for (int k = 0; k < storageSize; k++){
            int curr = k + 1;
            System.out.println(curr + ". " + Duke.storage.get(k).getStatusIcon() + " "
                    + Duke.storage.get(k).getDescription());
        }
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void markTask(int index) {
        Task currTask = storage.get(index);
        currTask.updateTask(true);
        System.out.println("*Honk!* Good Job!, Pengu has marked this task as done:\n" + currTask.getStatusIcon() + " "
                + currTask.getDescription());
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void unmarkTask(int index) {
        Task currTask = storage.get(index);
        currTask.updateTask(false);
        System.out.println("*Honk!* Pengu has marked this task as not done yet:\n" + currTask.getStatusIcon() + " "
                + currTask.getDescription());
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    public static void main(String[] args) {
        greeting();
        Scanner s = new Scanner(System.in);
        while (true){
            String userInput = s.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                exit();
                break;
            } else if (userInput.toLowerCase().equals("list")) {
                listTasks();
                continue;
            } else if (userInput.toLowerCase().startsWith("mark")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                markTask(index);
                continue;
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                unmarkTask(index);
                continue;
            }
            addTask(userInput);
            System.out.println("Added: " + userInput + "\n*Honk! Honk!*\n" +
                    "―――――――――――――――――――――――――――――――――――");
        }
    }
}
