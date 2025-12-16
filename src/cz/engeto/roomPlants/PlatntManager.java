package cz.engeto.roomPlants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlatntManager{

    private List<Plant>plants;
    private String listNote;

    //region construktor
    public PlatntManager(List<Plant> plants, String listNote) {
        this.plants = new ArrayList<>(plants);
        this.listNote = listNote;
    }
        //kdybych potřeboval vytvořit seznam květin s nějakým konkrétním popisem
    public PlatntManager(List<Plant> plantManager) {
        this.plants = new ArrayList<>(plants);
        this.listNote = "no comment";
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
    public List<Plant> getNewPlants() {
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

    public String getListNote() {
        return "poznámka: "+ listNote;
    }

    public void setListNote(String listNote) {
        this.listNote = listNote;
    }
    //end region methods

}
