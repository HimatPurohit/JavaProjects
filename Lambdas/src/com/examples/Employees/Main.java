package com.examples.Employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Cook", 21);
        Employee jack = new Employee("Jack Daniel", 40);
        Employee snow = new Employee("Snow Queen", 34);
        Employee red = new Employee("Red Robin", 23);
        Employee charmy = new Employee("Charmy Picasta", 28);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charmy);

        // lambda Functions
        Function<Employee, String> getLastName = (employee) -> {
            return employee.getName().substring(employee.getName().indexOf(" ") + 1);
        };
        Function<Employee, String> getFirstName = (employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(" "));
        };

//        for (Employee employee:employees) {
//            System.out.println(getLastName.apply(employee));
//        }
        Random randomBoolean = new Random();
        for (Employee employee : employees) {
            if (randomBoolean.nextBoolean()) {
                System.out.println("First Name: " + getName(getFirstName, employee));
            } else {
                System.out.println("Last Name: " + getName(getLastName, employee));
            }
        }

        // Function chaining
        Function<Employee, String> upperCase = (employee) -> employee.getName().toUpperCase();
        Function<String, String> firstname = (name) -> name.substring(0, name.indexOf(" "));
        Function chainedFunction = upperCase.andThen(firstname);

        for (Employee employee : employees) {
            System.out.println(chainedFunction.apply(employee));
        }

        // Any Bi Function, Predicate, Consumer can accept two parameters instead of single parameters
        // to chain BiFunctions or BiPredicate, the BI part will be the outermost, since it requires multiple parameters
        BiFunction<String, Employee, String> concatAge = (name, employee) -> name.concat(" " + employee.getAge());
        String name = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(name, employees.get(0)));

        // UnaryOperators
        int number=5;
        UnaryOperator<Integer> unaryOperator= i->i*i*i;
        System.out.printf("%d * %d * %d = %d%n",number,number,number,unaryOperator.apply(number));
        IntUnaryOperator intUnaryOperator= i->i*i;
        System.out.printf("%d * %d = %d%n",number,number,intUnaryOperator.applyAsInt(number));
//        DoubleUnaryOperator doubleUnaryOperator=i->i*i;
//        doubleUnaryOperator.applyAsDouble();
//        LongUnaryOperator longUnaryOperator=i->i*i;
//        longUnaryOperator.applyAsLong();


        System.out.println("********************************");
        System.out.println("All Employees");
        System.out.println("********************************");
        employees.forEach(employee -> System.out.println(employee.getName() + " " + employee.getAge()));

//        System.out.println("********************************");
//        System.out.println("Employees over 30");
//        System.out.println("********************************");
////        for (Employee employee:employees){
////            if (employee.getAge()>30){
////                System.out.println(employee.getName()+" "+employee.getAge());
////            }
////        }
//
//        employees.forEach(employee -> {
//            if (employee.getAge() > 30) {
//                System.out.println(employee.getName() + " " + employee.getAge());
//            }
//        });
//        System.out.println("********************************");
//        System.out.println("Employees younger or 30");
//        System.out.println("********************************");
//
//        employees.forEach(employee -> {
//            if (employee.getAge() <= 30) {
//                System.out.println(employee.getName() + " " + employee.getAge());
//            }
//        });

        printEmployessByAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
        printEmployessByAge(employees, "Employees younger or 30", employee -> employee.getAge() <= 30);
        printEmployessByAge(employees, "Employess younger than 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });

        IntPredicate greaterThan15 = num -> num > 15;
        System.out.println(greaterThan15.test(18));
        System.out.println(greaterThan15.test(14));

        IntPredicate lessThan100 = num -> num < 100;
        System.out.println(greaterThan15.and(lessThan100).test(30));
        System.out.println(greaterThan15.and(lessThan100).test(101));

        System.out.println(greaterThan15.or(lessThan100).test(101));

        Random random = new Random();
        System.out.println("********************************");
        System.out.println("using common method");
        System.out.println("********************************");
        for (int i = 0; i < 10; ++i) {
            System.out.println(random.nextInt(1000));
        }

        // using Supplier
        System.out.println("********************************");
        System.out.println("using Supplier");
        System.out.println("********************************");
        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
        for (int i = 0; i < 10; ++i) {
            System.out.println(randomSupplier.get());
        }
    }

    private static void printEmployessByAge(List<Employee> employees, String ageText, Predicate<Employee> agecondition) {
        System.out.println("********************************");
        System.out.println(ageText);
        System.out.println("********************************");

        for (Employee employee : employees) {
            if (agecondition.test(employee)) {
                System.out.println(employee.getName() + " " + employee.getAge());
            }
        }
    }

    private static String getName(Function<Employee, String> function, Employee employee) {
        return function.apply(employee);
    }
}
