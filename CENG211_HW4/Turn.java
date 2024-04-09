public class Turn implements Comparable<Turn> {
    private int currentTurn = 1;
    private int nextTurn = 2;
    private int specialTurn;
    private int ownerId;
    private String ownerName;
    private int speed;
    private double attackModifier = 1;

    public Turn(int ownerId, String ownerName, int speed) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.speed = speed;
    }

    public Turn(Turn otherTurn, int newId) {
        this.currentTurn = otherTurn.currentTurn;
        this.ownerId = newId;
        this.speed = otherTurn.speed;
        this.ownerName = otherTurn.ownerName;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void nextTurn() {
        currentTurn++;

    }

    public void incrementNextTurn() {
        nextTurn++;
    }

    public void setAttackModifier(double attackModifier) {
        this.attackModifier = attackModifier;
    }

    public void resetAttackModifier() {
        attackModifier = 1;
    }

    public boolean isSpecialTurn() {
        return currentTurn == specialTurn;
    }

    public void skipNextTurn() {
        currentTurn += 2;
    }

    public void skipCurrentTurn() {
        currentTurn++;
    }

    public int getNextTurn() {
        return nextTurn;
    }

    public int getSpeed() {
        return speed;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getAttackModifier() {
        return attackModifier;
    }

    public int getSpecialTurn() {
        return specialTurn;
    }

    public void setSpecialTurn(int specialTurn) {
        this.specialTurn = specialTurn;
    }

    @Override
    public int compareTo(Turn o) {
        return Integer.compare(this.speed, o.speed);
    }
}