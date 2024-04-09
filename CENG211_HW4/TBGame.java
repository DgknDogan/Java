import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class TBGame {

    public static void main(String[] args) {
        Initializer initializer = new Initializer();

        Menu menu = new Menu(initializer.opponentsArrayList, initializer.charactersArrayList, initializer.turnOrder);

        initializer.createOpponentsArray();
        menu.displayEnemies();
        initializer.createCharactersArray();
        menu.displayCharacters();
        initializer.createTurnOrder();
        menu.startBattle();
    }

    private static class Initializer {
        private Scanner scn = new Scanner(System.in);
        private ArrayList<Opponent> opponentsArrayList = new ArrayList<Opponent>();
        private ArrayList<Human<Weapon>> charactersArrayList = new ArrayList<Human<Weapon>>();
        private Queue<Turn> turnOrder = new ArrayDeque<>();

        public void createOpponentsArray() {
            Random rnd = new Random();
            int opponentNumber = rnd.nextInt(4) + 1;
            for (int i = 1; i <= opponentNumber; i++) {
                Opponent opponent = new Opponent(i);
                opponentsArrayList.add(opponent);
            }
        }

        public void createCharactersArray() {
            int numOfCharacters = 0;
            while (true) {
                try {
                    System.out.print("Please enter the number of characters to create: ");
                    numOfCharacters = Integer.parseInt(scn.nextLine());
                    if (1 <= numOfCharacters && numOfCharacters <= 3) {
                        break;
                    } else {
                        throw new NumberFormatException("Please enter a number between 1 and 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println();
            for (int i = 0; i < numOfCharacters; i++) {
                String name = "";
                while (true) {
                    System.out.print("Enter name for your " + (i + 1) + "." + " character:");
                    try {
                        name = scn.nextLine();
                        if (!charactersArrayList.isEmpty()) {
                            for (Human<Weapon> human : charactersArrayList) {
                                if (human.getName().equals(name)) {
                                    throw new NotAUniqueNameException("Not a unique name.");
                                }
                            }
                        }
                        break;
                    } catch (NotAUniqueNameException e) {
                        System.out.println(e.getMessage());
                    }
                }
                Human<Weapon> human = new Human<Weapon>(name, Weapon.getRandomWeapon());
                charactersArrayList.add(human);
            }
            System.out.println();
        }

        public void createTurnOrder() {
            ArrayList<Turn> turnList = new ArrayList<>();
            for (Human<Weapon> h : charactersArrayList) {
                turnList.add(h.getHumanTurn());
            }
            for (Opponent o : opponentsArrayList) {
                turnList.add(o.getOpponentTurn());
            }

            for (int i = 0; i < turnList.size() - 1; i++) {
                for (int j = 0; j < turnList.size() - 1 - i; j++) {
                    if (turnList.get(j).compareTo(turnList.get(j + 1)) < 0) {
                        Turn temp = turnList.get(j);
                        turnList.set(j, turnList.get(j + 1));
                        turnList.set(j + 1, temp);
                    }
                }
            }
            for (Turn turn : turnList) {
                turnOrder.add(turn);
            }
        }
    }

    private static class Menu {
        private ArrayList<Opponent> opponentsArrayList;
        private ArrayList<Opponent> modifiedOpponentsArrayList;
        private ArrayList<Human<Weapon>> charactersArrayList;
        private Queue<Turn> turnOrder;
        private int move = 1;

        public Menu(ArrayList<Opponent> opponentsArrayList, ArrayList<Human<Weapon>> charactersArrayList,
                Queue<Turn> turnOrder) {
            this.opponentsArrayList = opponentsArrayList;
            this.charactersArrayList = charactersArrayList;
            this.turnOrder = turnOrder;

            System.out.println("Welcome to TBGame!");
            System.out.println();
        }

        // Display Methods
        public void displayEnemies() {
            System.out.println("These opponents appeared in front of you:");
            for (Opponent o : opponentsArrayList) {
                System.out.println(o.toString());
            }
            System.out.println();
        }

        public void displayCharacters() {
            int counter = 1;
            for (Human<Weapon> h : charactersArrayList) {
                System.out.println(String.format("The stats of your %d. character:", counter));
                System.out.println(h.toString());
                counter++;
            }
            System.out.println();
        }

        public void displayTurnOrder() {
            System.out.print("*** Turn Order: ");
            for (Turn turn : turnOrder) {
                if (turn.getOwnerName().isEmpty()) {
                    System.out.print(String.format("Opponent %d, ", turn.getOwnerId()));
                } else {
                    System.out.print(String.format("%s, ", turn.getOwnerName()));
                }
            }
            System.out.println("***");
            System.out.println();
        }

        // War methods.
        public void startBattle() {
            modifiedOpponentsArrayList = new ArrayList<>(opponentsArrayList);
            System.out.println("The battle starts!");
            System.out.println();
            displayTurnOrder();

            while (true) {
                opponentsArrayList.clear();
                opponentsArrayList.addAll(modifiedOpponentsArrayList);
                getWinningCondition();
                makeMove();
                if (modifiedOpponentsArrayList.size() != opponentsArrayList.size()) {
                    for (int i = 1; i <= modifiedOpponentsArrayList.size() - opponentsArrayList.size(); i++) {
                        turnOrder.add(modifiedOpponentsArrayList.get(modifiedOpponentsArrayList.size() - i)
                                .getOpponentTurn());
                    }
                    opponentsArrayList.clear();
                    opponentsArrayList.addAll(modifiedOpponentsArrayList);
                }
            }
        }

        private void getWinningCondition() {
            removeDiedEntities();

            if (charactersArrayList.isEmpty()) {
                System.out.println("you lost the game");
                System.exit(0);
            } else if (modifiedOpponentsArrayList.isEmpty()) {
                System.out.println("You won the game");
                System.exit(0);
            }

        }

        private void removeDiedEntities() {
            for (Human<Weapon> human : charactersArrayList) {
                if (human.getPoints() <= 0) {
                    charactersArrayList.remove(human);
                    turnOrder.remove(human.getHumanTurn());
                }
            }
            for (Opponent opponent : opponentsArrayList) {
                if (opponent.getPoints() <= 0) {
                    modifiedOpponentsArrayList.remove(opponent);
                    turnOrder.remove(opponent.getOpponentTurn());
                }
            }

            opponentsArrayList.clear();
            opponentsArrayList.addAll(modifiedOpponentsArrayList);
        }

        private void makeMove() {
            for (Turn turn : turnOrder) {
                if (turn.getCurrentTurn() == turn.getNextTurn() - 1) {
                    if (!turn.getOwnerName().isEmpty()) {
                        // For human play.
                        humanPlay(turn);
                    }
                    // For opponent play.
                    else {
                        opponentPlay(turn);
                    }
                }
                turn.incrementNextTurn();
                break;
            }
        }

        private void humanPlay(Turn turn) {
            for (Human<Weapon> human : charactersArrayList) {
                if (turn.getOwnerName().equals(human.getName()) && human.getPoints() > 0) {
                    if (turn.isSpecialTurn() && human.getJob().equals(Job.Hunter)) {
                        human.makeHumanMove(move, opponentsArrayList, charactersArrayList, human);
                        turn.resetAttackModifier();
                        move++;
                    }
                    if (!turn.isSpecialTurn()) {
                        human.makeHumanMove(move, opponentsArrayList, charactersArrayList, human);
                        move++;
                    } else if (turn.isSpecialTurn()) {
                        human.makeHumanMove(move, opponentsArrayList, charactersArrayList, human);
                        move++;
                        turn.resetAttackModifier();
                        human.resetSideEffects();
                    }
                    Turn rturn = turnOrder.remove();
                    turnOrder.add(rturn);
                    displayTurnOrder();
                    break;
                }

            }
        }

        private void opponentPlay(Turn turn) {
            Random rnd = new Random();
            for (Opponent opponent : opponentsArrayList) {
                if ((turn.getOwnerId() == opponent.getOpponentId()) && opponent.getPoints() > 0) {
                    Human<Weapon> attackedHuman = charactersArrayList
                            .get(rnd.nextInt(charactersArrayList.size()));

                    opponent.makeOpponentMove(opponent, attackedHuman, modifiedOpponentsArrayList,
                            move);
                    move++;
                    while (true) {
                        if (opponent.getOpponentTurn().isSpecialTurn()
                                && opponent.getOppType().getClass().equals(OpponentTypes.Goblin.class)) {
                            opponent.makeOpponentMove(opponent, attackedHuman, modifiedOpponentsArrayList,
                                    move);

                            opponent.resetSideEffects();
                            move++;
                        } else {
                            opponent.getOpponentTurn().resetAttackModifier();
                            opponent.getOpponentTurn().nextTurn();
                            break;
                        }
                    }

                    Turn rturn = turnOrder.remove();
                    turnOrder.add(rturn);
                    break;
                }
            }
        }

    }
}