package Luna;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Ui {
    public  final String HORIZONTAL_LINE = "____________________________________________________________\n";
    public String name;

    BufferedReader br;


    public Ui (String n) {
        this.name = n;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void greet() {
        this.shifted_print(this.greetingString());
    }

    public String readInput() {
        try {
            return br.readLine();
        } catch (Exception e) {
            return "";
        }
    }
    public  String greetingString(){
        String msg = "";
        msg += String.format("%30s", " （„• ֊ •„)♡\n");
        msg += "heyo! my name is ✦" + this.name + "✦\n";
        msg += "What would you like for me to do?\n";
        return msg;
    }

    public  String sad_cat_img() {
        return
                "⠀               />    フ\n" +
                        "               | 　_  _|\n" +
                        "           ___/` ミ＿_xノ\n" +
                        "          /　　　     |\n" +
                        "         /　 ヽ　     ﾉ\n" +
                        "        │　    |　|　|\n" +
                        "     /￣|　    |　|　|\n" +
                        "    | (￣ ヽ＿ヽ_)__)\n" +
                        "    ＼二二)⠀⠀\n";
    }

    public void exitMessage() {
        shifted_print(signoffString());
    }

    public  String signoffString(){
        String msg = "";
        msg += "okay then, bye\n";
        msg += sad_cat_img();

        return msg;
    }

    public  void shifted_print(String text) {
        try {
            BufferedReader br = new BufferedReader(new StringReader(text));
            String readText = br.readLine();
            StringBuilder output = new StringBuilder();
            output.append("      " + HORIZONTAL_LINE);
            while (readText != null) {
                output.append("      ").append(readText).append("\n");
                readText = br.readLine();
            }
            output.append("      " + HORIZONTAL_LINE);
            System.out.print(output);
        } catch (Exception e) {
            //
        }
    }


    public void showList(TaskList tl) {
        StringBuilder text = new StringBuilder();

        if (tl.isEmpty()) {
            text.append("List is Empty");
        } else {
            text.append("These are your outstanding tasks\n");
            for (int i = 0; i < tl.size(); i++) {
                ListEntry ent = tl.get(i);
                text.append((i+1)).append(".").append(ent.toString()).append("\n");
            }
        }
        this.shifted_print(text.toString());
    }


}