public class PrintUtil {
    private static String[] spacersLeft = {
        "（ -.-）ノ----------------------------=====" +
                "=============================≡≡≡≡≡" +
                "≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡卍\n",
        "( ･∀･)ﾉ----------------------------=======" +
                "==========================≡≡≡≡≡≡≡≡" +
                "≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡ 卍\n",
        "(`･Д･)ノ===================================" +
                "===================================" +
                "======================☆\n",
        "ヽ| ・∀・|ノ------=================≡≡≡≡≡≡≡≡≡≡" +
                "≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡" +
                "≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡卍卍\n",
    };
    private static String[] spacersRight = {
            "卍≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡==" +
                    "===============================--" +
                    "--------------------ヽ（ -.-）\n",
        "卍≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡=========" +
                "=================---------------------" +
                "----------ヽ(･∀･ )\n",
        "☆==============================================" +
                "=======================================" +
                "======ヽ(`･Д･´)\n"
    };

    // Private constructor to prevent instantiation
    private PrintUtil() {}

    // Static utility method for printing lines
    private static void printSpacerLeft() {
        indent();
        System.out.println(spacersLeft[(int) Math.floor(Math.random() * 4)]);
    }

    private static void printSpacerRight() {
        indent();
        System.out.println(spacersRight[(int) Math.floor(Math.random() * 3)]);
    }

    private static void indent() {
        System.out.print("    ");
    }

    private static void taskIndent() {
        indent();
        System.out.print("  ");
    }

    public static void print(String s) {
        printSpacerLeft();
        indent();
        System.out.println(s.replaceAll("\n", "\n    ") + "\n");
        printSpacerRight();
    }

    public static void print(Task t) {
        taskIndent();
        System.out.println(t + "\n");
        printSpacerLeft();
    }
}
