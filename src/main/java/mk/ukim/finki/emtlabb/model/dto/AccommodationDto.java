package mk.ukim.finki.emtlabb.model.dto;

import lombok.Data;
import mk.ukim.finki.emtlabb.model.enumerations.Category;

@Data
public class AccommodationDto {
    private String name;
    private Category category;
    private Long hostId;
    private Integer numRooms;
    private boolean isRented;

    public AccommodationDto() {
    }

    public AccommodationDto(String name, Category category, Long hostId, Integer numRooms, boolean isRented) {
        this.name = name;
        this.category = category;
        this.hostId = hostId;
        this.numRooms = numRooms;
        this.isRented=isRented;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }
}
