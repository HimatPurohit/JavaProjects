package com.examples;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
    private static RandomAccessFile rFile;

    public static void main(String[] args) throws IOException {

        try (RandomAccessFile randFile = new RandomAccessFile("locations_rand.dat", "rwd")) {
            randFile.writeInt(locations.size());
            int indexSize = locations.size() * 3 * Integer.BYTES;
            int locationStart = (int) (indexSize + randFile.getFilePointer() + Integer.BYTES);
            randFile.writeInt(locationStart);
            long indexStart = randFile.getFilePointer();

            int startPointer = locationStart;
            randFile.seek(startPointer);

            for (Location location : locations.values()) {
                randFile.writeInt(location.getLocationID());
                randFile.writeUTF(location.getDescription());
                StringBuilder stringBuilder = new StringBuilder();
                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        stringBuilder.append(direction);
                        stringBuilder.append(",");
                        stringBuilder.append(location.getExits().get(direction));
                        stringBuilder.append(",");
                    }
                }

                randFile.writeUTF(stringBuilder.toString());

                IndexRecord record = new IndexRecord(startPointer, (int) (randFile.getFilePointer()));
                index.put(location.getLocationID(), record);
                startPointer = (int) randFile.getFilePointer();
            }

            randFile.seek(indexStart);
            for (Integer locationId : index.keySet()) {
                randFile.writeInt(locationId);
                randFile.writeInt(index.get(locationId).getStartByte());
                randFile.writeInt(index.get(locationId).getLength());
            }
        }

    }

    static {
        try {
            rFile = new RandomAccessFile("locations_rand.dat", "rwd");
            int numLocations = rFile.readInt();
            long locationsStartPoint = rFile.readInt();

            while (rFile.getFilePointer() < locationsStartPoint) {
                int locationId = rFile.readInt();
                int locationStart = rFile.readInt();
                int locationLength = rFile.readInt();

                IndexRecord record = new IndexRecord(locationStart, locationLength);
                index.put(locationId, record);
            }

        } catch (IOException e) {
            System.out.println("IOException in static initializer: " + e.getMessage());
        }

    }

    public Location getLocation(int locationId) throws IOException {
        IndexRecord record = index.get(locationId);
        rFile.seek(record.getStartByte());
        int id = rFile.readInt();
        String description = rFile.readUTF();
        String exits = rFile.readUTF();
        String[] exitPart = exits.split(",");

        Location location = new Location(id, description, null);
        if (locationId != 0) {
            for (int i = 0; i < exitPart.length; ++i) {
                System.out.println("exitPart " + exitPart[i]);
                System.out.println("exitPart[+1] " + exitPart[i + 1]);
                String direction = exitPart[i];
                int destination = Integer.parseInt(exitPart[++i]);
                location.addExit(direction, destination);
            }
        }
        return location;
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
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }

    public void close() throws IOException {
        rFile.close();
    }
}
