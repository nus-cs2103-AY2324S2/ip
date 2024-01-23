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

    private static ArrayList<String> storage = new ArrayList<>();
    private static void greeting() {
        System.out.println("Hello! I'm Pengu\n" + "What can I do for you? \n" + Duke.logo +
                "\nDid you know that the noise penguins make are called \"honks\"");
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon! **HONK HONK**");
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void addStorage(String text){
        Duke.storage.add(text);
    }

    private static void listStorage(){
        int storageSize = Duke.storage.size();
        for (int k = 0; k < storageSize; k++){
            int curr = k + 1;
            System.out.println(curr + ". " + Duke.storage.get(k));
        }
    }

    public static void main(String[] args) {
        greeting();
        Scanner s = new Scanner(System.in);
        while (true){
            String userInput = s.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                exit();
                break;
            } else if (userInput.toLowerCase().equals("list")){
                listStorage();
                continue;
            }
            addStorage(userInput);
            System.out.println("Added: " + userInput + "\n*Honk! Honk!*\n" +
                    "―――――――――――――――――――――――――――――――――――");
        }
    }
}
