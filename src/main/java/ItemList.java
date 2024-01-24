public class ItemList {
    private static String[] itemList = new String[100];
    private static int itemCount = 0;
    static void addToList(String s) {
        itemList[itemCount] = s;
        itemCount++;
    }

    static String getItemList() {
        int c = 0;
        String out = "";
        while (!(itemList[c] == null)) {
            System.out.println(itemList[c]);
            out += (c+1) + ". " + itemList[c] + "\n     ";
            c++;
        }
        return out;
    }
}
