public enum PriorityEnum {
    INVALUABLE("invaluable", 3),
    HIGHLYSIGNIFICENT("highly significant", 2),
    NOTEWORTHY("noteworthy", 1);

    private String priority;
    private int value;

    private PriorityEnum(String priority, int value) {
        this.priority = priority;
        this.value = value;
    }

    public static String getPriorityType(String name) {
        for (PriorityEnum priority : values()) {
            if (name.equals(priority.priority)) {
                return priority.priority;
            }
        }
        throw new IllegalArgumentException("Invalid input key");
    }

    public static int getPriorityValue(String name) {
        for (PriorityEnum priority : values()) {
            if (name.equals(priority.priority)) {
                return priority.value;
            }
        }
        throw new IllegalArgumentException("Invalid input key");
    }
}