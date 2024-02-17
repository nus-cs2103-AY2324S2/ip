package duke.ui;

import duke.DukeException;
import duke.configuration.Info;
import javafx.application.Platform;

/**
 * Represents the user interface of the bot.
 */
public class UI {
    /** The main window to take input and show output */
    private MainWindow mainWindow;

    /**
     * Constructs a new user interface.
     *
     * @param name The name of the bot.
     */
    public UI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void exit() {
        Platform.exit();
    }

    /**
     * Shows an error message from the bot.
     *
     * @param e The exception that caused the error.
     */
    public void showBotError(DukeException e) {
        showResponse(new ErrorResponse(e.getBotMessage()));
    }

    /**
     * Shows the welcome message from the bot.
     */
    public void showWelcomeMessage() {
        showResponse(new Response(Info.WELCOME_MESSAGE));
    }

    /**
     * Shows the response from the bot. If the response is null, nothing will be
     * shown.
     *
     * @param response The response from the bot.
     */
    public void showResponse(Response response) {
        if (response == null) {
            return;
        }
        mainWindow.showResponse(response);
    }

    public void showExitMessage() {
        showResponse(new Response(Info.EXIT_MESSAGE));
    }
}
