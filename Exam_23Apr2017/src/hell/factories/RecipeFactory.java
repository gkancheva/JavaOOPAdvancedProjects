package hell.factories;

import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.interfaces.Item;
import hell.interfaces.ItemCreatable;
import hell.interfaces.Recipe;
import hell.interfaces.RecipeCreatable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gery on 23.4.2017 г. at 16:47.
 */
public class RecipeFactory implements RecipeCreatable {

    @Override
    public Recipe createRecipe(String[] args) {
        String name = args[1];
        int strengthBonus = Integer.parseInt(args[3]);
        int agilityBonus = Integer.parseInt(args[4]);
        int intelligenceBonus = Integer.parseInt(args[5]);
        int hitpointsBonus = Integer.parseInt(args[6]);
        int damageBonus = Integer.parseInt(args[7]);
        List<String> requiredItems = Arrays.stream(args)
                .skip(8).collect(Collectors.toList());
        return new RecipeItem(name, strengthBonus, agilityBonus,
                intelligenceBonus, hitpointsBonus, damageBonus, requiredItems);
    }
}
