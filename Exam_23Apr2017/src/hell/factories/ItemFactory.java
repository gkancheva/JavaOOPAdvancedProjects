package hell.factories;

import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.interfaces.Item;
import hell.interfaces.ItemCreatable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gery on 23.4.2017 г. at 16:47.
 */
public class ItemFactory implements ItemCreatable{

    @Override
    public Item createItem(String[] args) {
        String name = args[1];
        int strengthBonus = Integer.parseInt(args[3]);
        int agilityBonus = Integer.parseInt(args[4]);
        int intelligenceBonus = Integer.parseInt(args[5]);
        int hitpointsBonus = Integer.parseInt(args[6]);
        int damageBonus = Integer.parseInt(args[7]);
        return new CommonItem(name, strengthBonus, agilityBonus,
                intelligenceBonus, hitpointsBonus, damageBonus);
    }
}
