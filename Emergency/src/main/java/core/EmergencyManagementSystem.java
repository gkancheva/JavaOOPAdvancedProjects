package core;

import collection.EmergencyRegister;
import collection.Register;
import models.centers.EmergencyCenter;
import models.emergencies.Emergency;
import utils.Constants;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EmergencyManagementSystem implements ManagementSystem {

    private static final String PROPERTY = "Property";
    private static final String HEALTH = "Health";
    private static final String ORDER = "Order";
    private static final String FIRE = "Fire";
    private static final String MEDICAL = "Medical";
    private static final String POLICE = "Police";

    private Map<String, Register<Emergency>> emergencies;
    private Map<String, Register<EmergencyCenter>> centers;
    private Map<String, Long> processedEmergencies;
    private Integer totalProcessedEmergencies;

    public EmergencyManagementSystem() {
        this.initEmergencies();
        this.initCenters();
        this.initProcessedEmergencies();
        this.totalProcessedEmergencies = 0;
    }

    @Override
    public String registerPropertyEmergency(Emergency emergency) {
        this.emergencies.get(PROPERTY).enqueueEmergency(emergency);
        return String.format(Constants.REGISTERED_PUBLIC_EMERGENCY_MESSAGE,
                PROPERTY,
                emergency.getEmergencyLevel(),
                emergency.getRegistrationTime());
    }

    @Override
    public String registerHealthEmergency(Emergency emergency) {
        this.emergencies.get(HEALTH).enqueueEmergency(emergency);
        return String.format(Constants.REGISTERED_PUBLIC_EMERGENCY_MESSAGE,
                HEALTH,
                emergency.getEmergencyLevel(),
                emergency.getRegistrationTime());
    }

    @Override
    public String registerOrderEmergency(Emergency emergency) {
        this.emergencies.get(ORDER).enqueueEmergency(emergency);
        return String.format(Constants.REGISTERED_PUBLIC_EMERGENCY_MESSAGE,
                ORDER,
                emergency.getEmergencyLevel(),
                emergency.getRegistrationTime());
    }

    @Override
    public String registerFireServiceCenter(EmergencyCenter center) {
        this.centers.get(PROPERTY).enqueueEmergency(center);
        return String.format(Constants.REGISTERED_SERVICE_CENTER_MESSAGE,
                FIRE,
                center.getName());
    }

    @Override
    public String registerMedicalServiceCenter(EmergencyCenter center) {
        this.centers.get(HEALTH).enqueueEmergency(center);
        return String.format(Constants.REGISTERED_SERVICE_CENTER_MESSAGE,
                MEDICAL,
                center.getName());
    }

    @Override
    public String registerPoliceServiceCenter(EmergencyCenter center) {
        this.centers.get(ORDER).enqueueEmergency(center);
        return String.format(Constants.REGISTERED_SERVICE_CENTER_MESSAGE,
                POLICE,
                center.getName());
    }

    @Override
    public String processEmergencies(String type) {
        Register<Emergency> emergenciesToProcess = this.emergencies.get(type);
        Register<EmergencyCenter> processingCenters = this.centers.get(type);
        while(!emergenciesToProcess.isEmpty()) {
            if(processingCenters.isEmpty()) {
                return String.format(Constants.EMERGENCIES_LEFT_TO_PROCESS_MESSAGE,
                        type,
                        emergenciesToProcess.getCount());
            }
            EmergencyCenter currentCenter = processingCenters.dequeueEmergency();
            if(currentCenter.isForRetirement()) {
                continue;
            }
            Emergency currentEmergency = emergenciesToProcess.dequeueEmergency();
            Long result = this.processedEmergencies.get(type);
            this.processedEmergencies.put(
                    type,
                    result + currentEmergency.getResultAfterProcessing());
            currentCenter.processEmergency();
            processingCenters.enqueueEmergency(currentCenter);
            this.totalProcessedEmergencies++;
        }
        return String.format(Constants.SUCCESSFULLY_RESPONDED_TO_EMERGENCIES_MESSAGE, type);
    }

    @Override
    public String emergencyReport() {
        for (Register<EmergencyCenter> registerCenter : this.centers.values()) {
            List<EmergencyCenter> temp = new LinkedList<>();
            while (!registerCenter.isEmpty()) {
                EmergencyCenter currentCenter = registerCenter.dequeueEmergency();
                if (currentCenter.isForRetirement()) {
                    continue;
                }
                temp.add(currentCenter);
            }
            for (EmergencyCenter emergencyCenter : temp) {
                registerCenter.enqueueEmergency(emergencyCenter);
            }
        }
        StringBuilder builder = new StringBuilder();
        Integer allRegisteredEmergencies = this.emergencies.entrySet()
                .stream()
                .mapToInt(e -> e.getValue().getCount())
                .sum();

        builder.append(Constants.PRRM_SERVICES_STATISTICS_MESSAGE)
                .append(System.lineSeparator());
        builder.append(String.format(Constants.SERVICE_CENTER_NB_MESSAGE,
                        FIRE, this.centers.get(PROPERTY).getCount()))
                .append(System.lineSeparator())
                .append(String.format(Constants.SERVICE_CENTER_NB_MESSAGE,
                        MEDICAL, this.centers.get(HEALTH).getCount()))
                .append(System.lineSeparator())
                .append(String.format(Constants.SERVICE_CENTER_NB_MESSAGE,
                        POLICE, this.centers.get(ORDER).getCount()))
                .append(System.lineSeparator())
                .append(String.format(Constants.TOTAL_PROCESSED_EMERGENCIES_D,
                        this.totalProcessedEmergencies))
                .append(System.lineSeparator())
                .append(String.format(Constants.CURRENTLY_REGISTERED_EMERGENCIES_D,
                        allRegisteredEmergencies))
                .append(System.lineSeparator())
                .append(String.format(Constants.TOTAL_PROPERTY_DAMAGE_FIXED_D,
                        this.processedEmergencies.get(PROPERTY)))
                .append(System.lineSeparator())
                .append(String.format(Constants.TOTAL_HEALTH_CASUALTIES_SAVED_D,
                        this.processedEmergencies.get(HEALTH)))
                .append(System.lineSeparator())
                .append(String.format(Constants.TOTAL_SPECIAL_CASES_PROCESSED_D,
                        this.processedEmergencies.get(ORDER)));
        return builder.toString().trim();
    }

    private void initEmergencies() {
        this.emergencies = new HashMap<>();
        this.emergencies.put(PROPERTY, new EmergencyRegister<>());
        this.emergencies.put(HEALTH, new EmergencyRegister<>());
        this.emergencies.put(ORDER, new EmergencyRegister<>());
    }

    private void initCenters() {
        this.centers = new HashMap<>();
        this.centers.put(PROPERTY, new EmergencyRegister<>());
        this.centers.put(HEALTH, new EmergencyRegister<>());
        this.centers.put(ORDER, new EmergencyRegister<>());
    }

    private void initProcessedEmergencies() {
        this.processedEmergencies = new HashMap<>();
        this.processedEmergencies.put(PROPERTY, 0L);
        this.processedEmergencies.put(HEALTH, 0L);
        this.processedEmergencies.put(ORDER, 0L);
    }
}
