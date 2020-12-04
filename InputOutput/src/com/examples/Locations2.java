package com.examples;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Locations2 implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    static {
        try (Scanner scanner = new Scanner(new FileReader("locations_big.txt"))) {
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                int location = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
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
                //Using scanner.skip(scanner.delimiter())

//                int loc=scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String direction=scanner.next();
//                scanner.skip(scanner.delimiter());
//                String dest=scanner.nextLine();
//                int destination =Integer.parseInt(dest);


                //Using string.split(",")
//                String string = scanner.nextLine();
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