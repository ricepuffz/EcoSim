package de.ricepuffz.ftpdeployer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {
    private ConfigReader() { }


    public static Map<String, String> readFile(String path) {
        Map<String, String> result = new HashMap<>();
        File file = new File(path);
        System.out.println(file.getAbsolutePath());

        if (!file.exists()) {
            System.err.println("Tried to read config file \"" + path + "\", which doesn't exist!");
            return null;
        }

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String readLine = reader.readLine();

             while (readLine != null) {
                String[] split = readLine.split("=");

                result.put(split[0], split[1]);

                readLine = reader.readLine();
             }

             reader.close();
        } catch (Exception e) {
            System.err.println("Error while trying to read config file \"" + path + "\"");
            e.printStackTrace();
        }

        return result;
    }
}
