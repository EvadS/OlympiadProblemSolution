package org.example.grop1;

import org.example.FileHelper;

import java.io.*;
import java.util.Objects;

public class Task1_1 {

    private static final String FILE_NAME = "test_file_name.txt";
    private static final String FOLDER_NAME = "test_folder_name";


    public static void main(String[] args) throws IOException {
        FileHelper.createFileInResourceFolder(FOLDER_NAME, FILE_NAME);
        readFromFile(FOLDER_NAME,FILE_NAME);
    }

    private static void readFromFile(String subFolder, String fileName) {
        InputStream inputStream = null;
        try {

            String fileNamePath = String.format("%s/%s", subFolder, fileName);
            //"sub-folder-name/fileToCreate2222.txt";
            String filePath = Objects.requireNonNull(Task1_1.class.getClassLoader().getResource(fileNamePath)).getPath();

            File file = new File(filePath);
            if(!file.exists()){
                //throw
            }

            inputStream =  new FileInputStream(filePath);

            //StringBuilder resultStringBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {

                    System.out.println("line :"+ line);
                   // resultStringBuilder.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
