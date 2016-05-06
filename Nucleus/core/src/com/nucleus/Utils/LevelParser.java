package com.nucleus.Utils;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

// A class for reading and parsing .txt files that will be levels.
public class LevelParser {


    //private static int[] intList;   //obsolete?
    public static com.nucleus.Utils.LevelData levelParse(int level) throws LevelNotExistingException {
        return splitLevelString((readFromFile(level)));
    }

    // method to read from a file and return a single String.
    public static String readFromFile(int level) throws LevelNotExistingException {
        String levelString = "";
        try {
            Scanner sc;
            File file = new File("level_" + Integer.toString(level) + ".txt");    //temporary as we have no way to get a file
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String lineInput = sc.nextLine();
                levelString = levelString + lineInput + "\n";
            }
            sc.close();
        } catch (FileNotFoundException e) {
        throw new LevelNotExistingException("level does not exist");
        }
        return levelString;
    }

    //split a string where there is a new line
    public static com.nucleus.Utils.LevelData splitLevelString (String str) {
        String temp = str.replaceAll(" ", "\n");
        String[] strings = temp.split("\n");
        int[] levelField = new int[strings.length];
        for (int i = 0; i < levelField.length; i++)
        {
            levelField[i] = Integer.parseInt(strings[i]);
        }
        com.nucleus.Utils.LevelData levelData = new com.nucleus.Utils.LevelData(levelField);
        return levelData;
    }

}