package com.examples;

import java.io.*;
import java.util.*;

public class Locations3 implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

        try (BufferedWriter locationFile = new BufferedWriter(new FileWriter("location.txt"));
             BufferedWriter directionFile = new BufferedWriter(new FileWriter("direction.txt"))) {
            for (Location location : locations.values()) {
                locationFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        directionFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
                    }
                }
            }
        }

    }

    static {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("locations_big.txt"))) {
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                String[] data = input.split(",");
                int location = Integer.parseInt(data[0]);
                String description = data[1];
                System.out.println("Imported " + location + ":" + description);
                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(location, new Location(location, description, tempExit));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Now reading Exits
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("directions_big.txt"))) {
            String input;
            while ((input = bufferedReader.readLine()) != null) {

                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                String dest = data[2];
                int destination = Integer.parseInt(dest);

                System.out.println(loc + ":" + direction + ":" + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
//            long end=LocalDateTime.now().getNano();
//            System.out.println(end-start);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Map.Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}