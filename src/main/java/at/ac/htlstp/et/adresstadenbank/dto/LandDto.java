package at.ac.htlstp.et.adresstadenbank.dto;

public class LandDto {

    public int id;
    public String Name;
    public String Hauptstadt;
    public String Einwohner;

    public LandDto(int id, String name, String hauptstadt, String einwohner) {
        this.id = id;
        Name = name;
        Hauptstadt = hauptstadt;
        Einwohner = einwohner;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHauptstadt() {
        return Hauptstadt;
    }

    public void setHauptstadt(String hauptstadt) {
        Hauptstadt = hauptstadt;
    }

    public String getEinwohner() {
        return Einwohner;
    }

    public void setEinwohner(String einwohner) {
        Einwohner = einwohner;
    }

}
