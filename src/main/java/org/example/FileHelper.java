package org.example;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class FileHelper {

    private static Logger log = Logger.getLogger(FileHelper.class.getName());
    /**
     * crate file in sub folder in resource folder
     * @param myFolderName sub folder name
     * @param fileName file name
     * @return file absolute path
     * @throws IOException
     */
    public static String createFileInResourceFolder(String myFolderName, String fileName) throws IOException {
        Path resourceDirectory = Paths.get("src", "main", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        // create folder if not exists
        final File dir = new File(absolutePath, myFolderName);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Unable to create " + dir.getAbsolutePath());
        }

        File file = new File(dir + "/" + fileName);

        if (!file.isFile() &&
                !file.createNewFile()) {
            throw new IOException("Error creating new file: " + file.getAbsolutePath());
        }

        String fileAbsolutePath = file.getAbsolutePath();
        log.info("File created:"+fileAbsolutePath );

        return fileAbsolutePath;
    }

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
