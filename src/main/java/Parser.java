public class Parser {
    private TaskList taskList;
    private UI ui;

    public Parser(TaskList taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public boolean isExit(String input) {
        return input.equals("bye");
    }

    public void parse(String input) throws UnknownInputException {
        int taskEnd = input.indexOf(" ");
        String commandType = taskEnd > 0 ? input.substring(0, taskEnd) : "list";
        TaskType type = TaskType.valueOf(commandType);
        String details = input.substring(taskEnd + 1, input.length());
        switch (type) {
        case todo:
            this.taskList.add(new ToDo(details));
            this.ui.printOnAdd();
            break;
        case deadline:
            String[] d = details.split("/by ");
            this.taskList.add(new Deadline(d[0], d[1]));
            this.ui.printOnAdd();
            break;
        case event:
            String[] v1 = details.split("/from ");
            String[] v2 = v1[1].split("/to ");
            this.taskList.add(new Event(v1[0], v2[0], v2[1]));
            this.ui.printOnAdd();
            break;
        case delete:
            int deleteIndex = Integer.parseInt(details) - 1;
            this.ui.printOnDelete(deleteIndex);
            this.taskList.remove(deleteIndex);
            this.ui.printTotal();
            break;
        case mark:
            int markIndex = Integer.parseInt(details) - 1;
            this.taskList.mark(markIndex);
            this.ui.printOnMark(markIndex);
            break;
        case unmark:
            int unmarkIndex = Integer.parseInt(details) - 1;
            this.taskList.unmark(unmarkIndex);
            this.ui.printOnUnmark(unmarkIndex);
            break;
        case list:
            this.ui.printList();
            break;
        default:
            throw new UnknownInputException("Sorry, I don't know what that command means");
        }


    }

}


