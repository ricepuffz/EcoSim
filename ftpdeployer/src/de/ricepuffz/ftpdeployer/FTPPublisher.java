package de.ricepuffz.ftpdeployer;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.Map;

public class FTPPublisher {
    private FTPPublisher() { }


    public static boolean publish() throws Exception {
        removeReloadFromIndexHTML();

        System.out.println("Reading config FTPPublish.config for FTP server data...");

        Map<String, String> configData = ConfigReader.readFile("FTPPublish.config");
        String serverHostname = configData.get("hostname");
        String username = configData.get("username");
        String password = configData.get("password");

        System.out.println("Done!");
        System.out.println("Connecting to FTP server " + serverHostname + "...");

        FTPClient client = new FTPClient();
        client.connect(serverHostname);

        if (client.login(username, password))
            System.out.println("Logged in as " + username + "!");
        else {
            System.err.println("Failed to log in to FTP server as " + username + "!");
            return false;
        }

        client.setFileType(FTP.BINARY_FILE_TYPE);

        System.out.println("Publishing HTML Project per FTP...");
        System.out.println("Removing files from FTP root...");

        removeDirectory(".", client);

        System.out.println("Removed files from FTP root");

        System.out.println("Uploading dist folder from html project to FTP root...");
        String distPath = "..\\html\\build\\dist";
        File file = new File(distPath);
        if (file.exists()) {
            uploadDirectory("", client);
        } else {
            System.err.println("Dist folder of html project does not exist! Did the build process fail?");
            client.quit();
            return false;
        }

        System.out.println("Uploaded dist folder to FTP root");
        System.out.println("Done publishing HTML project!");

        client.quit();
        return true;
    }

    public static void removeDirectory(String path, FTPClient client) throws Exception {
        FTPFile[] files = client.listFiles(path);
        for (FTPFile file : files) {
            if (file.isDirectory()) {
                removeDirectory(path + "/" + file.getName(), client);
                client.removeDirectory(path + "/" + file.getName());
            } else
                client.deleteFile(path + "/" + file.getName());
        }
    }

    public static void uploadDirectory(String path, FTPClient client) throws Exception {
        String distPath = "..\\html\\build\\dist";

        File[] files = new File(distPath + "\\" + path).listFiles();

        for (File file : files) {
            if (file != null && file.exists()) {
                if (file.isDirectory()) {
                    System.out.println("Uploading " + client.printWorkingDirectory().substring(1) + file.getName() + "/");
                    client.makeDirectory(file.getName());
                    client.changeWorkingDirectory(file.getName());
                    uploadDirectory(path + (path.equals("") ? "" : "\\") + file.getName(), client);
                    client.changeWorkingDirectory("..");
                } else {
                    System.out.println("Uploading " + path.replace('\\', '/') + file.getName());
                    client.storeFile(file.getName(), new FileInputStream(new File(distPath + "\\" + path + "\\" + "\\" + file.getName())));
                }
            } else
                System.err.println("Omitted");
        }
    }

    public static void removeReloadFromIndexHTML() throws IOException {
        System.out.println("Removing reload button from index.html if present...");

        String path = "..\\html\\build\\dist\\";

        File inputFile = new File(path + "index.html");
        File tempFile = new File(path + "temp");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = "<a class=\"superdev\"";
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.startsWith(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();

        String fileName = inputFile.getName();
        inputFile.delete();
        boolean successful = tempFile.renameTo(inputFile);

        System.out.println(successful ? "Done!" : "Failed");
    }



    public static void main(String[] args) {
        try {
            publish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
