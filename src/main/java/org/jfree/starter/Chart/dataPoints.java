package org.jfree.starter.Chart;

import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class dataPoints {
    private String name = "";
    private dataPoint[] arr;
    private int length;
    private String date = "";

    //constructor for an array of data points
    public dataPoints (String name, int size) {
        this.name = name;
        arr = new dataPoint[size];
        length = size;
    }

    //methods
    //add
    public void addElement(double number, String date) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                arr[i] =  new dataPoint(date, number);
            }
        }
    }

    public dataPoint getElement(int index) { return arr[index]; }

    public dataPoint getElementByDate(String date) { return null; }

    public void setElement(int index, double value) {}

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public dataPoint[] getArray() {
        return arr;
    }

    public void printArray() {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].getDate() + ": " + arr[i].getPoint());
        }
    }


    public String dataToString() {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str += arr[i].getPoint() +" "+ arr[i].getDate() + "\n";
            System.out.println(arr[i].getPoint() + " " + arr[i].getDate());
        }

        return str;
    }














}
