package models.emergencies;

import enums.EmergencyLevel;
import utils.RegistrationTime;

public class HealthEmergency extends BaseEmergency{
    private Integer damage;

    public HealthEmergency(String description, EmergencyLevel emergencyLevel,
                           RegistrationTime registrationTime, Integer damage) {
        super(description, emergencyLevel, registrationTime);
        this.damage = damage;
    }

    @Override
    public Integer getResultAfterProcessing() {
        return this.damage;
    }
}
