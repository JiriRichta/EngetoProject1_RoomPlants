package cz.engeto.roomPlants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlantManager{

    private List<Plant>plants=new ArrayList<>();

    //region construktor
    public PlantManager() {
    }
    //endregion construktor

    //region methods
        //přidání nové květiny
    public void addPlant (Plant plant){
        plants.add(plant);
    }

        //získání květiny na zadaném indexu
    public Plant getPlant(int index){
        return plants.get(index);
    }

        //odebrání květiny ze seznamu
    public void removePlant(int index){
        plants.remove(index);
    }

        //získání kopie seznamu květin
    public List<Plant> getCopyOfPlantsList() {
            return new ArrayList<>(plants);
    }

        //metoda se seznamem rostlin k zalití
    public List<Plant> getPlantsToWatering() {
        List<Plant> plantsToWatering = new ArrayList<>();

            for (Plant plant : plants) {
                if (plant.getWatering().plusDays(plant.getFrequencyOfWatering()).isBefore(LocalDate.now())) {
                    plantsToWatering.add(plant);
                }
            }
            return plantsToWatering;
        }

        //získání velikosti seznamu
    public int getSizeOfPlantsList(){
        return plants.size();
    }

        //razení dle jsména - defaultně
    public void sort(){
        plants.sort(Comparator.comparing(Plant::getName));
    }
        //razení dle jména - přímo určená metoda
    public void sortByName(){
        plants.sort(Comparator.comparing(Plant::getName));
    }

        //razení dle poslední zálivky- přímo určená metoda
    public void sortByLastWatering(){
        plants.sort(Comparator.comparing(Plant::getWatering).reversed());
    }

        //řazení dle nastavení komparátoru v naší metodě sort
    public void sort(Comparator<Plant>comparator){
        plants.sort(comparator);
    }
    //end region methods

}
