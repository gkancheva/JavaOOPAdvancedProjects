package hell.commands;

import hell.entities.miscellaneous.Inject;
import hell.interfaces.Hell;
import hell.interfaces.Hero;

/**
 * Created by gery on 24.4.2017 г. at 10:08.
 */
public class HeroCommand extends BaseCommand {

    @Inject
    private String[] arguments;

    public HeroCommand(Hell hellSystem) {
        super(hellSystem);
    }

    @Override
    public String execute() {
        Hero hero = super.getHellSystem().getHeroFactory().createHero(arguments);
        return super.getHellSystem().addHero(hero);
    }
}
