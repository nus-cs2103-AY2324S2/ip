public class ListCommand implements Command {
    public String execute(String command, String[] info, ItemList itemList) throws CustomExceptions {
        if (info.length == 1) {
            return itemList.toString();
        }
        return null;
    }
}
