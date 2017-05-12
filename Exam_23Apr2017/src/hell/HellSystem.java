package hell;

import hell.interfaces.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by gery on 23.4.2017 г. at 16:09.
 */
public class HellSystem implements Hell {

    private HeroCreatable heroFactory;
    private ItemCreatable itemFactory;
    private RecipeCreatable recipeFactory;
    private Map<String, Hero> heroes;

    public HellSystem(Map<String, Hero> heroes, HeroCreatable heroFactory, ItemCreatable itemFactory, RecipeCreatable recipeFactory) {
        this.setHeroes(heroes);
        this.heroFactory = heroFactory;
        this.itemFactory = itemFactory;
        this.recipeFactory = recipeFactory;
    }

    public HellSystem(HeroCreatable heroFactory, ItemCreatable itemFactory, RecipeCreatable recipeFactory) {
        this(new LinkedHashMap<>(), heroFactory, itemFactory, recipeFactory);
    }

    public void setHeroes(Map<String, Hero> heroes) {
        if(null == heroes) {
            this.heroes = new LinkedHashMap<>();
        }
        this.heroes = heroes;
    }

    @Override
    public String addHero(Hero hero) {
        this.heroes.put(hero.getName(), hero);
        return String.format("Created %s - %s",
                hero.getClass().getSimpleName(),
                hero.getName());
    }

    @Override
    public String addItemToHero(String heroName, Item item) {
        this.heroes.get(heroName).addItem(item);
        return String.format("Added item - %s to Hero - %s",
                item.getName(), heroName);
    }

    @Override
    public String inspect(String name) {
        return this.heroes.get(name).toString();
    }

    @Override
    public String addRecipeToHero(String heroName, Recipe item) {
        this.heroes.get(heroName).addRecipe(item);
        return String.format("Added recipe - %s to Hero - %s",
                item.getName(), heroName);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        List<Hero> sortedHeroes = this.heroes.values().stream()
                .sorted((h1, h2) -> {
                    long h1Sum = h1.getStrength() + h1.getAgility() + h1.getIntelligence();
                    long h2Sum = h2.getStrength() + h2.getAgility() + h2.getIntelligence();
                    if(Long.compare(h2Sum, h1Sum) == 0) {
                        long h1Points = h1.getHitPoints() + h1.getDamage();
                        long h2Points = h2.getHitPoints() + h2.getDamage();
                        return Long.compare(h2Points, h1Points);
                    } else {
                        return Long.compare(h2Sum, h1Sum);
                    }
                }).collect(Collectors.toList());
        for (Hero hero : sortedHeroes) {
            builder.append(String.format("%d. %s: %s",
                        counter,
                        hero.getClass().getSimpleName(),
                        hero.getName()))
                    .append(System.lineSeparator())
                    .append(String.format("###HitPoints: %d", hero.getHitPoints()))
                    .append(System.lineSeparator())
                    .append(String.format("###Damage: %d", hero.getDamage()))
                    .append(System.lineSeparator())
                    .append(String.format("###Strength: %d", hero.getStrength()))
                    .append(System.lineSeparator())
                    .append(String.format("###Agility: %d", hero.getAgility()))
                    .append(System.lineSeparator())
                    .append(String.format("###Intelligence: %d", hero.getIntelligence()))
                    .append(System.lineSeparator())
                    .append("###Items: ");
            Collection<Item> items = null;
            try {
                items = hero.getItems();
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            if(items.size() == 0) {
                builder.append("None").append(System.lineSeparator());
            } else {
                int count = items.size();
                for (Item item : items) {
                    if(--count == 0) {
                        builder.append(item.getName()).append(System.lineSeparator());
                    } else {
                        builder.append(item.getName() + ", ");
                    }
                }
            }
            counter++;
        }
        return builder.toString();
    }

    public HeroCreatable getHeroFactory() {
        return this.heroFactory;
    }

    public ItemCreatable getItemFactory() {
        return this.itemFactory;
    }

    public RecipeCreatable getRecipeFactory() {
        return this.recipeFactory;
    }
}
