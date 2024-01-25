import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) throws Exception{

        String NAME = "Luna"; // TENTATIVE

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String ln = sc.nextLine();

        bot_functions.shifted_print(bot_functions.greetingString(NAME));

        boolean exitFlag = false;
        ArrayList<list_Entry> user_list = new ArrayList<>();

        while (!exitFlag) {
            String input_command = br.readLine();

            // IF EXIT
            if (input_command.equalsIgnoreCase("bye") || input_command.equalsIgnoreCase("exit")) {
                bot_functions.shifted_print(bot_functions.signoffString());
                exitFlag = true;

            // IF LIST IS REQUESTED
            } else if (input_command.equalsIgnoreCase("list")){
                StringBuilder text = new StringBuilder();
                if (user_list.isEmpty()) {
                    text.append("List is Empty");
                } else {
                    for (int i = 0; i < user_list.size(); i++) {
                        list_Entry ent = user_list.get(i);
                        text.append((i+1)).append(".").append(ent.toString()).append("\n");
//                        text = text.concat((i).toString()).append(". ").append(user_list.get(i)).append("\n").toString();
                    }
                }

                bot_functions.shifted_print(text.toString());

                // MARK AND UNMARK COMMANDS
            } else {
                String [] keys = input_command.split(" ", 2);

                if (keys[0].equalsIgnoreCase("unmark") || keys[0].equalsIgnoreCase("mark")) {
                    if (Arrays.asList(keys).size() < 2) {
                        bot_functions.shifted_print("Missing inputs");
                    } else if (!bot_functions.isNumeric(keys[1])) {
                        bot_functions.shifted_print("Invalid number for a mark/unmark command");
                    } else {
                        int pos = Integer.parseInt(keys[1]);
                        if (pos <= user_list.size() && pos > 0) {
                            list_Entry ent = user_list.get(pos-1);
                            if (keys[0].equalsIgnoreCase("mark")) {
                                ent.markEntry();
                                bot_functions.shifted_print("Nice! I've marked this task as done:\n" + ent);
                            } else {
                                ent.unmarkEntry();
                                bot_functions.shifted_print("Nice! I've marked this task as not done yet:\n" + ent);
                            }
                        } else {
                            bot_functions.shifted_print("There are " + user_list.size() + " tasks in the list");
                        }
                    }
                } else {
                    list_Entry ent = new list_Entry(input_command, false);
                    user_list.add(ent);
                    StringBuilder text = new StringBuilder();
                    String count_msg = "Now you have " + user_list.size() + " tasks in the list.";
                    text.append(count_msg);
                    bot_functions.shifted_print(text.toString());
//                bot_functions.shifted_print("added: " + input_command);

                }

            }
        }

        System.exit(0);

    }



    public static class bot_functions {
        public static final String HORIZONTAL_LINE = "____________________________________________________________\n";

        public static String greetingString(String name){
            String msg = "";
            msg += String.format("%35s", "૮ ˶ᵔ ᵕ ᵔ˶ ა\n");
            msg += "heyo! my name is " + name + "\n";
            msg += "What would you like for me to do?\n";
            return msg;
        }

        public static String sad_cat_img() {
            return
                    "⠀               />    フ\n" +
                            "               | 　_  _|\n" +
                            "             ／` ミ＿_xノ\n" +
                            "          /　　　     |\n" +
                            "         /　 ヽ　     ﾉ\n" +
                            "        │　    |　|　|\n" +
                            "    ／￣|　    |　|　|\n" +
                            "    | (￣ ヽ＿ヽ_)__)\n" +
                            "    ＼二つ⠀⠀\n";
        }
        public static String signoffString(){
            String msg = "";
            msg += "okay then, bye\n";
//            msg += String.format("%40s", "( •̯́ ^ •̯̀)]\n");
            msg += sad_cat_img();

            return msg;
        }

        public static void shifted_print(String text) throws Exception{
            BufferedReader br = new BufferedReader(new StringReader(text));
            String readText = br.readLine();
            StringBuilder output = new StringBuilder();
            output.append("      " + HORIZONTAL_LINE);
            while (readText != null) {
                output.append("      ").append(readText).append("\n");
//                output = new String(output + "      " + readText + "\n");

                readText = br.readLine();
            }
            output.append("      " + HORIZONTAL_LINE);

//            output += "      " + HORIZONTAL_LINE;
            System.out.print(output);
        }

        // Reference from https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
        public static boolean isNumeric(String str) {
            try {
                Double.parseDouble(str);
                return true;
            } catch(NumberFormatException e){
                return false;
            }
        }

    }
}

