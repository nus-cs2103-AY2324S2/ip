package gulie;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * A simple task manager.
 */
public class Gulie {
    private GulieTasklist tasklist;
    private final static String savepath = "./data/Gulie.txt";
    private GulieUi ui;
    private GulieStorage storage;
    private GulieParser parser;

    public Gulie(InputStream input, PrintStream output) {
        ui = new GulieUi(input, output);
        storage = new GulieStorage(ui, "./data/Gulie.txt");
        parser = new GulieParser();
        tasklist = storage.load();
        ui.greet();
    }

    /**
     * Starts the manager.
     */
    public void run() {
        String inp = ui.getInput();
        while (!inp.equals("bye")) {
            try {
                Command command = parser.parse(inp);
                command.run(ui, storage, tasklist);
            } catch (GulieException ge) {
                ui.error(ge);
            }
            inp = ui.getInput();
        }
        try {
            storage.save(tasklist);
        } catch (GulieException ge) {
            ui.error(ge);
        }
        ui.farewell();
    }

    public static void main(String[] args) {
        new Gulie(System.in, System.out).run();
    }
}
