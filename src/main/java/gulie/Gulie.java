package gulie;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * A simple task manager.
 */
public class Gulie {
    private GulieTasklist tasklist;
    private final static String savepath = "./data/Gulie.txt";
    private GulieTextUi ui;
    private GulieStorage storage;
    private GulieParser parser;

    public Gulie(GulieInterface gulieInterface) {
        ui = new GulieTextUi(gulieInterface);
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
        final Scanner in = new Scanner(System.in);
        final PrintStream out = System.out;
        new Gulie(new GulieInterface() {
            final String LINE = "____________________________________________________________";
            @Override
            public String getString() {
                return in.nextLine();
            }

            @Override
            public void print(String str) {
                out.println(LINE);
                out.println(str);
                out.println(LINE);
            }

            @Override
            public void close() {
                in.close();
                out.close();
            }

            @Override
            public boolean isOpen() {
                return in.hasNextLine();
            }
        }).run();
    }
}
