import cz.engeto.roomPlants.Plant;
import cz.engeto.roomPlants.PlantException;
import cz.engeto.roomPlants.PlantManager;
import cz.engeto.roomPlants.PlantManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws PlantException {

        Plant kytka1= new Plant("kytka1","popis kytka1", LocalDate.of(2025,5,1),LocalDate.of(2025,12,1),10);
        Plant kytka2= new Plant("kytka2","popis kytka1", LocalDate.of(2025,4,1),LocalDate.of(2025,12,2),5);
        Plant kytka3= new Plant("kytka3","popis kytka1", LocalDate.of(2025,8,1),LocalDate.of(2025,12,3),2);

        PlantManager novyPlantmanager = new PlantManager();

        novyPlantmanager.addPlant(new Plant("kytka4","popis kytka4", LocalDate.of(2025,11,1),LocalDate.of(2025,12,25),10));
        novyPlantmanager.addPlant(new Plant("sd"));
        novyPlantmanager.addPlant(kytka1);
        novyPlantmanager.addPlant(kytka2);
        novyPlantmanager.addPlant(kytka3);

        novyPlantmanager.getCopyOfPlantsList().forEach(x-> System.out.println(x.getWateringInfo()));
        novyPlantmanager.sort(Comparator.comparing(Plant::getWatering));
        System.out.println("-----------------");
        novyPlantmanager.getCopyOfPlantsList().forEach(x-> System.out.println(x.getWateringInfo()));


    }
}