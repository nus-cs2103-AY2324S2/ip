package skyler.main;

import skyler.exception.SkylerException;

public class Skyler {

    public Skyler() {
        System.out.println("Executing load from local");
        Storage.loadTasksFromFile();
    }

    public String getResponse(String userInput) throws SkylerException {
        if (userInput.equals("bye")) {
            Ui.getByeMessage();
            Storage.saveTasksToFile();
            return "Skyler: Bye. Hope to see you again soon!\n";
        }
        return Parser.processUserInput(userInput);
    }
}