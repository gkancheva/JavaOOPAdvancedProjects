package commands;

import annotations.InjectArgs;
import commands.BaseCommand;
import core.ManagementSystem;
import models.centers.EmergencyCenter;
import models.centers.PoliceServiceCenter;

public class RegisterPoliceServiceCenterCommand extends BaseCommand {
    @InjectArgs
    private String[] params;
    private EmergencyCenter center;

    public RegisterPoliceServiceCenterCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        String name = this.params[1];
        Integer amountOfMaxEmergencies = Integer.valueOf(this.params[2]);
        this.center = new PoliceServiceCenter(name, amountOfMaxEmergencies);
        return super.getManagementSystem().registerPoliceServiceCenter(this.center);
    }
}