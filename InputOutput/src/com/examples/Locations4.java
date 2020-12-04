package com.examples;

import java.io.*;
import java.util.*;

public class Locations4 implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

        try (DataOutputStream locationFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for (Location location : locations.values()) {
                locationFile.writeInt(location.getLocationID());
                locationFile.writeUTF(location.getDescription());
                System.out.println(location.getLocationID() + " " + location.getDescription() + " " + (location.getExits().size() - 1));
                locationFile.writeInt(location.getExits().size() - 1);
                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        locationFile.writeUTF(direction);
                        locationFile.writeInt(location.getExits().get(direction));
                    }
                }
            }
        }

    }

    static {
        try (DataInputStream locationFile = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Map<String, Integer> exits = new LinkedHashMap<>();
                    int locId = locationFile.readInt();
                    String description = locationFile.readUTF();
                    int numExits = locationFile.readInt();
                    for (int i = 0; i < numExits; i++) {
                        String direction = locationFile.readUTF();
                        int destination = locationFile.readInt();
                        exits.put(direction, destination);
                    }
                    locations.put(locId, new Location(locId, description, exits));
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException e) {
            System.out.println("File Reading Finished and error is caught");
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