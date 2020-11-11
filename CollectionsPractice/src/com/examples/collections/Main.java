package com.examples.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("Olympian", 8, 12);

//        List<Theatre.Seat> seatCopy = new ArrayList<>(theatre.seats);
//        printList(seatCopy);

//        seatCopy.get(1).reserve();
        if (theatre.reserveSeat("A02")) {
            System.out.println("Please Pay for A02");
        } else {
            System.out.println("Seat Already reserved");
        }
        if (theatre.reserveSeat("D12")) {
            System.out.println("Please Pay for A02");
        } else {
            System.out.println("Seat Already reserved");
        }
        if (theatre.reserveSeat("B11")) {
            System.out.println("Please Pay for A02");
        } else {
            System.out.println("Seat Already reserved");
        }

        List<Theatre.Seat> seatCopy = new ArrayList<>(theatre.getSeats());
        printList(seatCopy);

        List<Theatre.Seat> priceCopy = new ArrayList<>(theatre.getSeats());
        //Sorted based on default parameters
        Collections.sort(priceCopy);
        printList(priceCopy);
        //Sorted based on PRICE_ORDER Comparator in Theater class
        Collections.sort(priceCopy, Theatre.PRICE_ORDER);
        printList(priceCopy);

//        Collections.reverse(seatCopy);
//        printList(seatCopy);
//        printList(theatre.seats);
//
//        Collections.shuffle(seatCopy);
//        printList(seatCopy);
//        printList(theatre.seats);


//        System.out.println("Min seat: " + Collections.min(seatCopy).getSeatNumber() +
//                "\nMax seat: " + Collections.max(seatCopy).getSeatNumber());
//
//        sortList(seatCopy);
//        printList(seatCopy);

//        theatre.getSeats();
//        if(theatre.reserveSeat("H11")) {
//            System.out.println("Please pay");
//        } else {
//            System.out.println("Sorry, seat is taken");
//        }
//        if(theatre.reserveSeat("A09")) {
//            System.out.println("Please pay");
//        } else {
//            System.out.println("Sorry, seat is taken");
//        }
//        if(theatre.reserveSeat("C11")) {
//            System.out.println("Please pay");
//        } else {
//            System.out.println("Sorry, seat is taken");
//        }
//        if(theatre.reserveSeat("H11")) {
//            System.out.println("Please pay");
//        } else {
//            System.out.println("Sorry, seat is taken");
//        }
//
//        if(theatre.reserveSeat("I11")) {
//            System.out.println("Please pay");
//        } else {
//            System.out.println("Sorry, seat is taken");
//        }
//        theatre.getSeats();

    }

    public static void printList(List<Theatre.Seat> list) {
        for (Theatre.Seat seat : list) {
            if (seat.getSeatNumber().substring(1).equals("01")) {
                System.out.println();
            }
            System.out.print(" " + seat.getSeatNumber() + " $" + seat.getPrice());
        }
        System.out.println();
        System.out.println("************************************************");
    }

//    public static void sortList(List<? extends Theatre.Seat> list) {
//        for (int i = 0; i < list.size() - 1; i++) {
//            for (int j = i + 1; j < list.size(); j++) {
//                if (list.get(i).compareTo(list.get(j)) > 0) {
//                    Collections.swap(list, i, j);
//                }
//            }
//        }
//    }
}
