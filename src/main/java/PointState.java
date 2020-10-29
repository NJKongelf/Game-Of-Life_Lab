public enum PointState {
    ALIVE("¤"),
    DEAD(" ");
    private String message;

    PointState(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
