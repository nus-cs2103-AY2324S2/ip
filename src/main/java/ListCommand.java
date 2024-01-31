public class ListCommand extends Command{
    public ListCommand() {
        super("", null);
    }

    @Override
    public void execute(State state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= state.getTasks().size();i++) {
            Task task = state.getTask(i - 1);
            sb.append(String.format("%d: %s\n", i, task));
        }
        if (state.getTasks().isEmpty()) {
            sb.append("No Tasks! Oopsie!");
        }
        System.out.println(sb);
    }
}
