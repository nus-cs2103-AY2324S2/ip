package duke;

import duke.command.Command;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.Scanner;
public class Duke {
    private Parser parser;
    private Ui ui;
    private TaskList taskList;

    public Duke(){
        this.parser = new Parser();
        this.ui = new Ui();
        this.taskList = new TaskList();
    }
    public void run(){
        this.ui.greet();
        Scanner scanner = new Scanner(System.in);
        try {
            Command command;
            do{
                command = this.parser.parse(scanner.nextLine());
                command.run(this.taskList);
            }while(!command.getType().equals(Parser.Cmd.bye));
        }catch(IllegalArgumentException e){
            Ui.informInvalidCommand();
        }
    }
    public static void main(String[] args) {
        Duke Alfred = new Duke();
        Alfred.run();
    }
}
