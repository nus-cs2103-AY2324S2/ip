import YapchitExceptions.YapchitException;


public class Yapchit {

    enum Operations {
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        TODO,
        DELETE;
    }

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Handler handler;
    private boolean isBye;

    public Yapchit(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isBye = false;
        this.parser = new Parser();
        this.handler = new Handler();

        try{
            this.tasks = storage.importFromFile(filePath);
        } catch(YapchitException e){
            ui.printTasklistLoadError();
            this.tasks = new TaskList();
        }
    }

    public void run(){
        // !(input.toLowerCase().equals("bye"))
        String input = ui.scanInput();
        while(!isBye){
            try{
                Operations k = parser.parseInputOperation(input);
                String[] parts = parser.parseInputParts(input);
                handler.handleOperation(input, k, tasks, ui, parser);
            } catch (YapchitException e){
                Ui.print(e.getMessage());
            } finally {
                input = ui.scanInput();
            }
        }
        bot.handleFileUpdate(filePath);
        bot.outro();
    }

    public static void main(String[] args) {

        Yapchit bot = new Yapchit("./src/main/data/dataStore.txt");
        bot.run();

    }

}
