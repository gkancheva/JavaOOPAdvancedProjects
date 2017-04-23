package models.centers;

public interface EmergencyCenter {
    Boolean isForRetirement();
    Integer getAmountOfMaximumEmergencies();
    String getName();
    void processEmergency();
    Integer getProcessedEmergencies();
}
