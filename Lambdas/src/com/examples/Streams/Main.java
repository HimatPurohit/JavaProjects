package com.examples.Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> someBingoNumbers = Arrays.asList(
                "A5", "A14",
                "G43", "G27", "G65", "g56",
                "K9", "K26",
                "M7", "M27",
                "O11", "O37");

        // this is effectively final since the initialization is not changed
        List<String> gNumbers = new ArrayList<>();

//        Consumer<String> consumer=(bingoNumber)->{
//            if (bingoNumber.toUpperCase().startsWith("G")){
//                gNumbers.add(bingoNumber);
////                System.out.println(bingoNumber);
//            }
//        };
//
//        someBingoNumbers.forEach(consumer);
//
//        gNumbers.sort((number1,number2)->number1.toUpperCase().compareTo(number2.toUpperCase()));
//        gNumbers.forEach((bingoNumber)-> System.out.println(bingoNumber));


        // using streams
        System.out.println("----------.stream method------------");
        someBingoNumbers.stream()
//                .map(s->s.toUpperCase())
                .map(String::toUpperCase)
                .filter(bingoNumber -> bingoNumber.startsWith("G"))
                .sorted()
                .forEach(System.out::println);


        Stream<String> ioNumberStream = Stream.of("I0", "I3", "I7", "O6", "O8", "I9");
        Stream<String> inNumberStream = Stream.of("I1", "I4", "N2", "N3", "I8", "I9");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
        System.out.println("-----------Stream<T>----------------");
//        System.out.println(concatStream.count());
//        System.out.println(concatStream.distinct().count());
        System.out.println(concatStream.distinct().peek(System.out::println).count());

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Cook", 28);
        Employee jack = new Employee("Jack Daniel", 34);
        Employee snow = new Employee("Snow Queen", 34);
        Employee red = new Employee("Red Robin", 23);
        Employee charmy = new Employee("Charmy Picasta", 28);

        Department hr = new Department("Human Resources");
        hr.addEmployee(john);
        hr.addEmployee(tim);
        hr.addEmployee(jack);

        Department accounting = new Department("Accounting");
        accounting.addEmployee(snow);
        accounting.addEmployee(red);
        accounting.addEmployee(charmy);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        System.out.println("-----------.flatmap-----------------");
        // flatMap returns a stream that can be used by further methods working on the same
        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

        System.out.println("--------.collect--------------------");
        // collect method: used to work on collected data
        List<String> sortedGNumbers = someBingoNumbers.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                // returns a List
//                .collect(Collectors.toList());
                // collect(Supplier,Accumulator,Combiner)
                //collect(Supplier,BiConsumer,BiConsumer)
                // return an ArrayList
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        for (String bingoGNumber : sortedGNumbers) {
            System.out.println(bingoGNumber);
        }

        System.out.println("--------Collectors.groupingBy-----------------");
        Map<Integer, List<Employee>> groupedByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(groupedByAge.keySet());
        for (Integer key : groupedByAge.keySet()) {
            for (Employee employee : groupedByAge.get(key)) {
                System.out.println(employee);
            }
        }

        System.out.println("--------.reduce-------------");
        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);

        System.out.println("---------------------");
        Stream.of("ABC","DEF","GHI","JKL","MNO","PQR")
                .filter(s-> {
                    System.out.println(s);
                    return s.length()==3;
                })
        .count();
    }
}
