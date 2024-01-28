import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo =   "       **   *******       **         **     **      ** **      ** **      **   ******     *******   **********   **      \n" +
                        "      **   /**////**     ****       ****   /**     /**/**     /**/**     /**  /*////**   **/////** /////**///   //**     \n" +
                        "     **    /**   /**    **//**     **//**  /**     /**/**     /**/**     /**  /*   /**  **     //**    /**       //**    \n" +
                        "    **     /*******    **  //**   **  //** /**********/**********/**********  /******  /**      /**    /**        //**   \n" +
                        "   **      /**///**   ********** **********/**//////**/**//////**/**//////**  /*//// **/**      /**    /**         //**  \n" +
                        "  **       /**  //** /**//////**/**//////**/**     /**/**     /**/**     /**  /*    /**//**     **     /**          //** \n" +
                        " **        /**   //**/**     /**/**     /**/**     /**/**     /**/**     /**  /*******  //*******      /**           //**\n" +
                        "//         //     // //      // //      // //      // //      // //      //   ///////    ///////       //             // ";

        System.out.println(logo);
        System.out.println("_________________________________________\n");
        System.out.println("Hello! I'm RahhBot. RAHHHH!!\n" + "Below contains the list of available CASE-SENSITIVE commands:\n");
        System.out.println("  |  todo <task>                           |\n"
                + "  |  deadline <task> /by <day/date>        |\n"
                + "  |  event <task> /from <start> /to <end>  |\n"
                + "  |  list                                  |\n"
                + "  |  mark <index>                          |\n"
                + "  |  unmark <index>                        |\n"
                + "  |  bye                                   |\n");
        System.out.println("What can I do for you today?\n");
        System.out.println("_________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        TaskList tasksList = new TaskList();

        boolean runBot = true;

        while (runBot) {
            String userInput = scanner.nextLine().trim();
            String[] words = userInput.split("\\s+");
            String command;

            try {
                command = CommandScanner.scanCommand(words);
                switch (Objects.requireNonNull(command)) {
                    case "TODO":
                        ToDoTask todoTask = new ToDoTask(ToDoTask.getDescription(words));
                        tasksList.addTask(todoTask);
                        continue;
                    case "DEADLINE":
                        String[] deadlineDesc = DeadlineTask.getDescription(words);
                        DeadlineTask deadlineTask = new DeadlineTask(deadlineDesc[0], deadlineDesc[1]);
                        tasksList.addTask(deadlineTask);
                        continue;
                    case "EVENT":
                        String[] eventDesc = EventTask.getDescription(words);
                        EventTask eventTask = new EventTask(eventDesc[0], eventDesc[1], eventDesc[2]);
                        tasksList.addTask(eventTask);
                        continue;
                    case "LIST":
                        tasksList.displayTasks();
                        continue;
                    case "MARK":
                        int markIndex = Integer.parseInt(words[words.length - 1]) - 1;
                        tasksList.getTask(markIndex).markDone();
                        continue;
                    case "UNMARK":
                        var unmarkIndex = Integer.parseInt(words[words.length - 1]) - 1;
                        tasksList.getTask(unmarkIndex).markNotDone();
                        continue;
                    case "DELETE":
                        int index = Integer.parseInt(words[words.length - 1]) - 1;
                        tasksList.deleteTask(index);
                        continue;
                    case "BYE":
                        System.out.println("Sayonara! Do visit again. RAHHHHH 0.0");
                        runBot = false;
                }
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: Index out of bounds! Kindly do not exceed the current total number of tasks shown when running 'list' -_-");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Please only use integers for 'mark' or 'unmark' commands and ensure theres a space before giving the index!");
            }


        }
    }
}
