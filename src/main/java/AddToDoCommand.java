import java.util.ArrayList;

public class AddToDoCommand extends Command {

    private String input;

    public AddToDoCommand(String userInput) {
        this.input = userInput;
    }
    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("Missing the description!");
        }
        String name = "";
        for (int i = 1; i < splitInput.length; i++) {
            name += splitInput[i] + " ";
        }
        ToDo newToDo= new ToDo(name.trim(), false);
        tasks.addTask(newToDo);
        ArrayList<Task> newToDoList = new ArrayList<>();
        newToDoList.add(newToDo);
        storage.writeArrayListToFile(newToDoList, false);
        ui.printAnyStatement("Got it. I've added this task:");
        ui.printAnyStatement(newToDo.toString());
        ui.printAnyStatement("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
