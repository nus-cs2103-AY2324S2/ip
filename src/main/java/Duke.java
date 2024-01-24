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
        Task newTask;
        if (description.toLowerCase().startsWith("todo")) {
            String[] descriptionArr = description.split(" ");
            newTask = new ToDo(descriptionArr[1]);
        } else if (description.toLowerCase().startsWith("deadline")) {
            String[] descriptionArr = description.split(" ");
            /*if (descriptionArr[2].toLowerCase().equals("/by")) {
                System.out.println("*HONKS ANGRILIY* Pengu thinks that the description of the deadline has to be followed by '/by'");
            }*/
            newTask = new Deadline(descriptionArr[1], descriptionArr[3]);
        } else if (description.toLowerCase().startsWith("event")) {
            String[] descriptionArr = description.split(" ");
            newTask = new Event(descriptionArr[1], descriptionArr[3], descriptionArr[5]);
        }
        else {
            newTask = new Task(description);
        }
        Duke.storage.add(newTask);
        System.out.println( String.format("*Honk! Honk!* Pengu has added this task:\n" + newTask.toString()
                + "\nGet back to work! you have %s tasks in the list\n"
                + "―――――――――――――――――――――――――――――――――――", Duke.storage.size()));
    }

    private static void listTasks() {
        int storageSize = Duke.storage.size();
        System.out.println("*Honk!* Pengu has listed your current tasks below:");
        for (int k = 0; k < storageSize; k++){
            int curr = k + 1;
            Task currTask = storage.get(k);
            System.out.println(curr + ". " + currTask.toString());
        }
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void markTask(int index) {
        Task currTask = storage.get(index);
        currTask.updateTask(true);
        System.out.println("*Honk!* Good Job!, Pengu has marked this task as done:\n" + currTask.toString());
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void unmarkTask(int index) {
        Task currTask = storage.get(index);
        currTask.updateTask(false);
        System.out.println("*Honk!* Pengu has marked this task as not done yet:\n" + currTask.toString());
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
                String[] inputArr = userInput.split(" ");
                int index = Integer.parseInt(inputArr[1]) - 1;
                markTask(index);
                continue;
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                String[] inputArr = userInput.split(" ");
                int index = Integer.parseInt(inputArr[1]) - 1;
                unmarkTask(index);
                continue;
            }
            addTask(userInput);
        }
    }
}
