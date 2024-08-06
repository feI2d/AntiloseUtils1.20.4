package xyz.antilose.events;

public class Event implements IEvent {

    private boolean cancelled;

    @Override
    public void cancel() {
        cancelled = true;
    }

    @Override
    public boolean isCancel() {
        return cancelled;
    }
}
