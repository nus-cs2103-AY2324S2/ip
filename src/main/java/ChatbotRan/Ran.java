package ChatbotRan;

import java.util.Scanner;

public class Ran {
    Parser parser;
    TaskList taskList;
    TaskIo taskIo;
    RanUi ui;

    public Ran(TaskIo taskIo) {
        this.taskList = new TaskList(taskIo);
        this.taskIo = taskIo;
        this.ui = new RanUi();
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        TaskIo ti = new TaskIo("data/ran.txt");
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

