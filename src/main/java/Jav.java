import java.io.IOException;
import java.util.Scanner;
/**
* The main program.
*/
public class Jav {
    /** The storage manager instance. */
    public static StorageManager storageManager = new StorageManager();
    
    /** The file manager instance. */
    public static FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        // Print any neccessary UI upon starting up
        String logo =
                  "      ____.  _________   ____\n"
                + "     |    | /  _  \\   \\ /   /\n"
                + "     |    |/  /_\\  \\   Y   /\n"
                + " /\\__|    /    |    \\     /\n"
                + " \\________\\____|__  /\\___/\n"
                + "                  \\/\n"
                + "The Joy Amplifying Virtuoso!\n";
                // Got ASCII Word Art from https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Jav
        System.out.println("Hello from\n" + logo);
        System.out.println("<---------------------------------------------------------->");
        MessagePrinter.printGreeting();
        
        // Load any neccessary data
        Scanner scan = new Scanner(System.in);
        try {
            storageManager.load(fileManager.loadStorageData());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Main program loop
        boolean isExiting = false;
        Parser parser = new Parser();
        while (!isExiting) {
            // Get user input
            System.out.println("| User Input:");
            System.out.print("> ");
            String s = scan.nextLine();
            
            // Check if requested command can be found
            try {
                String cmd = s;
                String param = "";

                // Check if its a command with parameters
                if (s.indexOf(" ") != -1) {
                    cmd = s.substring(0, s.indexOf(' '));
                    param = s.substring(s.indexOf(' ') + 1);
                }

                // Perform action based on command
                switch(parser.checkCommand(cmd)) {
                case B:
                    // Exit the program
                    isExiting = true;
                    break;
                case D:
                    // Create a deadline
                    storeTask(param, StorageManager.StorageType.DEADLINE);
                    break;
                case E: 
                    // Create a event
                    storeTask(param, StorageManager.StorageType.EVENT);
                    break;
                case L: 
                    // List all tasks
                    MessagePrinter.printStorage(storageManager.printStoredTasks());
                    break;
                case M: 
                    // Mark a task
                    updateMark(param, true);
                    break;
                case R: 
                    // Remove/Delete a task
                    deleteTask(param);
                    break;
                case T:
                    // Create a todo
                    storeTask(param, StorageManager.StorageType.TODO);
                    break;
                case U:
                    // Unmark a task 
                    updateMark(param, false);
                    break;
                case INVALID_CMD:
                    // Invalid command
                    MessagePrinter.printInvalidCommand();
                    MessagePrinter.echo(s);
                    break; 
                default:
                    break;        
                }
            } catch (InvalidParamException e) {
                MessagePrinter.printInvalidParameters();
                MessagePrinter.echo(s);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        MessagePrinter.printExit();
        System.out.println("<---------------------------------------------------------->");
        scan.close();
    }

    /**
     * Updates and mark/unmark a task.
     *
     * @param params a string containing the index of the task.
     * @param isMarking whether to mark or unmark the specified task.
     *
     * @throws InvalidParamException if the parameters are invalid.
     */
    public static void updateMark(String param, boolean isMarking) throws InvalidParamException {
        if (Integer.parseInt(param) >= 1) {
            if (storageManager.updateTask(Integer.parseInt(param) - 1, true)) {
                if (isMarking) {
                    MessagePrinter.printMarkingTask();
                } else {
                    MessagePrinter.printUnmarkingTask();
                }
                return;
            }
        }

        throw new InvalidParamException("Cannot mark/unmark task", null);
    }

    /**
     * Deletes/removes a task.
     *
     * @param params a string containing the index of the task.
     *
     * @throws InvalidParamException if the parameters are invalid.
     */
    public static void deleteTask(String param) throws InvalidParamException {
        if (Integer.parseInt(param) >= 1) {
            if (storageManager.deleteTask(Integer.parseInt(param) - 1)) {
                MessagePrinter.printDeletingTask();
            } else {
                throw new InvalidParamException("Cannot delete task", null);
            }
        } else {
            throw new InvalidParamException("Cannot delete task", null);
        }
    }

    /**
     * Stores a task into the storage.
     *
     * @param params a string containing the information of the task.
     * @param type the type of task to store.
     *
     * @throws InvalidParamException if the parameters are invalid.
     */
    public static void storeTask(String param, StorageManager.StorageType type) throws IOException, InvalidParamException {
        try {
            storageManager.store(param, type);
            fileManager.saveStorageData(storageManager.getFileFormat());
            MessagePrinter.printStoring();
        } catch (IOException ioe) {
            throw ioe;
        } catch (InvalidParamException ipe) {
            throw ipe;
        }
    }
}
