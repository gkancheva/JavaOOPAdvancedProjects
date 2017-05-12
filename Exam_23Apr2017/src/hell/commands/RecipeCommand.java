package hell.commands;

import hell.entities.miscellaneous.Inject;
import hell.interfaces.Hell;
import hell.interfaces.Recipe;

/**
 * Created by gery on 24.4.2017 г. at 22:26.
 */
public class RecipeCommand extends BaseCommand {

    @Inject
    private String[] arguments;

    public RecipeCommand(Hell hellSystem) {
        super(hellSystem);
    }

    @Override
    public String execute() {
        Recipe recipe = super.getHellSystem().getRecipeFactory().createRecipe(arguments);
        return super.getHellSystem().addRecipeToHero(arguments[2], recipe);
    }
}
