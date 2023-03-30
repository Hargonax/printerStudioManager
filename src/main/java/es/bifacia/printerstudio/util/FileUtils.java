package es.bifacia.printerstudio.util;

import java.io.File;

public abstract class FileUtils {

    /**
     * Prints the content of a folder.
     * @param folderPath Path to folder.
     */
    public static void printFolderContent(final String folderPath) {
        final File folderFile = new File(folderPath);
        if (!folderFile.exists() || !folderFile.isDirectory()) {
            throw new RuntimeException("The path provided (" + folderPath + ") doesn't correspond to a folder.");
        }
        if (folderFile.list().length > 0) {
            System.out.println("/**** " + folderPath + " context ****/");
            for (final String file : folderFile.list()) {
                System.out.println(file);
            }
        }
    }
}
