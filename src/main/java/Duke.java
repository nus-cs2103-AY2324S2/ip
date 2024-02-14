import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static TaskList tasks;
    private Data data;
    private Command currentCommand;

    private Input input;

    Duke() {
        this.tasks = new TaskList();
        this.data = new Data(this.tasks);
        this.input = new Input();

    }

    public void run() throws DukeException{
        this.data.createFile();
        this.data.load(tasks);

        Command currentCommand = Command.HELLO;
        currentCommand.execute();
        

        boolean exit = false;

        while (!exit) {
            String str = input.getNextLine();
            String commandString = input.getCommandString(str);
            Command command = input.getCommand(commandString);

            try {
                command.execute(tasks, str);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }


            if (command == Command.BYE) {
                this.data.write(tasks);
                exit = true;
            }

        }
    }


    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        duke.run();
    }






}
