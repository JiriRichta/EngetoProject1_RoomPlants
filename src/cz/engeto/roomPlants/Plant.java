package cz.engeto.roomPlants;

import java.time.LocalDate;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;    //počet dnů do další zálivky

    //region constructors
    public Plant(String name, String notes, int frequencyOfWatering, LocalDate watering, LocalDate planted ) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setWatering(watering);
        setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name, int frequencyOfWatering) throws PlantException{
        this.name = name;
        this.notes = "";
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name) throws PlantException {
        this(name,7);
    }
    //endregion constructors

    //getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) throws PlantException{
        if (planted.isAfter(watering)) {throw  new PlantException("Zadání data zasazení rostliny nesmí být starší než datum poslední zálivky ");

        }
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }
        //ještě bych měl ohlídat že žádný ze zadaných datumů není z budoucnosti
    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)){throw new PlantException("Zadání data poslední zálivky nesmí být starší než datum zasazení rostliny");}
            this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

        //ještě bych měl ohlídat že žádný ze zadaných datumů není z budoucnosti
    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if(frequencyOfWatering<=0){
            throw new PlantException("Zadaná frekvecne zálivky musí být větší něž 0. zadali jste chybně hodnotu :"
                    +frequencyOfWatering);
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }
    //endregion getter and setter

    public String getWateringInfo(){
        return "Květina: "+ name + " byla naposledy zalita " + watering.format(Settings.FORMAT_CZ_DATE)
                + ", doporučená další zálivka je "+watering.plusDays(frequencyOfWatering).format(Settings.FORMAT_CZ_DATE);
    }

    public void doWateringNow(){
        this.watering = LocalDate.now();
    }

    @Override
    public String toString(){return "["+getName()+", poslední zálivka "+getWatering()+", frekvence zalévání "+getFrequencyOfWatering()+"] ";}
}

