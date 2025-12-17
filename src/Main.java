import cz.engeto.roomPlants.*;
import cz.engeto.roomPlants.PlantManager;
import java.time.LocalDate;
import java.util.Comparator;


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

        try{
            plantManager.saveContentToFile("resources/kvetiny_NOVE.txt");
                //Za jiných okolností bych zajistil ochranu proti přepsání souboru - například vytvořením unikátního názvu s časovou známkou nebo lépe
                // bych to řešil kontrolou v PlantManageru, zrušením exportu nebo výzvou uživteli k zadání nového názvu
            } catch (PlantException e) {
                System.err.println(e.getMessage());
        }

        //znovunačtení souboru
        try {
            plantManager.loadContentFromFile("resources/kvetiny_NOVE.txt");
        }catch (PlantException e){
            System.err.println(e.getMessage()); plantManager.clearAll();
        }
        if(plantManager.getSizeOfPlantsList()!=0) {

            for (Plant plant : plantManager.getCopyOfPlantsList()) {
                System.out.println(plant.getWateringInfo());
            }
        }else {System.err.println("!!!Seznam je prázdný!!!");}

        //řazení podle jména - defaultně
        System.out.println("\n---podle jména---");
        plantManager.sort();
        for (Plant plant: plantManager.getCopyOfPlantsList()){
            System.out.println(plant.toString());
        }

        //řazení podle poslední zálivky
        System.out.println("\n---podle zálivky---");
        plantManager.sortByLastWatering();
        for (Plant plant: plantManager.getCopyOfPlantsList()){
            System.out.println(plant.toString());
        }

        //řazení podle libovlného kompárátoru - frequvence zálivky
        System.out.println("\n---podle libovlného kompárátoru - frequvence zálivky ---");
        plantManager.sort(Comparator.comparing(Plant::getFrequencyOfWatering));
        for (Plant plant: plantManager.getCopyOfPlantsList()){
            System.out.println(plant.toString());
        }

    }

}