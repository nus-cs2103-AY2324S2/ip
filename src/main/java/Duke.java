import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    protected Storage storage;
    protected TaskList tasks = new TaskList();
    String line = "_______________________________________________________\n";
    protected Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch(FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean isExit = false;
        ui.greet();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ThamesException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.bye();

//        while (true) {
//            try {
//                String input = ui.next();
//                if (input.equals("bye")) break;
//                else if (input.equals("list")) {
//                    list();
//                } else if (input.equals("mark")) {
//                    int index = ui.nextInt();
//                    if (index > taskList.size() || index <= 0) throw new Exception("Index has to be within list size!");
//                    Task task = taskList.get(index - 1);
//                    task.mark();
//                    try {
//                        storage.save(taskList);
//                    } catch (IOException e) {
//
//                    }
//                    ui.mark(task);
//                } else if (input.toLowerCase().equals("unmark")) {
//                    int index = ui.nextInt();
//                    if (index > taskList.size() || index <= 0) throw new Exception("Index has to be within list size!");
//                    Task task = taskList.get(index - 1);
//                    task.unmark();
//
//                    try {
//                        storage.save(taskList);
//                    } catch (IOException e) {
//
//                    }
//                    ui.unmark(task);
//                } else if (input.toLowerCase().equals("delete")) {
//                    int index = ui.nextInt();
//                    if (index > taskList.size() || index <= 0) throw new Exception("Index has to be within list size!");
//                    Task task = taskList.remove(index - 1);
//                    try {
//                        storage.save(taskList);
//                    } catch (IOException e) {
//
//                    }
//                    ui.delete(task, taskList.size());
//                } else if (input.toLowerCase().equals("todo")) {
//                    String str = ui.nextLine().trim();
//                    if (str.length() == 0) throw new Exception("Todo task cannot be empty!\n");
//                    addTodo(str);
//                } else if (input.toLowerCase().equals("deadline")) {
//                    String s = ui.nextLine();
//                    if (s.split("/by").length != 2) {
//                        throw new Exception("Please provide your deadline task in the following format:\n" +
//                                "Deadline <description> /by <description>\n");
//                    }
//                    String name = s.split("/by")[0].trim();
//                    String by = s.split("/by")[1].trim();
//                    if (name.length() == 0 || by.length() == 0 ) {
//                        throw new Exception(("Event names/to/from cannot be empty\n"));
//                    }
//
//                    addDeadline(name, by);
//                } else if (input.toLowerCase().equals("event")) {
//                    String s = ui.nextLine();
//                    String[] split1 = s.split("/from");
//                    if (split1.length != 2) {
//                        throw new Exception("Please provide your event task in the following format:\n" +
//                                "Event <description> /from <description> /to <description>\n");
//                    }
//                    String[] split2 = split1[1].split("/to");
//                    if (split2.length != 2) {
//                        throw new Exception("Please provide your event task in the following format:\n" +
//                                "Event <description> /from <description> /to <description>\n");
//                    }
//                    String name = split1[0].trim();
//                    String from = split2[0].trim();
//                    String to = split2[1].trim();
//                    if (name.length() == 0 || from.length() == 0 || to.length() == 0) {
//                        throw new Exception(("Event names/to/from cannot be empty\n"));
//                    }
//
//
//                    addEvent(name, from, to);
//                } else {
//                    throw new Exception("Sorry! I do not understand what this means!\n");
//                }
//            } catch (Exception e) {
//                System.out.println(line + e.getMessage() +
//                        line);
//            }
//        }
    }


    public static void main(String[] args) throws IOException {
        new Duke("./data/tasks.txt").run();
    }
}
