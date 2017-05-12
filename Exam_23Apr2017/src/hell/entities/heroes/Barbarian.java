package hell.entities.heroes;

import hell.interfaces.Inventory;

/**
 * Created by gery on 23.4.2017 г. at 15:33.
 */
public class Barbarian extends BaseHero {
    private static final long DEFAULT_STRENGTH = 90L;
    private static final long DEFAULT_AGILITY = 25L;
    private static final long DEFAULT_INTELLIGENCE = 10L;
    private static final long DEFAULT_HIT_POINTS = 350L;
    private static final long DEFAULT_DAMAGE = 150L;

    public Barbarian(String name) {
        super(name, DEFAULT_STRENGTH, DEFAULT_AGILITY, DEFAULT_INTELLIGENCE, DEFAULT_HIT_POINTS,
                DEFAULT_DAMAGE);
    }

}