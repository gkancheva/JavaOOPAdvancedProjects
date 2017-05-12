package hell.commands;

import hell.entities.miscellaneous.Inject;
import hell.interfaces.Hell;

/**
 * Created by gery on 24.4.2017 г. at 22:26.
 */
public class InspectCommand extends BaseCommand {

    @Inject
    private String[] arguments;

    public InspectCommand(Hell hellSystem) {
        super(hellSystem);
    }

    @Override
    public String execute() {
        return super.getHellSystem().inspect(arguments[1]);
    }
}
