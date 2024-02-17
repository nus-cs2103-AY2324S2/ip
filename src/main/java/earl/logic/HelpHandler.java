package earl.logic;

import earl.util.TaskList;
import earl.util.Ui;

/**
 * Class responsible for displaying list of available commands.
 */
public class HelpHandler extends Handler {

    public HelpHandler(String args) {
        super(args);
    }

    @Override
    public void handle(TaskList tasks, Ui ui) {
        if (!args.isEmpty()) {
            ui.buildResponse("Input defies parsing: " + args);
        }
        ui.buildResponse("Pray, issue a command forthwith.");
        ui.buildResponse("The sanctioned commands unfold:");
        for (HandlerType type : HandlerType.values()) {
            ui.buildResponse("-" + type.name().toLowerCase());
        }
        ui.completeResponse();
    }
}
