import java.util.Random;

public enum Job {
    Knight, Hunter, Squire, Villager;

    public static Job getRandomJob() {
        Random rnd = new Random();
        return values()[rnd.nextInt(values().length)];
    }
}