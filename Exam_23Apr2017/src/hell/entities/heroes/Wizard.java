package hell.entities.heroes;

import hell.interfaces.Inventory;

/**
 * Created by gery on 23.4.2017 г. at 15:33.
 */
public class Wizard extends BaseHero {
    private static final long DEFAULT_STRENGTH = 25L;
    private static final long DEFAULT_AGILITY = 25L;
    private static final long DEFAULT_INTELLIGENCE = 100L;
    private static final long DEFAULT_HIT_POINTS = 100L;
    private static final long DEFAULT_DAMAGE = 250L;

    public Wizard(String name) {
        super(name, DEFAULT_STRENGTH, DEFAULT_AGILITY, DEFAULT_INTELLIGENCE,
                DEFAULT_HIT_POINTS, DEFAULT_DAMAGE);
    }
}
