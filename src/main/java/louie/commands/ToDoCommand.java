package louie.commands;

import louie.Louie;
import louie.LouieException;
import louie.Parser;
import louie.tasks.Task;
import louie.tasks.ToDo;

public class ToDoCommand extends Command {

    private static final String SUCCESS_MSG = "Ok, I've added a new todo...\n  %s";

    @Override
    public void run(Parser parser, Louie louie) throws LouieException {
        String name = parser.nextUntilOption();
        parser.assertEnd();
        Task t = new ToDo(name);
        louie.getUi().print(String.format(SUCCESS_MSG, t.describe()));
        louie.getTasks().add(t);
        louie.getStorage().writeTasks(louie.getTasks());
    }
}
