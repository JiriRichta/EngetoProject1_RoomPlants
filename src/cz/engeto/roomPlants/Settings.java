package cz.engeto.roomPlants;

import java.time.format.DateTimeFormatter;

public class Settings {

    public static final DateTimeFormatter FORMAT_CZ_DATE = DateTimeFormatter.ofPattern("d.M.yyyy"); //nastavení formatu datumu
    private final static String DELIMITER = ";";
    private static final int NUMBER_OF_PARTS_ON_LINE = 5;

    public static String getDELIMITER() {
        return DELIMITER;
    }

    public static int getNUMBER_OF_PARTS_ON_LINE() {
        return NUMBER_OF_PARTS_ON_LINE;
    }

}

