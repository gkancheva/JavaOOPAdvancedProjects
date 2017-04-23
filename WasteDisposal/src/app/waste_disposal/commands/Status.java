package app.waste_disposal.commands;

import app.waste_disposal.contracts.GarbageProcessor;
import app.waste_disposal.contracts.RecyclingSystem;

/**
 * Created by gery on 20.4.2017 г. at 13:03.
 */
public class Status extends BaseCommand{

    public Status(GarbageProcessor processor, RecyclingSystem system) {
        super(processor, system);
    }

    @Override
    public String execute() {
        return super.getRecyclingSystem().toString();
    }
}
