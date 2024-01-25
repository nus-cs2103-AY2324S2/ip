import java.util.List;

public class DeadlineCommand implements NamedCommand {
    public String getName() { return "deadline"; }
    public void execute(ChatSession session, String commandArgs) {
        Pair<String, List<Pair<String, String>>> data = SubcommandParser.parseSubcommands(commandArgs, "/");
        String name = data.getFirst();
        List<Pair<String, String>> subcommPairs = data.getSecond();
        Deadline t = new Deadline(name);

        for (Pair<String, String> subcommPair : subcommPairs) {
            String subcommString = subcommPair.getFirst();
            switch (subcommString) {
                case "/by":
                    String date = subcommPair.getSecond();
                    t.setByDate(date);
                    break;
                default:
                    // add exception handling later
                    break;
            }
        }
    }
}
