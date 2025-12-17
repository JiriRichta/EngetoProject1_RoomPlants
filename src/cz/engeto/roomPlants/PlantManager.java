package cz.engeto.roomPlants;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PlantManager{

    private List<Plant>plants=new ArrayList<>();
    private Plant plant;

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

        //načtení seznamu ze souboru
        public void loadContentFromFile(String fileName, String delimiter) throws PlantException {
            int lineNumber = 0;     // pro lokalizaci chyby

            plants.clear();

            try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
                while (scanner.hasNextLine()) {
                    lineNumber++;
                    String line = scanner.nextLine();
                    String[] parts = line.split(Settings.getDELIMITER());
                    if (parts.length != Settings.getNUMBER_OF_PARTS_ON_LINE()) {
                        throw new PlantException(
                                "Nesprávný počet položek na řádku číslo: " +lineNumber+
                                        "\n"+ line+
                                        "\n"+ "pro načtení dat ze souboru je nutné mít v souboru zadané všech 5 očekávaných položek!" +
                                        "\nOčekáváno: "+ Settings.getNUMBER_OF_PARTS_ON_LINE() +
                                        ", skutečně: " + parts.length);
                    }

                    addPlant(parseLine(parts,lineNumber,line));
                }
            } catch (FileNotFoundException e) {
                throw new PlantException(
                        "Soubor "+fileName+" nebyl nalezen!\n"+e.getLocalizedMessage());
            }
        }

    private Plant parseLine(String[] parts, int lineNumber, String line) throws PlantException {

        try {
            String name= parts[0];
            String notes= parts[1];;
            LocalDate planted= LocalDate.parse(parts[2]);;
            LocalDate watering= LocalDate.parse(parts[3]);;
            int frequencyOfWatering= Integer.parseInt(parts[4]);;

            return new Plant(name,notes,planted,watering,frequencyOfWatering);
        } catch (NumberFormatException e) {
            throw new PlantException(
                    "Chybný formát čísla na řádku číslo: " + lineNumber +
                            "\n" + line +
                            "\n" + e.getLocalizedMessage());
        } catch (DateTimeException e) {
            throw new PlantException(
                    "Chybný formát data na řádku číslo: " + lineNumber +
                            "\n" + line +
                            "\n" + e.getLocalizedMessage());
        } catch (IllegalArgumentException e) {
            throw new PlantException(
                    "Chybná kategorie na řádku číslo: " + lineNumber +
                            "\n"+ line +
                            "\n" + e.getLocalizedMessage());
        } catch (Exception e) {
            throw new PlantException(
                    "Chyba při zpracování řádku číslo: " + lineNumber +
                            "\n"+ line +
                            "\n" + e.getLocalizedMessage());
        }
    }

    public void saveContentToFile(String fileName)
            throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(
                new FileWriter(fileName)))) {
            for (Plant plant : plants) {
                String line = plant.getName() + Settings.getDELIMITER() +
                        plant.getNotes() + Settings.getDELIMITER() +
                        plant.getPlanted() + Settings.getDELIMITER()+
                        plant.getWatering() + Settings.getDELIMITER() +
                        plant.getFrequencyOfWatering() +Settings.getDELIMITER();
                writer.println(line);
            }
        } catch (IOException e) {
            throw new PlantException(
                    "Chyba při ukládání do souboru "+fileName+"!\n"+e.getLocalizedMessage());
        }
    }


    //end region methods

}
