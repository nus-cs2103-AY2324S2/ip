class ByeCommand extends Command {
    @Override
    public void execute(Storage storage, boolean silent) {
        if (!silent){
            System.out.println("Bye. Hope to see you again soon!");
        }
        System.exit(0);
    }


}
