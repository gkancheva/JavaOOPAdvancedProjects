package models.emergencies;

import enums.EmergencyLevel;
import utils.RegistrationTime;

public class PropertyEmergency extends BaseEmergency{
    private Integer numberOfCasualties;

    public PropertyEmergency(String description, EmergencyLevel emergencyLevel,
                             RegistrationTime registrationTime, Integer numberOfCasualties) {
        super(description, emergencyLevel, registrationTime);
        this.numberOfCasualties = numberOfCasualties;
    }

    @Override
    public Integer getResultAfterProcessing() {
        return this.numberOfCasualties;
    }
}