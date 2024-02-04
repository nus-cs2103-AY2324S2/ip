public class UnmarkCommand implements Command {
    @Override
    public String execute(String command, String[] info, ItemList itemList) throws CustomExceptions{
        if (info.length != 2) {
            throw new CustomExceptions.markException("Please enter unmark command in the following format: unmark <index>");
        } else {
            try {
                int index = Integer.parseUnsignedInt(info[1]);
                Item item = itemList.getList().get(index - 1);
                item.markUndone();
                return item.undoneMessage();
            } catch (NumberFormatException e) {
                throw new CustomExceptions.unrecognizedCommandException("");
            }
        }
    }
}
