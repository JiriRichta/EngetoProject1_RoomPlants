import cz.engeto.roomPlants.*;
import cz.engeto.roomPlants.PlantManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws PlantException {

        PlantManager plantManager = new PlantManager();

        //načtení souboru
        try {
            plantManager.loadContentFromFile("resources/kvetiny.txt");
        }catch (PlantException e){
            System.err.println(e.getMessage()); plantManager.clearAll();
        }
        if(plantManager.getSizeOfPlantsList()!=0) {

            for (Plant plant : plantManager.getCopyOfPlantsList()) {
                System.out.println(plant.getWateringInfo());
            }
        }else {System.err.println("!!!Seznam je prázdný!!!");}

        //přidání nové rostliny
        plantManager.addPlant(new Plant("Rozchodník","suchomilná",93, LocalDate.of(2025,12,9),LocalDate.of(2025,12,1)));

        //přidání tulipánů
        for(int i=1;i<11;i++){
            plantManager.addPlant(new Plant("Tulipán na prodej ("+i+")",14));
        }

        //odebrání květiny na 3.pozici
        plantManager.removePlant(2);

        //plantManager.saveContentToFile("resources/kvetiny);
    }

}