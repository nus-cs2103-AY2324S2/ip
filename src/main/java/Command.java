public interface Command {
    String execute(String command, String[] info, ItemList itemList) throws CustomExceptions;

}
