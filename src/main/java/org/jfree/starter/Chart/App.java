package org.jfree.starter.Chart;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * TO DO
 * 1. Adding new data points just repeats the same one
 * 2. change the name of dataPoints and dataPoint
 * 3. Add exceptions and enable methods to request you try again if an input is not correct
 * 4. Allow multiple data sets to be loaded
 * 5. Need to change dataPoints to a linkedlist for better flexibility
 * 6. Test.
 */

public class App {

    /**
     * Method to take user input for dataPoints array.
     * Userinputs: name, length.
     */
    public static dataPoints createDataSet() {
        Scanner scanner = new Scanner(System.in);
        String name ="";
        String date = "";
        int length = 0;
        boolean valid = false;

        System.out.println("Enter name of data elements: ");
        name = scanner.next();
        scanner.nextLine();

        System.out.println("Enter number of data points: ");
        do {
            try {
                length = scanner.nextInt();
                scanner.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.nextLine();
            }
        }   while (!valid);


        dataPoints array = new dataPoints(name, length);
        return array;
    }

    /**
     * Method to add data points to existing array
     * no return needed.
     * Need to make the array accesible but immutable
     */
    public static void addDataPoints(dataPoints array) {
        try {
            Scanner scanner = new Scanner(System.in);
            dataPoints list = array;
            double points = 0.0;
            String date = "";

            //need to add exception for wrong input type and try again message.
            for (int i = 0; i < array.getArray().length; ++i) {
                System.out.println("Enter data point: ");
                points = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("Enter date (DD/MM/YYYY): ");
                date = scanner.next();

                array.addElement(points, date);
            }
        }
        catch (NullPointerException e) {
            System.out.println("Data set has not been initialized or loaded.");
        }
    }

    //to view data set
    public static void printDataPoints (dataPoints list) {
        try {
            list.printArray();
        }
        catch (NullPointerException e) {
            System.out.println("A data set does not exist to save.");
        }
    }

    public static void SaveObjectArrayStart(dataPoints list) {
        SaveLoadDataSets sl = new SaveLoadDataSets(list);
        sl.SaveArray();
    }

    public static dataPoints loadDataSet() {
        SaveLoadDataSets sl = new SaveLoadDataSets();
        return sl.ReadFile();
    }

    public static void loadSets() {
        SaveLoadDataSets ls =  new SaveLoadDataSets();
        ls.viewSavedDataSets();
    }

//test

    public static void main(String[] args) {

        dataPoints[] list = new dataPoints[10];

        //create menu to select
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Create Data Set");
            System.out.println("2. View Data Set");
            System.out.println("3. Add to Data Set");
            System.out.println("4. Create Graph");
            System.out.println("5. Save Data Set");
            System.out.println("6. Load Data Set");
            System.out.println("7. View Saved Data Sets");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    list[0] = createDataSet();

                    break;
                case 2:
                    printDataPoints(list[0]);
                    break;
                case 3:
                    addDataPoints(list[0]);
                    break;
                case 4:
                    new createGraphWindow(list[0]);
                    break;
                case 5:
                    SaveObjectArrayStart(list[0]);
                    break;
                case 6:
                    list[0] = loadDataSet();
                case 7:
                    loadSets();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);


    }
    //test
    }
