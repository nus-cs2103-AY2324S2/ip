import java.io.InputStreamReader;
import java.io.BufferedReader;
//import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Luna {

    enum commandHints {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        EXIT
    }
    public static void main(String[] args) throws Exception{

        String NAME = "Luna"; // TENTATIVE
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        private Ui ui = new Ui();
        Ui.shifted_print(Ui.greetingString(NAME));
        boolean exitFlag = false;
        commandHints currentHint = commandHints.EXIT;
        ArrayList<list_Entry> user_list = new ArrayList<>();

        // Testing ListFileManager
        Storage storage = new Storage("taskList");
//        lfm.appendToFile("TEST");


        // End Testing

        while (!exitFlag) {
            String input_command = br.readLine();
            if (input_command.equalsIgnoreCase("help")) {
                switch(currentHint) {
                    case LIST:
                        Ui.shifted_print("To display all the task in your list\n--> Type: list");
                        break;
                    case TODO:
                        Ui.shifted_print("To add a to-do task to your list\n--> Type: todo <task name>");
                        break;
                    case DEADLINE:
                        Ui.shifted_print("To add a deadline task to your list\n--> Type: deadline <task name> <end date>");
                        break;
                    case EVENT:
                        Ui.shifted_print("To add a event task to your list\n--> Type: event <task name> <start date> <end date>");
                        break;
                    case MARK:
                        Ui.shifted_print("To mark a task as done\n--> Type: mark <list num>");
                        Ui.shifted_print("To unmark a task as not done\n--> Type: unmark <list num>");
                        Ui.shifted_print("To delete a task from the list\n--> Type: delete <list num>");
                        break;
                    case EXIT:
                        Ui.shifted_print("End the program :(\n--> Type: bye");
                        break;

                }
            }

            if (input_command.equalsIgnoreCase("save")) {
                storage.clearFile();
                for (list_Entry listEntry : user_list) {
                    storage.appendEntry(listEntry);
                }
            } else if (input_command.equalsIgnoreCase("load")) {
                user_list.clear();
                storage.loadList(user_list);
            } else
            // IF EXIT
            if (input_command.equalsIgnoreCase("bye") || input_command.equalsIgnoreCase("exit")) {
                Ui.shifted_print(Ui.signoffString());
                exitFlag = true;

            // IF LIST IS REQUESTED
            } else if (input_command.equalsIgnoreCase("list")){
                currentHint = commandHints.LIST;

                StringBuilder text = new StringBuilder();
                if (user_list.isEmpty()) {
                    text.append("List is Empty");
                } else {
                    text.append("These are your outstanding tasks\n");
                    for (int i = 0; i < user_list.size(); i++) {
                        list_Entry ent = user_list.get(i);
                        text.append((i+1)).append(".").append(ent.toString()).append("\n");
                    }
                }
                Ui.shifted_print(text.toString());

            } else {
                currentHint = commandHints.MARK;

                String [] keys = input_command.split(" ", 2);

                if (keys[0].equalsIgnoreCase("unmark") || keys[0].equalsIgnoreCase("mark") || keys[0].equalsIgnoreCase("delete")) {
                    if (Arrays.asList(keys).size() < 2) {
                        Ui.shifted_print("Missing inputs");
                    } else if (!Ui.isNumeric(keys[1])) {
                        Ui.shifted_print("Please input a number after the command E.g (mark/unmark/delete) 3");
                    } else {
                        int pos = Integer.parseInt(keys[1]);
                        if (pos <= user_list.size() && pos > 0) {
                            list_Entry ent = user_list.get(pos-1);
                            if (keys[0].equalsIgnoreCase("delete")) {
                                user_list.remove(pos-1);
                                Ui.shifted_print("Deleting the following task:\n " + ent
                                        + "\nThere are " + user_list.size() + " tasks in the list");
                            }
                            else if (keys[0].equalsIgnoreCase("mark")) {
                                ent.markEntry();
                                Ui.shifted_print("Nice! I've marked this task as done:\n" + ent);
                            } else {
                                ent.unmarkEntry();
                                Ui.shifted_print("Nice! I've marked this task as not done yet:\n" + ent);
                            }
                        } else {
                            Ui.shifted_print("There are " + user_list.size() + " tasks in the list");
                        }
                    }
                } else {
                    currentHint = commandHints.TODO;

                    boolean successFlag = false;
                    list_Entry ent = new list_Entry();
                    if (Arrays.asList(keys).size() == 2) {
                        if (keys[0].equalsIgnoreCase("todo")) {
                            successFlag = true;
                            ent = new list_Entry_Todo(keys[1], false);
                        } else if (keys[0].equalsIgnoreCase("deadline")) {
                            currentHint = commandHints.DEADLINE;

                            String[] keys_entry = keys[1].split(" /by ", 2);
                            if (Arrays.asList(keys_entry).size() == 2) {
                                successFlag = true;
                                ent = new list_Entry_Deadline(keys_entry[0], false, LocalDate.parse(keys_entry[1]));
                            }
                        } else if (keys[0].equalsIgnoreCase("event")) {
                            currentHint = commandHints.EVENT;

                            String[] keys_entry1 = keys[1].split(" /from ", 2);
                            if (Arrays.asList(keys_entry1).size() == 2) {
                                String[] keys_entry2 = keys_entry1[1].split(" /to ", 2);
                                if (Arrays.asList(keys_entry2).size() == 2) {
                                    successFlag = true;
                                    ent = new list_Entry_Event(keys_entry1[0], false, LocalDate.parse(keys_entry2[0]), LocalDate.parse(keys_entry2[1]));
                                }
                            }
                        }
                    }

                    if (successFlag) {
                        user_list.add(ent);
                        StringBuilder text = new StringBuilder();
                        String count_msg = "Oki! Added a task to the list:\n   " + ent + "\nNow you have " + user_list.size() + " tasks in the list.";
                        text.append(count_msg);
                        Ui.shifted_print(text.toString());
                    } else {
                        Ui.shifted_print("Wrong format");
                    }

                }
            }
        }
        System.exit(0);
    }



}

