package Yapchit;

import Yapchit.YapchitExceptions.YapchitException;


public class Yapchit {

    enum Operations {
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        TODO,
        DELETE,
        FIND;
    }

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Handler handler;
    private boolean isBye;
    private String filePath;

    public Yapchit(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isBye = false;
        this.parser = new Parser();
        this.handler = new Handler();
        this.filePath = filePath;

        try{
            this.tasks = storage.importFromFile(filePath, ui, handler, parser);
        } catch(YapchitException e){
            ui.printTasklistLoadError();
            this.tasks = new TaskList();
        }
    }

    public void run(){

        ui.printIntro();
        String input = ui.scanInput();
        while(!handler.checkIsBye(input)){
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
        storage.updateFile(filePath, this.tasks);
        ui.printOutro();
    }

    public static void main(String[] args) {

        Yapchit bot = new Yapchit("./src/main/data/dataStore.txt");
        bot.run();
    }

}
