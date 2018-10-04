package com.oauth2.config;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Created by Dégi János on 2018.04.20..
 */
public class XRenameProjectImpl {

    public static String newProjectName = "TestBackend";
    public static String rename = "rename";
    public static String replacecontent = "replacecontent";


    public static void main(String[] args){

        File folder = new File("d:\\workspace_temp\\"+newProjectName);
        scanFolders(folder, rename);
        scanFolders(folder, replacecontent);

    }

    /**
     *
     * @param folder
     */
    private static void scanFolders(File folder, String operation) {
//        File folder = new File("d:\\workspace_spring_security_course\\newProjectName");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
//                System.out.println("File " + listOfFiles[i].getName());
//                System.out.println("File " + listOfFiles[i].getPath());
                if (operation.equals(rename)) {
                    renameFile(listOfFiles[i]);
                } else if (operation.equals(replacecontent)) {
                    replaceFileContent(listOfFiles[i], "SpringSecurityOauth2");
                    replaceFileContent(listOfFiles[i], "springSecurityOauth2");
                    replaceFileContent(listOfFiles[i], "springsecurityoauth2");
                }


            } else if (listOfFiles[i].isDirectory()) {
//                System.out.println("Directory " + listOfFiles[i].getName());
//                System.out.println("Directory " + listOfFiles[i].getPath());
                scanFolders(listOfFiles[i], operation);
                if (operation.equals(rename)) {
                    renameFile(listOfFiles[i]);
                }
            }
        }
    }

    public static void renameFile(File file) {
        if (file.getName().contains("SpringSecurityOauth2")) {
            String path = file.getPath().replace("SpringSecurityOauth2", newProjectName);
            System.out.println(path);
            file.renameTo(new File(path));
        }
        if (file.getName().contains("springSecurityOauth2")) {
            String path = file.getPath().replace("springSecurityOauth2", newProjectName);
            System.out.println(path);
            file.renameTo(new File(path));
        }
        if (file.getName().contains("springsecurityoauth2")) {
            String path = file.getPath().replace("springsecurityoauth2", newProjectName);
            System.out.println(path);
            file.renameTo(new File(path));
        }
    }

    /**
     *
     * @param f
     */
    public static void replaceFileContent(File f, String oldProjectName) {
        try {

            if (f.getName().contains(".jar") ||
                    f.getName().contains(".java") ||
                    f.getName().contains(".class") ||
                    f.getName().contains(".war")) {
                return;
            }

            System.out.println("________________________________________________");
            System.out.println("replaceFileContent " + f.getName());
            System.out.println("________________________________________________");

            String template = oldProjectName;
            String replaceWith = newProjectName;

            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(f.getPath()));
            String line;
            StringBuffer inputBuffer = new StringBuffer();

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            String inputStr = inputBuffer.toString();

            file.close();

//            System.out.println(inputStr); // check that it's inputted right

            inputStr = inputStr.replace(template, replaceWith);

            // check if the new input is right
//            System.out.println("----------------------------------\n"  + inputStr);

            // write the new String with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(f.getPath());
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

}
