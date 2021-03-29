package com.company.method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUltil {
    public static void createFile(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Created file successfully at: " + path);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String path, String fileContent) {
        try {
            // write file and close
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(fileContent);
            fileWriter.close();
            System.out.println("Writed file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String path) {
        String content = "";
        // using x to modify content of file while read each line
        int x = 0;

        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (x != 0) {
                    x++;
                    content += ("\n" + data);
                } else {
                    x++;
                    content += data;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
