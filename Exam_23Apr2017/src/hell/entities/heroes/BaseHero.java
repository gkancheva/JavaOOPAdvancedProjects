package hell.entities.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.entities.miscellaneous.ItemCollection;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by gery on 23.4.2017 г. at 15:23.
 */
public abstract class BaseHero implements Hero {
    private String name;
    private long strength;
    private long agility;
    private long intelligence;
    private long hitPoints;
    private long damage;
    private Inventory inventory;

    protected BaseHero(String name, long strength, long agility, long intelligence, long hitPoints, long damage) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.inventory = new HeroInventory();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength + this.inventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return this.agility + this.inventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return this.intelligence + this.inventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints + this.inventory.getTotalHitPointsBonus();
    }

    @Override
    public long getDamage() {
        return this.damage + this.inventory.getTotalDamageBonus();
    }

    @Override
    public Collection<Item> getItems() throws InvocationTargetException, IllegalAccessException {
        Collection<Item> items = null;
        Class<?> inventoryClass = this.inventory.getClass();
        Field[] inventoryFields = inventoryClass.getDeclaredFields();
        for (Field inventoryField : inventoryFields) {
            if (inventoryField.isAnnotationPresent(ItemCollection.class)) {
                inventoryField.setAccessible(true);
                try {
                    Map<String, Item> itemMap = (Map<String, Item>) inventoryField.get(this.inventory);
                    items = itemMap.values();
                    break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return items;
    }

    @Override
    public void addItem(Item item) {
        this.inventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.inventory.addRecipeItem(recipe);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hero: %s, Class: %s",
                        this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("HitPoints: %d, Damage: %d",
                        this.getHitPoints(), this.getDamage()))
                .append(System.lineSeparator())
                .append(String.format("Strength: %d", this.getStrength()))
                .append(System.lineSeparator())
                .append(String.format("Agility: %d", this.getAgility()))
                .append(System.lineSeparator())
                .append(String.format("Intelligence: %d", this.getIntelligence()))
                .append(System.lineSeparator())
                .append(String.format("Items:"));
        Collection<Item> items = null;
        try {
            items = this.getItems();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if(items.size() > 0) {
            builder.append(System.lineSeparator());
            for (Item item : items) {
                builder.append(item.toString());
            }
        } else {
            builder.append(" None").append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

}
