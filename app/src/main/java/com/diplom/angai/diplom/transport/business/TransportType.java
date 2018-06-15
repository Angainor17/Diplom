package com.diplom.angai.diplom.transport.business;

public enum TransportType {
    BUS,
    TOPIC,
    TROLLEY;

    public static TransportType[] getTransportTypes(int transportType) {
        TransportType[] transportTypes = new TransportType[getTransportTypesCount(transportType)];
        for (int i = 0; i < transportTypes.length; i++) {
            switch (i) {
                case 0: {
                    transportTypes[i] = BUS;
                    break;
                }
                case 1: {
                    transportTypes[i] = TOPIC;
                    break;
                }
                case 2: {
                    transportTypes[i] = TROLLEY;
                    break;
                }
            }
        }
        return transportTypes;
    }

    private static int getTransportTypesCount(int transportType) {
        String transportTypeString = String.valueOf(transportType);
        int typeCount = 0;

        for (int i = 0; i < transportTypeString.length(); i++) {
            if (transportTypeString.charAt(i) == '1') {
                typeCount++;
            }
        }
        return typeCount;
    }
}
