package hell.entities.items;

import hell.interfaces.Recipe;

import java.util.*;

/**
 * Created by gery on 23.4.2017 г. at 15:37.
 */
public class RecipeItem extends BaseItem implements Recipe {

    private Collection<String> requiredItems;

    public RecipeItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus, Collection<String> requiredItems) {
        super(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
        this.setRequiredItems(requiredItems);
    }

    private void setRequiredItems(Collection<String> requiredItems) {
        if(null == requiredItems || requiredItems.size() == 0){
            this.requiredItems = new LinkedHashSet<>();
        }
        this.requiredItems = requiredItems;
    }

    @Override
    public List<String> getRequiredItems() {
        return Collections.unmodifiableList(new ArrayList<>(this.requiredItems));
    }
}
