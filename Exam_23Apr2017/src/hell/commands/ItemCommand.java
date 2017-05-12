package hell.commands;

import hell.entities.miscellaneous.Inject;
import hell.interfaces.Hell;
import hell.interfaces.Item;

/**
 * Created by gery on 24.4.2017 г. at 22:26.
 */
public class ItemCommand extends BaseCommand {

    @Inject
    private String[] arguments;

    public ItemCommand(Hell hellSystem) {
        super(hellSystem);
    }

    @Override
    public String execute() {
        Item item = super.getHellSystem().getItemFactory().createItem(arguments);
        return super.getHellSystem().addItemToHero(arguments[2], item);
    }
}
