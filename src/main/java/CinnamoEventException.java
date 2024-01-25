class CinnamoEventException extends CinnamoException {
    @Override
    public String toString() {
        return "Hi! Please provide detailed schedule for the event with \"/from\" to indicate " +
                "the starting time and \"/end\" to indicate ending time of the event:) -- Cinnamo >.<";
    }
}
