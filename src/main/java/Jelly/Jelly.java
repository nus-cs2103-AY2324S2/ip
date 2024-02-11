package Jelly;

//enum is not required
public class Jelly {

    private static TaskList list;
    private static Storage storage;
    private static Parser parser;
    private static Ui ui;
    private static String line = "\n-------------------------------------------";

    private static String welcome = "(ᵔ_ᵔ) Hello! I'm Jelly\nWhat can I do for you?";
    private static String farewell = "(•︿•) Bye. Hope to see you again soon!";

    private static String path = "jelly.txt";

    public static void main(String[] args) {

        storage = new Storage(path);
        ui = new Ui();
        try {
            list = new TaskList(storage.load());
        } catch (JellyException e) {
            list = new TaskList();
            ui.printLoadingError(e);
        }
        parser = new Parser(list, ui);

        ui.printLine();
        ui.printWelcomeMessage();
        ui.printLine();

        parser.loop();

        ui.printFarewellMessage();
        ui.printLine();

        storage.save(list);
    }

}
