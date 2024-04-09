import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Human<W extends Weapon> implements Character<W> {
    private String name;
    private int points;
    private int stamina = 10;
    private int attack;
    private int speed;
    private W weapon;
    private Scanner scn = new Scanner(System.in);
    private Job job = Job.getRandomJob();
    private boolean isSpecialUsed = false;
    private Turn humanTurn;
    private double damageReduction = 1;

    public Human(String name, W weapon) {
        Random random = new Random();

        this.name = name;
        this.weapon = weapon;

        points = random.nextInt(51) + 100;
        attack = random.nextInt(21) + 20;
        speed = random.nextInt(90) + 10;

        humanTurn = new Turn(-1, name, speed);
    }

    // #region Interface methods.
    /*
     * This method attacks the selected opponent with bare hands.
     */
    @Override
    public int punch(Opponent opponent) {
        int damageDealt = (int) Math.floor((attack * 0.8) * humanTurn.getAttackModifier());
        stamina--;
        return opponent.getAttacked(damageDealt);
    }

    /*
     * This method attacks the selected opponent with Character's weapon
     * and its attack style according to the user input.
     */
    @Override
    public int attackWithWeapon(Opponent opponent, int choice) {
        int damageDealt;
        if (weapon instanceof Weapon.Sword) {
            switch (choice) {
                case 1:
                    stamina -= 2;
                    damageDealt = ((Weapon.Sword) weapon).slashAttack(attack,
                            humanTurn.getAttackModifier());
                    return opponent.getAttacked(damageDealt);
                case 2:
                    stamina -= 2;
                    damageDealt = ((Weapon.Sword) weapon).stabAttack(attack,
                            humanTurn.getAttackModifier());
                    return opponent.getAttacked(damageDealt);
            }
        } else if (weapon instanceof Weapon.Spear) {
            switch (choice) {
                case 1:
                    stamina -= 2;
                    damageDealt = (((Weapon.Spear) weapon).stabAttack(attack,
                            humanTurn.getAttackModifier()));
                    return opponent.getAttacked(damageDealt);
                case 2:
                    stamina -= 2;
                    damageDealt = ((Weapon.Spear) weapon).thrownAttack(attack,
                            humanTurn.getAttackModifier());
                    return opponent.getAttacked(damageDealt);
            }
        } else if (weapon instanceof Weapon.Bow) {
            switch (choice) {
                case 1:
                    stamina -= 1;
                    damageDealt = ((Weapon.Bow) weapon).singleArrowAttack(attack,
                            humanTurn.getAttackModifier());
                    return opponent.getAttacked(damageDealt);
                case 2:
                    stamina -= 3;
                    damageDealt = ((Weapon.Bow) weapon).twoArrowsAttack(attack,
                            humanTurn.getAttackModifier());
                    return opponent.getAttacked(damageDealt);
            }
        }
        return 0;
    }

    /*
     * This method allows damage reduction for 1 turn.
     * Also increases the stamina by 3.
     */
    @Override
    public void guard() {
        damageReduction = 0.25;
        stamina += 3;
    }

    /*
     * This method makes a special action over an opponent.
     * The action is chosen by Character's job.
     */
    @Override
    public void specialAction(int move, ArrayList<Opponent> opponentArrayList,
            ArrayList<Human<Weapon>> charactersArrayList,
            Human<Weapon> currentHuman) {
        try {
            if (isSpecialUsed) {
                throw new SpecialAlreadyUsedException("You already used your special attack!");
            }
            if (job.equals(Job.Knight)) {
                humanTurn.setAttackModifier(3);
                humanTurn.setSpecialTurn(humanTurn.getCurrentTurn() + 1);
                humanTurn.skipCurrentTurn();
            } else if (job.equals(Job.Hunter)) {
                humanTurn.setAttackModifier(0.5);
                makeHumanMove(move, opponentArrayList, charactersArrayList, currentHuman);
                humanTurn.setSpecialTurn(humanTurn.getCurrentTurn() + 1);
                humanTurn.nextTurn();
            } else if (job.equals(Job.Squire)) {
                humanTurn.setAttackModifier(0.5);
                makeHumanMove(move, opponentArrayList, charactersArrayList, currentHuman);
                stamina = 10;
                humanTurn.nextTurn();

            } else if (job.equals(Job.Villager)) {
                makeHumanMove(move, opponentArrayList, charactersArrayList, currentHuman);
                humanTurn.nextTurn();
                // No special power.
            }
            isSpecialUsed = true;
        } catch (SpecialAlreadyUsedException e) {
            System.out.println(e.getMessage());
            makeHumanMove(move, opponentArrayList, charactersArrayList, currentHuman);
        }
    }

    @Override
    public void run(ArrayList<Opponent> opponentsArrayList) {
        System.out.println("Your character(s) started running away. The battle ends!");
        System.out.println("Thanks for playing!");
        System.out.println("Remaining opponents: ");
        for (Opponent opponent : opponentsArrayList) {
            System.out.println(
                    String.format("Opponent %d remaining points: %d.", opponent.getOpponentId(), opponent.getPoints()));
        }
        System.exit(0);
    }
    // #endregion

    public void makeHumanMove(int move, ArrayList<Opponent> opponentArrayList,
            ArrayList<Human<Weapon>> charactersArrayList,
            Human<Weapon> currentHuman) {

        System.out.println(String.format("Move %d - It is the turn of %s.", move, name));
        System.out.println("[1] Punch");
        System.out.println("[2] Attack with weapon");
        System.out.println("[3] Guard");
        System.out.println("[4] Special Action");
        System.out.println("[5] Run");

        try {
            Opponent attackedOpponent;
            int dealtDamage;
            while (true) {
                System.out.print("Please select an option: ");
                int option = Integer.parseInt(scn.nextLine());
                switch (option) {
                    case 1:
                        attackedOpponent = getAtackedOpponent(opponentArrayList);
                        try {
                            dealtDamage = punch(attackedOpponent);
                            if (stamina < 0) {
                                stamina = 0;
                                throw new InsufficientStaminaException("Not enough stamina.");
                            }
                            humanTurn.nextTurn();
                            displayAttackResult(move, attackedOpponent, dealtDamage);
                            return;
                        } catch (InsufficientStaminaException e) {
                            System.out.println(e.getMessage());
                            makeHumanMove(move, opponentArrayList, charactersArrayList, currentHuman);
                            return;
                        }

                    case 2:
                        int staminaBefore = stamina;
                        int choice;
                        while (true) {
                            try {
                                System.out.print(String.format("Please select weapon attack type (%s):",
                                        getWeapon().toString()));
                                choice = Integer.parseInt(scn.nextLine());
                                if (choice != 1 && choice != 2) {
                                    throw new NumberFormatException("Invalid option. Please select either 1 or 2.");
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println(e.getMessage());

                            }
                        }
                        try {
                            attackedOpponent = getAtackedOpponent(opponentArrayList);
                            dealtDamage = attackWithWeapon(attackedOpponent, choice);
                            if (stamina < 0) {
                                stamina = staminaBefore;
                                throw new InsufficientStaminaException("Not enough stamina.");
                            }
                            humanTurn.nextTurn();
                            displayAttackResult(move, attackedOpponent, dealtDamage);
                            return;
                        } catch (InsufficientStaminaException e) {
                            System.out.println(e.getMessage());
                            makeHumanMove(move, opponentArrayList, charactersArrayList, currentHuman);
                            return;
                        }

                    case 3:
                        guard();
                        humanTurn.nextTurn();
                        return;
                    case 4:
                        specialAction(move, opponentArrayList, charactersArrayList, currentHuman);
                        return;
                    case 5:
                        run(opponentArrayList);
                        return;

                }
                throw new NumberFormatException("Invalid option. Please select a number between 1 and 5.");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            makeHumanMove(move, opponentArrayList, charactersArrayList, currentHuman);
        }

    }

    public void displayAttackResult(int move, Opponent opponent, int dealtDamage) {
        System.out.println(String.format("Move %d Result: %s attacks Opponent %d. Deals %d damage.", move,
                name, opponent.getOpponentId(), dealtDamage));
        System.out.println(String.format("%s, Job: %s, Points : %d, Stamina: %d", name, job.name(),
                points, stamina));
        System.out.println(String.format("Opponent %d, Type: %s, Points : %d", opponent.getOpponentId(),
                opponent.getOppType().getClass().getSimpleName(), opponent.getPoints()));
    }

    public int getAttacked(int oppDamage) {
        points -= oppDamage * damageReduction;
        return (int) Math.floor(oppDamage * damageReduction);
    }

    public void displayGetAttacked() {
        System.out.println(String.format("%s, Job: %s, Points : %d, Stamina: %d", getName(),
                getJob().name(), getPoints(), getStamina()));
    }

    public Opponent getAtackedOpponent(ArrayList<Opponent> opponentsArrayList) {
        int opponentId;
        while (true) {
            try {
                System.out.print("Please enter an opponent id:");
                opponentId = Integer.parseInt(scn.nextLine());
                for (Opponent opponent : opponentsArrayList) {
                    if (opponent.getOpponentId() == opponentId) {
                        return opponent;
                    }
                }
                throw new NumberFormatException("Opponent not found. Please enter a valid opponent");
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void resetSideEffects() {
        damageReduction = 1;
    }

    @Override
    public String toString() {
        return String.format(
                "%s, Job: %s, Points : %d, Stamina: %d, Attack: %d, Speed: %d ,Weapon: %s with +%d attack", name,
                job.name(), points, stamina, attack, speed, weapon.getClass().getSimpleName(),
                weapon.getAdditionalAttack());
    }

    // Getter Methods.
    public Turn getHumanTurn() {
        return humanTurn;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStamina() {
        return stamina;
    }

    public W getWeapon() {
        return weapon;
    }

    public int getPoints() {
        return points;
    }

    public Job getJob() {
        return job;
    }

    public boolean isSpecialUsed() {
        return isSpecialUsed;
    }
}