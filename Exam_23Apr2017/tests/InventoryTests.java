package tests;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by gery on 23.4.2017 г. at 20:40.
 */
public class InventoryTests {

    private Inventory heroInventory;
    private Item item;
    private Recipe recipe;

    @Before
    public void init() {
        this.heroInventory = new HeroInventory();
        this.item = Mockito.mock(Item.class);
        this.recipe = Mockito.mock(Recipe.class);
        Mockito.when(recipe.getName()).thenReturn("Test");
        Mockito.when(item.getAgilityBonus()).thenReturn(10);
        Mockito.when(item.getStrengthBonus()).thenReturn(10);
        Mockito.when(item.getDamageBonus()).thenReturn(10);
        Mockito.when(item.getHitPointsBonus()).thenReturn(10);
        Mockito.when(item.getIntelligenceBonus()).thenReturn(10);
        Mockito.when(item.getName()).thenReturn("Test");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenTryingToAddNull() {
        heroInventory.addCommonItem(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddExistingItem() {
        Item currenItem = Mockito.mock(Item.class);
        heroInventory.addCommonItem(this.item);
        Mockito.when(currenItem.getName()).thenReturn("Test");
        heroInventory.addCommonItem(currenItem);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenTryingToAddNullAsRecipe() {
        heroInventory.addRecipeItem(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddExistingRecipe() {
        Recipe current = Mockito.mock(Recipe.class);
        heroInventory.addRecipeItem(this.recipe);
        Mockito.when(current.getName()).thenReturn("Test");
        heroInventory.addCommonItem(current);
    }

    @Test
    public void shouldReturnCorrectTotalStrenghtBonus() {
        heroInventory.addCommonItem(this.item);
        Assert.assertEquals(10, heroInventory.getTotalStrengthBonus());
    }

    @Test
    public void shouldReturnCorrectTotalAgilityBonus() {
        heroInventory.addCommonItem(this.item);
        Assert.assertEquals(10, heroInventory.getTotalAgilityBonus());
    }

    @Test
    public void shouldReturnCorrectTotalIntelligenceBonus() {
        heroInventory.addCommonItem(this.item);
        Assert.assertEquals(10, heroInventory.getTotalIntelligenceBonus());
    }

    @Test
    public void shouldReturnCorrectTotalHitBonusPoints() {
        heroInventory.addCommonItem(this.item);
        Assert.assertEquals(10, heroInventory.getTotalHitPointsBonus());
    }

    @Test
    public void shouldReturnCorrectTotalDamagePoints() {
        heroInventory.addCommonItem(this.item);
        Assert.assertEquals(10, heroInventory.getTotalDamageBonus());
    }

}