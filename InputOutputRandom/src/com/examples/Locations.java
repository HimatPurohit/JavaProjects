package com.examples;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();

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

        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations5.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Location location = (Location) locFile.readObject();
                    System.out.println("Read location " + location.getLocationID() + " : " + location.getDescription());
                    System.out.println("Found " + location.getExits().size() + " exits");

                    locations.put(location.getLocationID(), location);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException io) {
            System.out.println("IO Exception" + io.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
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
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
