package hell;

import hell.core.Engine;
import hell.factories.HeroFactory;
import hell.factories.ItemFactory;
import hell.factories.RecipeFactory;
import hell.interfaces.*;
import hell.interpreter.CommandInterpreter;
import hell.io.ConsoleReader;
import hell.io.ConsoleWriter;
import hell.io.Reader;
import hell.io.Writer;

public class Main {
    public static void main(String[] args) {

        HeroCreatable factory = new HeroFactory();
        ItemCreatable itemFactory = new ItemFactory();
        RecipeCreatable recipeFactory = new RecipeFactory();

        Hell hellSystem = new HellSystem(factory, itemFactory, recipeFactory);

        Interpreter interpreter = new CommandInterpreter(hellSystem);
        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();

        Runnable engine = new Engine(reader, writer, hellSystem, interpreter);
        engine.run();
    }
}