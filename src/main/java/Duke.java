import exceptions.DukeException;
import exceptions.StorageException;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws StorageException {
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

        String relativePath = "./src/main/data/tasks.txt";
        TaskList tasksList = TaskList.getInstance(relativePath);

        Scanner scanner = new Scanner(System.in);
        boolean runBot = true;
        while (runBot) {
            String[] userInput = scanner.nextLine().trim().split("\\s+");

            if (userInput[0].equalsIgnoreCase("BYE")) {
                System.out.println("Sayonara! Do visit again. RAHHHHH 0.0");
                runBot = false;
            }

            try {
                Parser.parseUserInput(userInput);
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: Index out of bounds! Kindly do not exceed the current total number of tasks shown when running 'list' -_-");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Please only use integers for 'mark' or 'unmark' commands and ensure theres a space before giving the index!");
            }

        }
        tasksList.saveTasks();
    }
}
