package ChatbotRan;

import java.util.Scanner;

public class Ran {
    Parser parser;
    TaskList taskList;
    TaskIO taskIO;
    RanUI ui;

    public Ran(TaskIO taskIO) {
        this.taskList = new TaskList(taskIO);
        this.taskIO = taskIO;
        this.ui = new RanUI();
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        TaskIO ti = new TaskIO("data/ran.txt");
        Ran chatbot = new Ran(ti);
        chatbot.run();
    }

    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        do {
            ui.line();
            try {
                parser.exec(sc.nextLine(),taskList,ui);
            } catch (TaskException e) {
                ui.error(e);
            }
        } while (parser.running());

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

