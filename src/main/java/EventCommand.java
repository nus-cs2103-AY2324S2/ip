import java.util.List;

public class EventCommand implements NamedCommand {
    public String getName() { return "event"; }
    public void execute(ChatSession session, String commandArgs) {
        Pair<String, List<Pair<String, String>>> data = SubcommandParser.parseSubcommands(commandArgs, "/");
        String name = data.getFirst();
        List<Pair<String, String>> subcommPairs = data.getSecond();
        Event t = new Event(name);

        for (Pair<String, String> subcommPair : subcommPairs) {
            String subcommString = subcommPair.getFirst();
            switch (subcommString) {
                case "/from":
                    String fromDate = subcommPair.getSecond();
                    t.setFromDate(fromDate);
                    break;
                case "/to":
                    String toDate = subcommPair.getSecond();
                    t.setToDate(toDate);
                default:
                    // add exception handling later
                    break;
            }
        }

        session.taskList.add(t);
        session.printMessage(String.format("Got it. I've added the following task: \n %s", t.getName()));
    }
}
