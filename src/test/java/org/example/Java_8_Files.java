package org.example;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Java_8_Files {

    private final String FILE_NAME = "fileToCreate.txt";
    private final String myFolderName = "TASK_1_1";

    @Test
    public void givenUsingFile_whenCreatingFile_thenCorrect() throws IOException, URISyntaxException {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        // create folder if not exists
        final File dir = new File(absolutePath, myFolderName);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Unable to create " + dir.getAbsolutePath());
        }

        File file = new File(dir + "/" + FILE_NAME);

        if (!file.isFile() &&
                !file.createNewFile()) {
            throw new IOException("Error creating new file: " + file.getAbsolutePath());
        }

        String value = "232323";
        try
                (FileWriter fw = new FileWriter(file.getAbsoluteFile());
                 BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(value);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Test
    public void givenFilePath_whenUsingFilesLines_thenFileData() throws URISyntaxException, IOException {
        String expectedData = "Hello, world!";

        Path path = Paths.get(getClass().getClassLoader()
                .getResource(myFolderName+"/"+ FILE_NAME).toURI());

        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();

        assertEquals(expectedData, data.trim());
    }

}
