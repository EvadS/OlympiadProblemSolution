package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1_1Test {

    private final String FILE_NAME = "src/test/resources/fileToCreate.txt";

    @AfterEach
    @BeforeEach
    public void cleanUpFiles() {
        File targetFile = new File(FILE_NAME);
        targetFile.delete();
    }


    @Test
    public void givenUsingFile_whenCreatingFile_thenCorrect() throws IOException {
        File newFile = new File(FILE_NAME);
        boolean success = newFile.createNewFile();
        assertTrue(success);
    }

    /**
     * We'll read the “fileTest.txt” available under src/main/resources:
     */
    @Test
    public void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() throws IOException {
        String expectedData = "Hello, world!";

        Class clazz = Task1_1Test.class;
        InputStream inputStream = clazz.getResourceAsStream("/fileTest.txt");
        String data = readFromInputStream(inputStream);

        //(data, containsString(expectedData));
    }

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br= new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

}