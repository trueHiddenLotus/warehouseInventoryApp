package com.example.usapparelinventoryapp.models;

import java.util.List;
import java.util.Objects;

public class LocationModel{

    private int location_id;
    private String location;

    public LocationModel() {

    }

    public LocationModel(int location_id, String location) {
        this.location_id = location_id;
        this.location = location;
    }

    public LocationModel(List<Integer> line1, String line2) {
    }

    public int getLocation_id() {
        return location_id;
    }

    public int setLocation_id() {
        this.location_id = location_id;
        return location_id;
    }

    public String getLocation() {
        return location;
    }


    public CharSequence setLocation() {
        this.location = location;
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationModel)) return false;
        LocationModel that = (LocationModel) o;
        return location_id == that.location_id && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location_id, location);
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "location_id=" + location_id +
                ", location='" + location + '\'' +
                '}';
    }
}
