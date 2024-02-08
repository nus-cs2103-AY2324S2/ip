package gulie;

import java.time.LocalDateTime;

public abstract class Command {
    public abstract void run(GulieUi ui, GulieStorage storage, GulieTasklist tasklist) throws GulieException;
}
