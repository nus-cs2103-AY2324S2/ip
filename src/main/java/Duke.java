import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws Exception{

        String NAME = "Luna"; // TENTATIVE

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String ln = sc.nextLine();

        bot_functions.shifted_print(bot_functions.greetingString(NAME));

        boolean exitFlag = false;
        ArrayList<String> user_list = new ArrayList<>();

        while (!exitFlag) {
            String input_command = br.readLine();

            if (input_command.equalsIgnoreCase("bye") || input_command.equalsIgnoreCase("exit")) {
                bot_functions.shifted_print(bot_functions.signoffString());
                exitFlag = true;

            } else if (input_command.equalsIgnoreCase("list")){
                StringBuilder text = new StringBuilder();
                if (user_list.isEmpty()) {
                    text.append("List is Empty");
                } else {
                    for (int i = 0; i < user_list.size(); i++) {
                        text.append((i+1)).append(". ").append(user_list.get(i)).append("\n");

//                        text = text.concat((i).toString()).append(". ").append(user_list.get(i)).append("\n").toString();
                    }
                }

                bot_functions.shifted_print(text.toString());

            } else {
                user_list.add(input_command);
                bot_functions.shifted_print("added: " + input_command);
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

    }
}

