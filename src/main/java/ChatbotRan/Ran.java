package ChatbotRan;

import java.util.Scanner;

public class Ran {
    TaskList taskList;
    TaskIO taskIO;
    RanUI ui;

    public Ran(TaskIO taskIO) {
        this.taskList = new TaskList(taskIO);
        this.taskIO = taskIO;
        this.ui = new RanUI();
    }

    public static void main(String[] args) {
        TaskIO ti = new TaskIO("data/ran.txt");
        Ran chatbot = new Ran(ti);
        chatbot.run();
    }

    public void run() {
        ui.greet();
        boolean running = true;
        Scanner sc = new Scanner(System.in);

        do {
            ui.line();
            String line = sc.nextLine();
            int space = line.indexOf(' ');
            String command = space == -1 ? line : line.substring(0, space);
            Task task;
            try {
                switch (command) {
                case "mark":
                    task = this.handleTaskNo(line, space);
                    ui.mark(task.isCompleted());
                    task.setCompleted(true);
                    taskList.updateTasks();
                    ui.printTask(task);
                    break;
                case "unmark":

                    task = this.handleTaskNo(line, space);
                    ui.unmark(task.isCompleted());
                    task.setCompleted(false);
                    taskList.updateTasks();
                    ui.printTask(task);
                    break;
                case "delete":
                    task = this.handleTaskNo(line, space);
                    taskList.remove(task);
                    ui.delete(task);
                    ui.printNumber(taskList.size());
                    break;
                case "deadline":
                    Deadline deadline = Deadline.parse(line, space);
                    this.addTask(deadline);
                    break;
                case "todo":
                    Todo todo = Todo.parse(line, space);
                    this.addTask(todo);
                    break;
                case "event":
                    Event event = Event.parse(line, space);
                    this.addTask(event);
                    break;
                default:
                    switch (line) {
                    case "bye":
                        running = false;
                        break;
                    case "list":
                        ui.printTasks(taskList);
                        break;
                    default:
                        ui.unknown();
                    }


                }
            } catch (TaskException e) {
                ui.error(e);
            }
        } while (running);

        ui.bye();
    }


    private Task handleTaskNo(String line, int space) throws TaskException {
        if (space == -1) {
            throw new TaskException("Missing task number.");
        }
        Integer taskNo = Util.parseNumber(line, space);
        if (taskNo == null || taskNo < 1) {
            throw new TaskException("Invalid task number.");
        } else if (taskNo > taskList.size()) {
            throw new TaskException("No task by that number.");
        } else {
            return taskList.get(taskNo - 1);
        }
    }

    private void addTask(Task task) {
        taskList.add(task);
        ui.addTask(task);
        ui.printNumber(taskList.size());

    }

}

