package com.models;

import com.models.structures.Floor;

class PowerConsumptionEvaluator {

    private final static int MAIN_CORRIDOR_CONSUMPTION_LIMIT = 15;
    private final static int SUB_CORRIDOR_CONSUMPTION_LIMIT = 10;

    private static int getPowerConsumptionLimit(Floor floor) {
        return floor.mainCorridorCount() * MAIN_CORRIDOR_CONSUMPTION_LIMIT
            + floor.subCorridorCount() * SUB_CORRIDOR_CONSUMPTION_LIMIT;
    }

    static boolean isResourceUtilizationCrossed(Floor floor) {
        return floor.getConsumption() > getPowerConsumptionLimit(floor);
    }

}
