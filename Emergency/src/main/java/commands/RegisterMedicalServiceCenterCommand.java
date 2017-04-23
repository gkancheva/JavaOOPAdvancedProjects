package commands;

import annotations.InjectArgs;
import commands.BaseCommand;
import core.ManagementSystem;
import models.centers.EmergencyCenter;
import models.centers.MedicalServiceCenter;

public class RegisterMedicalServiceCenterCommand extends BaseCommand {
    @InjectArgs
    private String[] params;
    private EmergencyCenter center;

    public RegisterMedicalServiceCenterCommand(ManagementSystem managementSystem) {
        super(managementSystem);
    }

    @Override
    public String execute() {
        String name = this.params[1];
        Integer amountOfMaxEmergencies = Integer.valueOf(this.params[2]);
        this.center = new MedicalServiceCenter(name, amountOfMaxEmergencies);
        return super.getManagementSystem().registerMedicalServiceCenter(this.center);
    }
}
