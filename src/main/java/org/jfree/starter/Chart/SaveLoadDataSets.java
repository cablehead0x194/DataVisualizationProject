package org.jfree.starter.Chart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class SaveLoadDataSets {
    dataPoints dataSet;

    public SaveLoadDataSets() {
    }

    public SaveLoadDataSets(dataPoints dataSet) {
        this.dataSet = dataSet;
    }


    public void SaveArray() {

        String fileName = "";

        Scanner scanner = new Scanner(System.in);
        System.out.println("New file name: ");
        fileName = scanner.next();
        //create file to save data points to
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
                return;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //write name and then data points to txt file.
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(dataSet.getName() + "\n");
            myWriter.write(dataSet.getLength() + "\n");
            //create toString for dataPoints
            myWriter.write(dataSet.dataToString());

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            System.out.println("Data set has not been initialized or loaded.");
        }
    }

    //does this work?
    public dataPoints ReadFile() {
        try {
            Scanner scanner = new Scanner(System.in);

        String fileName = "";


        System.out.println("Please enter the name of the data set file: ");
        fileName = scanner.next();

        File myObj = new File("data/" + fileName);

        // create new dataPoints set and add information from scanner into new dataset.
        Scanner myReader = new Scanner(myObj);
            String name = "";
            int length = 0;

            name = myReader.nextLine();
            length = myReader.nextInt();

            dataPoints array = new dataPoints(name, length);

            while (myReader.hasNextLine()) {
                double data = myReader.nextDouble();
                String date = myReader.nextLine();
                array.addElement(data, date);
                //System.out.println(data);
            }
            return array;
        }
        catch (FileNotFoundException e) {
            System.out.println("File does not exist or is not readable.");

        }

        return null;
    }

    public void viewSavedDataSets() {
        String dir = "data";

        File directory = new File(dir);

        File[] files = directory.listFiles();

        if (files != null) {
            for (File fl : files) System.out.println(fl.getName());
        }
    }



}
