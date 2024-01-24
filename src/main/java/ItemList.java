public class ItemList {
    private static String[] itemList = new String[100];
    private static boolean[] doneList = new boolean[100];
    private static int itemCount = 0;

    static void addToList(String s) {
        itemList[itemCount] = s;
        itemCount++;
    }

    static void markDone(int i) {
        if (i <= itemCount) {
            doneList[i-1] = true;
        }
    }

    static void markUndone(int i) {
        if (i <= itemCount) {
            doneList[i-1] = false;
        }
    }

    static String printChecked(boolean b) {
        return b ? "X" : " ";
    }

    static String doneMessage(int i) {
        return "Nice! I've marked this task as done:\n     " +
               "[X]" + itemList[i];
    }

    static String undoneMessage(int i) {
        return "OK, I've marked this task as not done yet:\n     " +
                "[ ]" + itemList[i];
    }

    static String getItemList() {
        int c = 0;
        String out = "";
        while (!(itemList[c] == null)) {
            System.out.println(itemList[c]);
            out += (c+1) + ".[" + printChecked(doneList[c])+ "] " + itemList[c] + "\n     ";
            c++;
        }
        return out;
    }
}
