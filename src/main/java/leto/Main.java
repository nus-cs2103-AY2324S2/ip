package leto;

import leto.parser.Parser;
import leto.tasklist.TaskList;

import static leto.ui.Ui.letoLogo;

public class Main {
    public static void main(String[] args) {
        letoLogo();
        TaskList.initFromFile();
        Parser.readCommandAndExecute();
    }
}
