import java.util.ArrayList;
import java.util.Random;

public class Opponent {
    private int opponentId;
    private int points;
    private int attack;
    private int speed;
    private OpponentTypes oppType = OpponentTypes.getRandomType();
    private Turn opponentTurn;
    private double damageReduction = 1;

    public Opponent(int opponentId) {
        Random random = new Random();
        this.opponentId = opponentId;
        points = random.nextInt(100) + 50;
        attack = random.nextInt(20) + 5;
        speed = random.nextInt(89) + 1;

        opponentTurn = new Turn(opponentId, "", speed);
    }

    // Copy constructor.
    public Opponent(Opponent otherOpponent, int newId) {
        this.opponentId = newId;
        this.points = otherOpponent.points;
        this.attack = otherOpponent.attack;
        this.speed = otherOpponent.speed;
        this.oppType = otherOpponent.oppType;
        this.opponentTurn = new Turn(otherOpponent.opponentTurn, newId);

    }

    public void attack(Human<Weapon> attackedHuman, int move) {
        int dealtDamage = attackedHuman.getAttacked(attack);
        System.out.println(String.format("Move %d - Opponent %d attacks %s. Deals %d damage.", move,
                opponentId, attackedHuman.getName(), dealtDamage));
        attackedHuman.displayGetAttacked();
        opponentTurn.nextTurn();
    }

    public void guard(int move) {
        System.out.println(String.format("Move %d - Opponent %d guards itself.", move, opponentId));
        damageReduction = 0.5;
        opponentTurn.nextTurn();
    }

    public void special(Opponent opponent, Human<Weapon> attackedHuman, ArrayList<Opponent> modifiedOponentsArrayList,
            int move) {
        System.out.println(String.format("Move %d - Opponent %d makes a special move.", move, opponentId));
        if (oppType.getClass().equals(OpponentTypes.Goblin.class)) {
            OpponentTypes.Goblin.rushingAttack(opponent, attackedHuman, modifiedOponentsArrayList);
        } else if (oppType.getClass().equals(OpponentTypes.Orc.class)) {
            OpponentTypes.Orc.heavyHit(opponent, attackedHuman);
        } else if (oppType.getClass().equals(OpponentTypes.Slime.class)) {
            OpponentTypes.Slime.absorb(opponent, attackedHuman);
        } else if (oppType.getClass().equals(OpponentTypes.Wolf.class)) {
            OpponentTypes.Wolf.callFriend(opponent, modifiedOponentsArrayList);
        }
    }

    public void makeOpponentMove(Opponent opponent, Human<Weapon> attackedHuman,
            ArrayList<Opponent> modifiedOpponentsArrayList, int move) {
        Random rnd = new Random();
        int randomMove = rnd.nextInt(3);
        switch (randomMove) {
            case 0:
                attack(attackedHuman, move);
                break;
            case 1:
                guard(move);
                break;
            case 2:
                special(opponent, attackedHuman, modifiedOpponentsArrayList, move);
                break;
        }
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Type: %s, Points : %d, Attack: %d, Speed: %d", opponentId,
                oppType.getClass().getSimpleName(),
                points, attack,
                speed);
    }

    public int getAttacked(int dealtDamage) {
        points -= dealtDamage * damageReduction;
        return (int) Math.floor(dealtDamage * damageReduction);
    }

    public void resetSideEffects() {
        damageReduction = 1;
    }

    public void heal(int heal) {
        points += heal;
    }

    // Getter Methods.
    public int getSpeed() {
        return speed;
    }

    public int getAttack() {
        return attack;
    }

    public Turn getOpponentTurn() {
        return opponentTurn;
    }

    public int getOpponentId() {
        return opponentId;
    }

    public int getPoints() {
        return points;
    }

    public OpponentTypes getOppType() {
        return oppType;
    }

    // Setter methods.
    public void setPoints(int points) {
        this.points = points;
    }
}