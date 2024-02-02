package duke;

public class Parser {
    /**
     * Handles user input and performs corresponding actions
     *
     * @param input
     * @return true if exit command is given, otherwise false
     */
    public static boolean handleInput(String input, Ui ui, TaskList taskList) {
        String[] splitInput = input.split(" ", 2);
        String keyword = splitInput[0];
        String content = splitInput.length > 1 ? splitInput[1] : "";

        switch (keyword) {
            case "list":
                ui.print(taskList.toString());
                break;
            case "bye":
                ui.print("ok see you bro");
                return true;
            case "mark": {
                Task task = taskList.get(Integer.parseInt(content) - 1);
                task.markDone(true);
                ui.print("good job bro, marked this task as done:\n" + task.toString());
                break;
            }
            case "unmark": {
                Task task = taskList.get(Integer.parseInt(content) - 1);
                task.markDone(false);
                ui.print("ok i help you unmark this task ah:\n" + task.toString());
                break;
            }
            case "delete": {
                Task task = taskList.remove(Integer.parseInt(content) - 1);
                ui.print("ok deleted this one:\n" + task.toString());
                break;
            }
            case "todo": {
                try {
                    Task task = new TodoTask(content);
                    taskList.add(task);
                    ui.print("added this task for you liao:\n" + task.toString());
                } catch (DukeException e) {
                    ui.print(e.toString());
                }

                break;
            }
            case "deadline": {
                String[] splitContent = content.split(" /by ");
                String eventName = splitContent[0];
                String deadline = splitContent.length > 1 ? splitContent[1] : "";
                try {
                    Task task = new DeadlineTask(eventName, deadline);
                    taskList.add(task);
                    ui.print("added this task for you liao:\n" + task.toString());
                } catch (DukeException e) {
                    ui.print(e.toString());
                }
                break;
            }
            case "event": {
                String[] splitContent = content.split(" /from ");
                String eventName = splitContent[0];
                String temp = splitContent.length > 1 ? splitContent[1] : "";

                splitContent = temp.split(" /to ");
                String from = splitContent[0];
                String to = splitContent.length > 1 ? splitContent[1] : "";

                try {
                    Task task = new EventTask(eventName, from, to);
                    taskList.add(task);
                    ui.print("added this task for you liao:\n" + task.toString());
                } catch (DukeException e) {
                    ui.print(e.toString());
                }
                break;
            }
            default:
                ui.print("what are u saying");
                break;
        }
        return false;
    }
}
