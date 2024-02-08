package gulie;

import java.time.LocalDateTime;

public abstract class Command {
    public abstract void run(GulieUi ui, GulieStorage storage, GulieTasklist tasklist) throws GulieException;

    public static class EventCommand extends Command {
        private String name;
        private LocalDateTime from, to;
        public EventCommand(String name, LocalDateTime from, LocalDateTime to) {
            this.name = name;
            this.from = from;
            this.to = to;
        }

        @Override
        public void run(GulieUi ui, GulieStorage storage, GulieTasklist tasklist) {
        }
    }
}
