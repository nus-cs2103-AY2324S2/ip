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
                parser.exec(sc.nextLine(), taskList, ui);
            } catch (TaskException e) {
                ui.error(e);
            }
        } while (parser.running());

        ui.bye();
    }

}

