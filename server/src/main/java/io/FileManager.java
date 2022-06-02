package io;

import logging.Logger;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileManager {
    public static String read(String path) {
        try {
            InputStream fileStream = FileManager.class.getClassLoader().getResourceAsStream(path);

            if (fileStream == null) {
                Logger.warn("Could not find resource: '" + path + "'");
                return "";
            }

            InputStreamReader streamReader = new InputStreamReader(fileStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);

            StringBuilder content = new StringBuilder();

            for (String line; (line = reader.readLine()) != null;) {
                content.append(line);
                content.append("\n");
            }

            return content.toString();
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