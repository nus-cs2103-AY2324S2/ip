package lumiere.lumiere;

import java.util.Scanner;
import java.io.IOException;

public class Ui {

    /**
     * An instance method that gets the program started.
     * 
     * @param tasks   The list of tasks (TaskList object) that might be used to
     *                execute certain user commands.
     * @param storage The storage object that might be used to execute certain user
     *                commands.
     * @throws IOException If addTask throws an exception.
     */
    public void run(TaskList tasks, Storage storage) throws IOException {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String nextTask = scanner.nextLine();
            if (nextTask.startsWith("list")) {
                System.out.println("Here are the tasks in your list:");
                tasks.printList();
            } else if (nextTask.startsWith("bye")) {
                exit();
                break;
            } else {
                tasks.addTask(nextTask, storage);
            }
        }
        scanner.close();
    }

    /**
     * An instance method that prints a welcome greeting.
     */
    public void greet() {
        System.out.println("Hello! I'm Lumiere\n" + "Tell me what I can do for you, be my guest!\n");
    }

    /**
     * An instance method that prints an exit greeting.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
