import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
        System.out.println("Hello! I'm RahhBot. RAHHHH!!\n");
        System.out.println("What can I do for you?\n");
        System.out.println("_________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        TaskList tasksList = new TaskList();

        boolean runBot = true;

        while (runBot) {
            String userInput = scanner.nextLine().trim();
            String[] words = userInput.split("\\s+");
            String command = null;

            if (words.length < 2 && !(words[0].equalsIgnoreCase("list"))) {
                System.out.println("Invalid/incomplete command, please try again.");
                continue;
            }

            try {
                command = CommandScanner.scanCommand(words);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

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
                case "BYE":
                    runBot = false;
            }

        }
    }
}
