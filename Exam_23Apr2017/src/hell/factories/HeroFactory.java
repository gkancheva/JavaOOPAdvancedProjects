package hell.factories;

import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.interfaces.HeroCreatable;
import hell.interfaces.Hero;

/**
 * Created by gery on 23.4.2017 г. at 16:39.
 */
public class HeroFactory implements HeroCreatable {

    @Override
    public Hero createHero(String[] params){
        String name = params[1];
        String type = params[2];
        switch (type) {
            case "Barbarian":
                return new Barbarian(name);
            case "Assassin":
                return new Assassin(name);
            case "Wizard":
                return new Wizard(name);
        }
        return null;
    }
}