package ChatbotRan;

import java.util.Scanner;

/**
 * Runs the entire chatbot. Reads from standard input.
 */
public class Ran {
    Parser parser;
    TaskList taskList;
    TaskIo taskIo;
    RanUi ui;

    /**
     * Constructs the object using given input
     *
     * @param taskIo Storage of tasks
     */
    public Ran(TaskIo taskIo) {
        this.taskList = new TaskList(taskIo);
        this.taskIo = taskIo;
        this.ui = new RanUi();
        this.parser = new Parser();
    }

    /**
     * Runs the chatbot on startup.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        TaskIo ti = new TaskIo("data/ran.txt");
        Ran chatbot = new Ran(ti);
        chatbot.run();
    }

    /**
     * Starts the chatbot.
     */
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

