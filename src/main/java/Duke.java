import java.util.List;
import java.util.Scanner;

public class Duke {
    private static ChatBot createPaimonChatBot() {
        String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⣴⣦⡀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"+
                "⠀⠀⠀⠀⠀⢠⡄⠀⢿⠀⠘⠃⠀⢼⡏⢸⡷⠀⠘⠃⠀⡿⠀⢠⡄⠀⠀⠀⠀⠀\n"+
                "⠀⠀⠀⢲⣆⠀⠋⠀⣠⡴⠶⠖⠛⠛⠛⠛⠛⠛⠳⠶⢦⣤⡀⠘⠁⢠⡶⠀⠀⠀\n"+
                "⠀⠀⠀⠀⠀⠀⠀⠘⢷⣤⣴⣾⡿⢻⡟⢻⡟⢿⣿⣶⣤⡼⠇⠀⠀⠀⠀⠀⠀⠀\n"+
                "⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠉⠉⠁⢾⡃⢘⣷⠈⠉⠉⠻⣦⡀⠀⠀⠀⠀⠀⠀⠀\n"+
                "⠀⠀⠀⠀⠀⠀⢠⡾⠃⠀⠀⠀⠀⠈⢻⡾⠁⠀⠀⠀⠀⠈⢻⣄⠀⠀⠀⠀⠀⠀\n"+
                "⠀⠀⠀⠀⠀⣴⠏⠀⠀⠀⠀⣶⡀⠀⠀⢠⡀⠀⢀⣄⠀⠀⠀⠙⣧⡀⠀⠀⠀⠀\n"+
                "⠰⣶⣤⠶⠞⠃⠀⢀⣤⠄⢸⡟⢷⡀⠀⢸⡿⣦⡀⠉⠁⣄⡀⠀⠈⠻⠶⣤⣴⠆\n"+
                "⠀⠈⠙⢻⡟⠛⠃⠈⠁⠀⣿⣀⡈⠛⢶⣤⣿⣌⣻⣦⡀⠈⠙⠛⠛⢻⡟⠋⠁⠀\n"+
                "⠀⠀⠀⣸⡇⠀⠀⠀⠀⢸⣿⠋⢻⡆⠀⠀⠉⣿⠉⣿⣧⠀⠀⠀⠀⠘⣷⠀⠀⠀\n"+
                "⠀⠀⠀⣿⠀⢰⡇⠀⠀⣿⠙⠳⠞⣡⣤⣤⣤⠛⠶⠋⣿⠀⠀⢸⡇⠀⢿⡄⠀⠀\n"+
                "⠀⠀⠀⣿⠀⠘⣧⠀⠀⢻⣆⠀⠀⢻⣄⣠⡿⠀⠀⢠⡿⠀⠀⣸⡇⠀⣸⠇⠀⠀\n"+
                "⠀⠀⠀⠹⣆⠀⠹⣦⣀⠀⠙⣷⣶⣤⣀⣀⣠⣴⣾⠛⠁⣀⣴⠟⠀⣠⡟⠀⠀⠀\n"+
                "⠀⠀⠀⠀⠙⠷⣤⣀⡙⠿⣿⣭⡅⠀⠉⠋⠁⢀⣭⣿⠿⠋⣁⣠⡾⠋⠀⠀⠀⠀\n"+
                "⠀⠀⠀⠀⠀⠀⠈⠉⠙⠛⠉⠉⠀⠀⠀⠀⠀⠀⠉⠉⠛⠋⠉⠁⠀⠀⠀⠀⠀⠀\n";
        String botName = "Paimon";
        List<String> greetings = List.of(
                "Ah, there you are! Hello! Paimon wondered where you were! This is going to be so much fun, right?",
                "Ahoy there! It's great to see you! Paimon's hungry!",
                "Ah, Paimon missed you! It's been so long...",
                "Ad astra abyssosque, welcome to Paimon's house!",
                "Good morning, Traveler. Ah... what's it like out today? Paimon wants to hear your story."
        );

        List<String> farewells = List.of(
                "Farewell, it was fun to meet you! Take care, see you later, and may you find many new treasures",
                "Farewell, until we meet again!",
                "Safe travels, and take care!",
                "Good luck! And don't spend all your Mora in one place.",
                "Adios!"
        );

        return new ChatBot(botName, logo, greetings, farewells);
    }
    public static void main(String[] args) {
        ChatBot Paimon = createPaimonChatBot();
        Paimon.greet();
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            command = scanner.nextLine();
            switch (command) {
                case "list":
                    Paimon.listTasks();
                    break;
                case "bye":
                    Paimon.bye();
                    return;
                default:
                    Paimon.addTask(command);
            }
        }
    }


}
