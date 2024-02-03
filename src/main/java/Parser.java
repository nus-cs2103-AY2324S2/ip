import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Parser {
    private Ui ui;


    public Parser() {
        ui = new Ui();
    }

    public String parseCommand(String description) {
        String[] tokens = description.split("/", 2);
        String command = tokens[0].split(" ")[0];
        return command;
    }

    public int indexParser(String description) {
        String[] tokens = description.split("/", 2);
        return Integer.parseInt(tokens[0].split(" ")[1]);
    }

    public String[] todoParser(String description) {
        String[] tokens = description.split(" ");
        String[] res = new String[1];
        String taskName = "";
        if (tokens.length == 1)
            throw new ArrayIndexOutOfBoundsException("The description of a todo cannot be empty.");

        for (int i = 1; i < tokens.length; i++) {
            taskName += tokens[i] + " ";
        }
        res[0] = taskName;
        return res;
    }

    public String[] deadlineParser(String description) {
        String[] tokens = description.split("/", 2);
        String[] tokens2 = tokens[0].split(" ", 2);
        String[] res = new String[2];
        String taskName = "";
        if (tokens2.length == 1)
            throw new ArrayIndexOutOfBoundsException("The description of a deadline cannot be empty.");

        for (int i = 1; i < tokens2.length; i++) {
            taskName += tokens2[i] + " ";
        }
        res[0] = taskName;
        res[1] = tokens[1].split(" ")[1];
        return res;
    }

    public String[] eventParser(String description) {
        String[] tokens = description.split("/", 3);
        String[] tokens2 = tokens[0].split(" ", 2);
        String[] res = new String[2];
        String taskName = "";

        if (tokens2.length == 1)
            throw new ArrayIndexOutOfBoundsException("The description of a deadline cannot be empty.");

        for (int i = 1; i < tokens2.length; i++) {
            taskName += tokens2[i] + " ";
        }
        res[0] = taskName;
        res[1] = tokens[1].split(" ")[1];
        res[2] = tokens[2].split(" ")[1];
        return res;
    }
}
