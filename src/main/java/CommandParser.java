import java.util.List;
public class CommandParser {
    public static Action parseCommand(String command, TaskList taskList) {
        String[] words = command.split(" ");

        switch (words[0]) {
            case "bye":
                taskList.goodBye();
                return new Farewell();
            case "list":
                taskList.listTasks();
                return new MyList(taskList);
            case "mark":
                if (words.length > 1) {
                    int index = Integer.parseInt(words[1]) - 1;
                    taskList.markTask(index);
                    return new Mark();
                }
                break;
            case "unmark":
                if (words.length > 1) {
                    int index = Integer.parseInt(words[1]) - 1;
                    taskList.unmarkTask(index);
                    return new Unmark();
                }
                break;
            default:
                Task newTask = new Task(command);
                taskList.addTask(newTask);
                return new Echo(command);
        }

        // Default action if the command is not recognized
        return new Echo("I'm sorry, I didn't understand that command.");
    }
}
