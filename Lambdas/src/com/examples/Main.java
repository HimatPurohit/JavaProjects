package com.examples;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // single line body
        new Thread(() -> System.out.println("Runnable Thread using lambda expression")).start();
        // multi line body
        new Thread(() -> {
            System.out.println("Runnable Thread using lambda expression multiline statements");
            System.out.println("line 2");
            System.out.printf("line %d\n", 3);
        }).start();


        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim", 21);
        Employee jack = new Employee("Jack", 40);
        Employee snow = new Employee("Snow", 23);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

//        Collections.sort(employees, new Comparator<Employee>() {
//            @Override
//            public int compare(Employee employee1, Employee employee2) {
//                return employee1.getName().compareTo(employee2.getName());
//            }
//        });

//        Collections.sort(employees, (Employee employee1,Employee employee2) -> employee1.getName().compareTo(employee2.getName()));
        Collections.sort(employees, (employee1, employee2) -> employee1.getName().compareTo(employee2.getName()));

//        for (Employee employee : employees) {
//            System.out.println(employee.getName());
////            new Thread(()-> System.out.println(employee.getAge())).start();
//            System.out.println(employee.getAge());
//        }

        employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });


//        String sillyString=doStringStuff(new Upperconcat() {
//            @Override
//            public String upperAndConcat(String s1, String s2) {
//                return s1.toUpperCase()+s2.toUpperCase();
//            }
//        },employees.get(0).getName(),employees.get(1).getName());


//        String sillyString = doStringStuff((s1, s2) -> s1.toUpperCase() + s2.toUpperCase(),
//                employees.get(0).getName(), employees.get(1).getName());

//        UpperConcat uc = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase();
        // Lambda expression with return statement 1
//        UpperConcat uc = (s1, s2) -> {
//            return s1.toUpperCase() + s2.toUpperCase();
//        };

        // Lambda expression with return statement 2
        UpperConcat uc = (s1, s2) -> {
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };
        String sillyString = doStringStuff(uc, employees.get(0).getName(), employees.get(1).getName());


        System.out.println("SillyString: " + sillyString);


        AnotherClass anotherClass = new AnotherClass();
        String str1 = anotherClass.doSomething();
        System.out.println(str1);
        String str2 = anotherClass.doSomething2();
        System.out.println(str2);

        anotherClass.printNumber();


    }

    public final static String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }

}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}

class AnotherClass {
    public String doSomething() {
        System.out.println("The class Name is: " + getClass().getSimpleName());
//        return Main.doStringStuff(new UpperConcat() {
//            @Override
//            public String upperAndConcat(String s1, String s2) {
//                System.out.println("The anonymous class Name is: "+getClass().getSimpleName());
//                return s1.toUpperCase() + s2.toUpperCase();
//            }
//        }, "String1", "String2");

        UpperConcat uc = (s1, s2) -> {
            System.out.println("The lambda class Name is: " + getClass().getSimpleName());
            return s1.toUpperCase() + s2.toUpperCase();
        };
        return Main.doStringStuff(uc, "String1", "String2");

    }

    public String doSomething2() {
        System.out.println("The class Name is: " + getClass().getSimpleName());
        return Main.doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                System.out.println("The anonymous class Name is: " + getClass().getSimpleName());
                return s1.toUpperCase() + s2.toUpperCase();
            }
        }, "String1", "String2");
    }

    public void printNumber() {
        // value of num is effectively final since it is not updated after assignment
        int num = 25;

        Runnable runnable = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
            System.out.println("The number is "+num);
        };

        new Thread(runnable).start();
    }
}