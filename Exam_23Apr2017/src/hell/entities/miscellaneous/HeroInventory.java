package hell.entities.miscellaneous;

import hell.entities.items.CommonItem;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.util.*;

public class HeroInventory implements Inventory {

    @ItemCollection
    private Map<String, Item> commonItems;

    private Map<String, Recipe> recipeItems;

    public HeroInventory() {
        this.commonItems = new LinkedHashMap<>();
        this.recipeItems = new LinkedHashMap<>();
    }

    @Override
    public long getTotalStrengthBonus() {
        return this.commonItems.entrySet()
                .stream()
                .mapToInt((x) -> x.getValue().getStrengthBonus())
                .sum();
    }

    @Override
    public long getTotalAgilityBonus() {
        return this.commonItems.entrySet()
                .stream()
                .mapToInt((x) -> x.getValue().getAgilityBonus())
                .sum();
    }

    @Override
    public long getTotalIntelligenceBonus() {
        return this.commonItems.entrySet()
                .stream()
                .mapToInt((x) -> x.getValue().getIntelligenceBonus())
                .sum();
    }

    @Override
    public long getTotalHitPointsBonus() {
        return this.commonItems.entrySet()
                .stream()
                .mapToInt((x) -> x.getValue().getHitPointsBonus())
                .sum();
    }

    @Override
    public long getTotalDamageBonus() {
        return this.commonItems.entrySet()
                .stream()
                .mapToInt((x) -> x.getValue().getDamageBonus())
                .sum();
    }

    @Override
    public void addCommonItem(Item item) {
        if(null == item) {
            throw new NullPointerException();
        }
        if(this.commonItems.containsKey(item.getName())) {
            throw new IllegalArgumentException();
        }
        this.commonItems.put(item.getName(), item);
        this.checkRecipes();
    }

    @Override
    public void addRecipeItem(Recipe recipe) {
        if(null == recipe) {
            throw new NullPointerException();
        }
        if(this.recipeItems.containsKey(recipe.getName())) {
            throw new IllegalArgumentException();
        }
        this.recipeItems.put(recipe.getName(), recipe);
        this.checkRecipes();
    }

    private void checkRecipes() {
        for (Recipe recipe : this.recipeItems.values()) {
            List<String> requiredItems = new ArrayList<>(recipe.getRequiredItems());
            for (Item item : this.commonItems.values()) {
                if (requiredItems.contains(item.getName())) {
                    requiredItems.remove(item.getName());
                }
            }
            if (requiredItems.isEmpty()) {
                this.combineRecipe(recipe);
            }
        }
    }

    private void combineRecipe(Recipe recipe) {
        for (int i = 0; i < recipe.getRequiredItems().size(); i++) {
            String item = recipe.getRequiredItems().get(i);
            this.commonItems.remove(item);
        }
        Item newItem = new CommonItem(
                recipe.getName(),
                recipe.getStrengthBonus(),
                recipe.getAgilityBonus(),
                recipe.getIntelligenceBonus(),
                recipe.getHitPointsBonus(),
                recipe.getDamageBonus());
        this.commonItems.put(newItem.getName(), newItem);
        //this.recipeItems.remove(recipe.getName());
    }
}