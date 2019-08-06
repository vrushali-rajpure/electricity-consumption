package com.main;

import com.models.FloorResourceMonitor;
import com.models.Movement;
import com.models.structures.Floors;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Number of floorsCount::");
        final int floorsCount = in.nextInt();
        System.out.println("Main corridors per floor::");
        final int mainCorridorsCount = in.nextInt();
        System.out.println("Sub corridors per floor::");
        final int subCorridorCount = in.nextInt();

        final Floors floors = Floors.createFloors(floorsCount, mainCorridorsCount, subCorridorCount);

        System.out.println("Default State");
        System.out.println(floors.getStatus());

        while (true) {
            System.out.println("***Want to add movement (y/n)::");
            final String movementAns = in.next();
            if (movementAns.toLowerCase().equals("N".toLowerCase())) {
                System.exit(0);
            }

            System.out.println("Movement going on (y/n) ?");
            final boolean isMovement = isMovement(in.next());
            System.out.println("Movement in Floor Number::");
            final int floorNumber = in.nextInt();
            System.out.println("Movement in sub corridor Number::");
            final int subCorridorNumber = in.nextInt();

            Movement movement = Movement.getMovement(isMovement, floorNumber, subCorridorNumber);
            FloorResourceMonitor monitor = new FloorResourceMonitor(floors);
            monitor.applyMovement(movement);

            System.out.println("--------------------START : updated state----------------");
            System.out.println(floors.getStatus());
            System.out.println("--------------------END : updated state----------------");
        }
    }

    private static boolean isMovement(String input) {
        return input.toLowerCase().equals("y");
    }

}
