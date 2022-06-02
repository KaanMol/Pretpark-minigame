package io;

import logging.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

public class FileManager {
    public static String read(String path) {
        try {
            URL fileUrl = FileManager.class.getClassLoader().getResource(path);

            if (fileUrl == null) {
                Logger.warn("Could not find resource: '" + path + "'");
                return "";
            }

            File file = new File(fileUrl.toURI());

            if (!file.exists()) {
                Logger.warn("Could not find file: '" + file.getAbsolutePath() + "'");
                return "";
            }

            Scanner scanner = new Scanner(new FileReader(file));
            StringBuilder source = new StringBuilder();

            while (scanner.hasNextLine()) {
                source.append(scanner.nextLine());
                source.append("\n");
            }

            return source.toString();
        } catch (Exception e) {
            Logger.warn(e, "Could not read file: '" + path + "'");
            return "";
        }
    }

    public static void write(String path, String contents) {
        try (PrintWriter writer = new PrintWriter(path)) {
            writer.print(contents);
        } catch (Exception e) {
            Logger.warn(e, "Could not write to file: '" + path + "'");
        }
    }

    public static InputStream getResource(String file) {
        InputStream stream = FileManager.class.getClassLoader().getResourceAsStream(file);

        if (stream == null) {
            Logger.warn("Could not find resource file: '" + file + "'");
        }

        return stream;
    }
}