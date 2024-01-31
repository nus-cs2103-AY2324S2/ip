import java.util.Arrays;

public class Parser {
    private boolean isExit;
    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui){
        this.isExit = false;
        this.tasks = tasks;
        this.ui = ui;
    }
    public int finder(String checker, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(checker)) {
                return i;
            }
        }
        return -1;
    }
    public Task identify(String request) throws TaskException {
        if (request.startsWith("todo")) {
            String[] reqList = request.split(" ");
            if (reqList.length < 2) {
                throw new TaskException("What do you want to do? Description of todo cannot be empty.");
            }
            String desc = String.join(" ", Arrays.copyOfRange(reqList, 1, reqList.length));
            Todo current = new Todo(desc);
            return current;

        } else if (request.startsWith("deadline")) {
            String[] reqList = request.split(" ");
            if (Arrays.asList(reqList).contains("/by")) {
                int byIndex = finder("/by", reqList);
                String desc = String.join(" ", Arrays.copyOfRange(reqList, 1, byIndex));
                String time = String.join(" ", Arrays.copyOfRange(reqList, byIndex + 1, reqList.length));
                Deadline current = new Deadline(desc, time);
                return current;
            } else{
                throw new TaskException("Please specify when is the deadline.");
            }

        } else if (request.startsWith("event")) {
            String[] reqList = request.split(" ");
            if (Arrays.asList(reqList).contains("/from") && Arrays.asList(reqList).contains("/to")){
                int fromIndex = finder("/from", reqList);
                int toIndex = finder("/to", reqList);
                String desc = String.join(" ", Arrays.copyOfRange(reqList, 1, fromIndex));
                String start = String.join(" ", Arrays.copyOfRange(reqList, fromIndex + 1, toIndex));
                String end = String.join(" ", Arrays.copyOfRange(reqList, toIndex + 1, reqList.length));

                Event current = new Event(desc, start, end);
                return current;
            } else if (Arrays.asList(reqList).contains("/from")){
                throw new TaskException("Please specify when the event ends.");
            } else if (Arrays.asList(reqList).contains("/to")){
                throw new TaskException("Please specify when the event starts.");
            } else {
                throw new TaskException("Please specify the event timeframe.");
            }

        } else {
            throw new TaskException("Apologies, I don't understand you. Please try again");
        }
    }

    public void read(String current){
        if(current.equals("bye")) {
            ui.bye();
            this.isExit = true;
        } else if(current.equals("list")) {
            this.ui.showList(tasks.showList());
        } else if (current.startsWith("mark")) {
            String[] marking = current.split(" ");
            int position = Integer.parseInt(marking[1]) - 1;
            Task curr = tasks.getTask(position);
            curr.makeDone();
            ui.markTask(curr);
        } else if (current.startsWith("unmark")) {
            String[] marking = current.split(" ");
            int position = Integer.parseInt(marking[1]) - 1;
            Task curr = tasks.getTask(position);
            curr.makeUndone();
            ui.unmarkTask(curr);
        } else if (current.startsWith("delete")){
            String[] marking = current.split(" ");
            int position = Integer.parseInt(marking[1]) - 1;
            ui.delete(position, tasks);
        } else {
            try {
                Task newTask = identify(current);
                tasks.addTask(newTask);
                ui.addTask(newTask, tasks);
            } catch (TaskException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public boolean isExit(){
        return this.isExit;
    }
}
