package commands;

import annotations.InjectArgs;
import commands.BaseCommand;
import core.ManagementSystem;
import models.centers.EmergencyCenter;
import models.centers.FiremanServiceCenter;

public class RegisterFireServiceCenterCommand extends BaseCommand {

    @InjectArgs
    private String[] params;
    private EmergencyCenter center;

    public RegisterFireServiceCenterCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        String name = this.params[1];
        Integer amountOfMaxEmergencies = Integer.valueOf(this.params[2]);
        this.center = new FiremanServiceCenter(name, amountOfMaxEmergencies);
        return super.getManagementSystem().registerFireServiceCenter(this.center);
    }
}
