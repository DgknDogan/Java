import java.util.ArrayList;

public interface Character<W> {
    public int punch(Opponent opponent);

    public int attackWithWeapon(Opponent opponent, int choice);

    public void guard();

    public void specialAction(int move, ArrayList<Opponent> opponentArrayList,
            ArrayList<Human<Weapon>> charactersArrayList,
            Human<Weapon> currentHuman);

    public void run(ArrayList<Opponent> opponentsArrayList);
}