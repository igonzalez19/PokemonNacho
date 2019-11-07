package com.example.pokemon.entity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Pokemon")
public class Pokemon {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private int height;

    @ColumnInfo
    private int weight;

    @ColumnInfo
    private String type;

    @ColumnInfo
    private String ability;


    @ColumnInfo
    private String rutaImg;

    @ColumnInfo
    private String description;

    public Pokemon(String name, int height, int weight, String type, String ability, String rutaImg, String description) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.type = type;
        this.ability = ability;
        this.rutaImg = rutaImg;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", type='" + type + '\'' +
                ", habilidad='" + ability + '\'' +
                ", rutaImg='" + rutaImg + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }
}
