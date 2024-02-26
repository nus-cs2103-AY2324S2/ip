package duke;

import duke.command.Command;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Duke(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        try{
            taskList = new TaskList(storage.load());
            PropertyChangeListener pcs = new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    try {
                        storage.Store(taskList.toString());
                    }catch (DukeException de){
                        //show error message
                    }
                }
            };
//            taskList.addPropertyChangeListener(pcs);
        }catch (DukeException de) {
            ui.showError(de.getMessage());
            taskList = new TaskList();
        }
    }

    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String commandText = ui.readCommand();
                Command command = Parser.parse(commandText);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException de) {
                ui.showError(de.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("/home/shiva/IdeaProjects/cs2103-t9-p2/duke/duke.txt").run();
    }
}