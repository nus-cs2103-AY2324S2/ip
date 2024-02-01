import exceptions.NumeratorException;
import exceptions.storage.LoadingException;

import java.nio.file.Path;
import java.util.Scanner;

public class Numerator {
    private TaskList taskList;
    private final Storage storage;

    public Numerator() {
        this("data/storage.txt");
    }

    public Numerator(String filePath) {
        Path path = Path.of(filePath);
        storage = new Storage(path);

        try {
            taskList = storage.load();
        } catch (LoadingException e) {
            Ui.printLoadingError(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        Ui.printLogo();
        Scanner sc = new Scanner(System.in);

        String input;
        while (true) {
            if (sc.hasNext()) {
                try {
                    input = sc.nextLine();
                    Ui.printLine();
                    boolean exit = Parser.parseArguments(input, taskList, storage);
                    storage.save(taskList);

                    if (exit) {
                        Ui.printExit();
                        sc.close();
                        break;
                    }
                } catch (NumeratorException e) {
                    Ui.printError(e);
                } finally {
                    Ui.printLine();
                }

            } else {
                sc.close();
                break;
            }
        }

    }

    public static void main(String[] args) {
        new Numerator().run();
    }
}

