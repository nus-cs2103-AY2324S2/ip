import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static ChatBot createPaimonChatBot() {
        String logo = "      ___          ___                     ___          ___          ___     \n"+
                "     /\\  \\        /\\  \\         ___       /\\__\\        /\\  \\        /\\__\\    \n"+
                "    /::\\  \\      /::\\  \\       /\\  \\     /::|  |      /::\\  \\      /::|  |   \n"+
                "   /:/\\:\\  \\    /:/\\:\\  \\      \\:\\  \\   /:|:|  |     /:/\\:\\  \\    /:|:|  |   \n"+
                "  /::\\~\\:\\  \\  /::\\~\\:\\  \\     /::\\__\\ /:/|:|__|__  /:/  \\:\\  \\  /:/|:|  |__ \n"+
                " /:/\\:\\ \\:\\__\\/:/\\:\\ \\:\\__\\ __/:/\\/__//:/ |::::\\__\\/:/__/ \\:\\__\\/:/ |:| /\\__\\\n"+
                " \\/__\\:\\/:/  /\\/__\\:\\/:/  //\\/:/  /   \\/__/~~/:/  /\\:\\  \\ /:/  /\\/__|:|/:/  /\n"+
                "      \\::/  /      \\::/  / \\::/__/          /:/  /  \\:\\  /:/  /     |:/:/  / \n"+
                "       \\/__/       /:/  /   \\:\\__\\         /:/  /    \\:\\/:/  /      |::/  /  \n"+
                "                  /:/  /     \\/__/        /:/  /      \\::/  /       /:/  /   \n"+
                "                  \\/__/                   \\/__/        \\/__/        \\/__/    \n";
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

    private static ChatBot createPaimonChatBotForTesting() {
        String logo = "logo";
        String botName = "Paimon";
        List<String> greetings = List.of("Ad astra abyssosque, welcome to Paimon's house!");
        List<String> farewells = List.of("Safe travels, and take care!");
        return new ChatBot(botName, logo, greetings, farewells);
    }


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        storage.loadTasksFromFileToTaskList(tasks);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(storage, ui, tasks);
                isExit = c.isExit();
            } catch (ChatBotParameterException | ChatBotCommandException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }


}
