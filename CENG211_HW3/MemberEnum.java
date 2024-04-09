public enum MemberEnum {
    STUDENT_W_SCHOLARSHIP("studentWScholar", 0.3),
    STUDENT_WO_SCHOLARSHIP("studentWOScholar", 0.2),
    GENERAL_MEMBER("general", 0);

    private String type;
    private double discountRate;

    private MemberEnum(String type, double discountRate) {
        this.type = type;
        this.discountRate = discountRate;
    }

    public static double getDiscountRate(String type) {
        for (MemberEnum member : values()) {
            if (member.type.equals(type)) {
                return member.discountRate;
            }
        }
        throw new IllegalArgumentException("Invalid input key");
    }
}
