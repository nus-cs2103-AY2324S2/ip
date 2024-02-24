import java.io.BufferedReader;
import java.io.StringReader;

public class Ui {
    public static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    public static String greetingString(String name){
        String msg = "";
        msg += String.format("%30s", " （„• ֊ •„)♡\n");
        msg += "heyo! my name is ✦" + name + "✦\n";
        msg += "What would you like for me to do?\n";
        return msg;
    }

    public static String sad_cat_img() {
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

    public static String signoffString(){
        String msg = "";
        msg += "okay then, bye\n";
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
            readText = br.readLine();
        }
        output.append("      " + HORIZONTAL_LINE);
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