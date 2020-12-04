package com.examples;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Location {
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
//        if(exits != null) {
//            this.exits = new HashMap<String, Integer>(exits);
//        } else {
//            this.exits = new HashMap<String, Integer>();
//        }
        if(exits != null) {
            this.exits = new LinkedHashMap<>(exits);
        } else {
            this.exits = new LinkedHashMap<>();
        }
        this.exits.put("Q", 0);
    }

//    public void addExit(String direction, int location) {
//        exits.put(direction, location);
//    }

    protected void addExit(String direction, int location) {
        exits.put(direction, location);
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

//    public Map<String, Integer> getExits() {
//        return new HashMap<String, Integer>(exits);
//    }

    public Map<String, Integer> getExits() {
        return new LinkedHashMap<>(exits);
    }
}
