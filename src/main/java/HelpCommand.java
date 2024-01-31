import java.util.*;
import java.io.*;

public class HelpCommand extends Command {

    @Override
    public void execute() {
        Ui.helpMessage();
    }
}
