package models.emergencies;

import enums.EmergencyLevel;
import utils.RegistrationTime;

public interface Emergency {
    EmergencyLevel getEmergencyLevel();
    RegistrationTime getRegistrationTime();
    Integer getResultAfterProcessing();

}
