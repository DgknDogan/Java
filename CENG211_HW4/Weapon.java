import java.util.Random;

public abstract class Weapon {
    private static int additionalAttack;

    public Weapon() {
        Random random = new Random();
        additionalAttack = random.nextInt(10) + 10;
    }

    public static Weapon getRandomWeapon() {
        Random rnd = new Random();
        Weapon[] weaponInstances = { new Sword(), new Spear(), new Bow() };
        return weaponInstances[rnd.nextInt(weaponInstances.length)];
    }

    public int getAdditionalAttack() {
        return additionalAttack;
    }

    // Weapon Classes.
    public static class Sword extends Weapon {
        public int slashAttack(int attack, double attackModifier) {
            return (int) ((additionalAttack + attack) * attackModifier);
        }

        public int stabAttack(int attack, double attackModifier) {
            Random rnd = new Random();
            if ((rnd.nextInt(4) + 1) == 1)
                return 0;
            return (int) ((additionalAttack + attack) * 2 * attackModifier);
        }

        public String toString() {
            return "[1]Slash [2]Stab";
        }
    }

    public static class Spear extends Weapon {
        public int stabAttack(int attack, double attackModifier) {
            return (int) ((additionalAttack + attack) * 1.1 * attackModifier);
        }

        public int thrownAttack(int attack, double attackModifier) {
            return (int) ((additionalAttack + attack) * 2 * attackModifier);
        }

        public String toString() {
            return "[1]Stab [2]Thrown";
        }
    }

    public static class Bow extends Weapon {
        public int singleArrowAttack(int attack, double attackModifier) {
            return (int) ((additionalAttack + attack) * 0.8 * attackModifier);
        }

        public int twoArrowsAttack(int attack, double attackModifier) {
            return (int) ((additionalAttack + attack) * 2.5 * attackModifier);
        }

        public String toString() {
            return "[1]Single [2]Two Arrow";
        }
    }
}
