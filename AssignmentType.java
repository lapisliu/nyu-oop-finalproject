public enum AssignmentType {
    HOMEWORK("homework"),
    MIDTERM("midterm"),
    FINAL("final");

    private String type;

    AssignmentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
