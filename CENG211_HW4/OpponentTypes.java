import java.util.ArrayList;
import java.util.Random;

public abstract class OpponentTypes {
    public static OpponentTypes getRandomType() {
        Random rnd = new Random();
        OpponentTypes[] opponentInstances = { new Slime(), new Goblin(), new Orc(),
                new Wolf() };
        return opponentInstances[rnd.nextInt(opponentInstances.length)];
    }

    public static class Slime extends OpponentTypes {
        public static void absorb(Opponent currentOpponent, Human<Weapon> attackedHuman) {
            int damageDealt = currentOpponent.getAttack();
            System.out
                    .println(String.format("Opponent %d uses Absorb on %s. Deals %d damage",
                            currentOpponent.getOpponentId(),
                            attackedHuman.getName(), damageDealt));
            currentOpponent.heal(damageDealt);

            if (currentOpponent.getPoints() > 150) {
                currentOpponent.setPoints(150);
            }
            attackedHuman.getAttacked(damageDealt);
            attackedHuman.displayGetAttacked();
            System.out.println(String.format("Opponent %d, Type: %s, Points : %d", currentOpponent.getOpponentId(),
                    currentOpponent.getOppType().getClass().getSimpleName(), currentOpponent.getPoints()));
            currentOpponent.getOpponentTurn().nextTurn();
        }
    }

    public static class Goblin extends OpponentTypes {
        public static void rushingAttack(Opponent currentOpponent, Human<Weapon> attackedHuman,
                ArrayList<Opponent> modifiedOponentsArrayList) {
            currentOpponent.getOpponentTurn().setAttackModifier(0.7);
            int damageDealt = attackedHuman.getAttacked((int) (currentOpponent.getAttack()
                    * currentOpponent.getOpponentTurn().getAttackModifier()));
            System.out.println(String.format("Opponent %d uses Rush Attack on %s. Deals %d damage",
                    currentOpponent.getOpponentId(), attackedHuman.getName(), damageDealt));
            attackedHuman.displayGetAttacked();
            currentOpponent.getOpponentTurn().setSpecialTurn(currentOpponent.getOpponentTurn().getCurrentTurn());

        }
    }

    public static class Orc extends OpponentTypes {
        public static void heavyHit(Opponent currentOpponent, Human<Weapon> attackedHuman) {
            int damageDealt = attackedHuman.getAttacked(currentOpponent.getAttack() * 2);
            System.out.println(String.format("Opponent %d uses Heavy Hit on %s. Deals %d damage",
                    currentOpponent.getOpponentId(), attackedHuman.getName(), damageDealt));
            attackedHuman.displayGetAttacked();
            currentOpponent.getOpponentTurn().skipNextTurn();
        }
    }

    public static class Wolf extends OpponentTypes {
        public static void callFriend(Opponent currentOpponent, ArrayList<Opponent> opponentsArrayList) {
            System.out
                    .println(String.format("Opponent %d is trying to call a friend.", currentOpponent.getOpponentId()));
            Random rnd = new Random();
            if (rnd.nextInt(9) < 2) {
                System.out.println("Summon successful.");
                Opponent calledOpponent = new Opponent(currentOpponent, opponentsArrayList.size() + 1);
                calledOpponent.getOpponentTurn().nextTurn();
                System.out.println(String.format("Summoned opponent is: %s", calledOpponent.toString()));
                opponentsArrayList.add(calledOpponent);
            } else {
                System.out.println("Summon failed.");
            }
            currentOpponent.getOpponentTurn().nextTurn();
        }
    }
}