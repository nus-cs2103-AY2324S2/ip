package duke;


public class Duke {
    //private static ArrayList<duke.Task> list;
    private static Storage storage;
    private static TaskList list;
    private static Ui ui;
    private static Parser parser;
    private static final String FILE_PATH = "data/duke.txt";











    public static void main(String[] args) throws DukeException {
        ui = new Ui();
        ui.intro();

        storage = new Storage(FILE_PATH);
        list = new TaskList(storage);// array to store tasks given
        parser = new Parser(list, storage);



        
        ui.read_commands(parser);



                }
        }




