package asher;

public class Asher {
    public Asher() {
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Asher heard: " + input;
    }

    /*private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    public Asher(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        parser = new Parser(ui, tasks);
    }

    public void run() {
        String dataFile = "./taskLists.txt";
        storage.getFileContents(dataFile, tasks);
        Scanner scanner = new Scanner(System.in);

        ui.showWelcomeMessage();

        try {
            String input;
            do {
                input = scanner.nextLine();
                parser.parseCommand(input);
            } while (!input.equals("bye"));
        } catch (BotException e) {
            System.out.println("Error: " + e.getMessage());
        }
        storage.writeToFile(tasks);
        ui.showExitMessage();
    }*/
}