public class ItemList {
    private static String itemList = "";

    static void addToList(String s) {
        itemList += s + "\n";
    }

    static String getItemList() {
        return itemList;
    }
}
