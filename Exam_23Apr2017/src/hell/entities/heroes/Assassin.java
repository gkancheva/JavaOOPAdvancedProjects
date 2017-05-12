package hell.entities.heroes;

import hell.interfaces.Inventory;

/**
 * Created by gery on 23.4.2017 г. at 15:33.
 */
public class Assassin extends BaseHero {
    private static final long DEFAULT_STRENGTH = 25L;
    private static final long DEFAULT_AGILITY = 100L;
    private static final long DEFAULT_INTELLIGENCE = 15L;
    private static final long DEFAULT_HIT_POINTS = 150L;
    private static final long DEFAULT_DAMAGE = 300L;

    public Assassin(String name) {
        super(name, DEFAULT_STRENGTH, DEFAULT_AGILITY, DEFAULT_INTELLIGENCE,
                DEFAULT_HIT_POINTS, DEFAULT_DAMAGE);
    }
}
