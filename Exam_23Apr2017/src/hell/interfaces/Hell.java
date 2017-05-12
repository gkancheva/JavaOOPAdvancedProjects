package hell.interfaces;

/**
 * Created by gery on 23.4.2017 г. at 16:09.
 */
public interface Hell {
    String addHero(Hero hero);
    String addItemToHero(String heroName, Item item);
    String inspect(String heroName);
    String addRecipeToHero(String heroName, Recipe item);
    HeroCreatable getHeroFactory();
    ItemCreatable getItemFactory();
    RecipeCreatable getRecipeFactory();
}
