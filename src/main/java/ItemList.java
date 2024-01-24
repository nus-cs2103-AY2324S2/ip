public class ItemList {
    private static String itemList = "";
    private static int itemCount = 1;
    static void addToList(String s) {
        itemList += itemCount + ". " + s + "\n     ";
        itemCount++;
    }

    static String getItemList() {
        return itemList;
    }
}
