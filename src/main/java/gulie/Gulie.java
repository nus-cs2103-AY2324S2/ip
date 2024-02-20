package gulie;

import gulie.gui.GulieApp;
import javafx.application.Application;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * A simple task manager.
 */
public class Gulie {

    public static final String NAME = "Güliedistodiez";
    private GulieTasklist tasklist;
    private final static String savepath = "./data/Gulie.txt";
    private GulieUi ui;
    private GulieStorage storage;
    private GulieParser parser;

    public Gulie(GulieInterface gulieInterface) {
        ui = new GulieUi(gulieInterface);
        storage = new GulieStorage(ui, "./data/Gulie.txt");
        parser = new GulieParser();
        tasklist = storage.load();
        ui.printGreet();
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
        ui.printFarewell();
    }

    private static void launchTextGulie() {
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

    public static void main(String[] args) {
        Application.launch(GulieApp.class, args);
    }
}
