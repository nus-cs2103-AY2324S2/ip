public class Parser {

    public static boolean parse(String msg, boolean isInit, TaskList tasks) throws LamballParseException {
        String[] parts = msg.split(" ", 2);
        if (!isInit) {
            System.out.println("\n    Lamball");
        }
        switch (parts[0]) {
            case "mark": {
                if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
                    throw new LamballParseException("Invalid number, baa.");
                }
                int idx = Integer.valueOf(parts[1]) - 1;
                String toFile = tasks.mark(isInit, idx);
                if (!isInit) {
                    Storage.replaceLine(toFile, idx);
                }
                return true;
            }
            case "unmark": {
                if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
                    throw new LamballParseException("Invalid number, baa.");
                }
                int idx = Integer.valueOf(parts[1]) - 1;
                String toFile = tasks.unMark(idx);
                Storage.replaceLine(toFile, idx);
                return true;
            }
            case "bye":
                return false;
            case "list":
                tasks.printList();
                return true;
            case "todo": {
                String toFile = tasks.toDo(parts, isInit);
                if (!isInit) {
                    Storage.writeToFile(toFile);
                }
                return true;
            }
            case "event": {
                String toFile = tasks.event(parts, isInit);
                if (!isInit) {
                    Storage.writeToFile(toFile);
                }
                return true;
            }
            case "deadline": {
                String toFile = tasks.deadline(parts, isInit);
                if (!isInit) {
                    Storage.writeToFile(toFile);
                }
                return true;
            }
            case "delete": {
                if (!parts[1].matches("-?\\d+")) {
                    throw new LamballParseException("Invalid number, baa.");
                }
                int idx = Integer.valueOf(parts[1]) - 1;
                tasks.deleteFromList(parts, idx);
                Storage.deleteLine(idx);
                return true;
            }
            default:
                throw new LamballParseException("Sorry, I don't understaaaaaand your commaaaaand, baa. :(");
        }
    }
}
