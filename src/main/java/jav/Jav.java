package jav;

import jav.command.Command;
import jav.exception.InvalidCommandException;
import jav.exception.InvalidParamException;
import jav.manager.CommandHistoryManager;
import jav.manager.ParserManager;
import jav.manager.UiManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
* The main program.
*/
public class Jav extends Application {
    public Jav() {}

    @Override
    public void start(Stage stage) {
    }

    /**
     * Recieves input data from user and prints a response.
     *
     * @param s a string containing the user input data.
     * @return a string containing the response.
     */
    public String getResponse(String s) {
        String output = "";

        try {
            String cmd = s;
            String param = "";

            // Check if its a command with parameters
            if (s.indexOf(" ") != -1) {
                cmd = s.substring(0, s.indexOf(' '));
                param = s.substring(s.indexOf(' ') + 1);
            }

            // Parse and execute command
            Command c = ParserManager.getInstance().checkCommand(cmd, param);
            output = c.execute();
            CommandHistoryManager.getInstance().pushCommandToHistory(c);

            // Check if recieved exit command
            if (output == "SHUTDOWN") {
                shutdown();
            }
        } catch (InvalidCommandException e) {
            output = UiManager.getInstance().printInvalidCommand() + "\n" + UiManager.getInstance().echo(s);
        } catch (InvalidParamException e) {
            output = UiManager.getInstance().printInvalidParameters() + "\n" + UiManager.getInstance().echo(s);
        } catch (Exception e) {
            output = "ERRORMSG: " + e.getMessage();
        }

        return output;
    }

    /**
     * Shuts down the program forcefully.
     */
    public void shutdown() {
        Platform.exit();
    }
}
