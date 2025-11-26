package com.app.soap;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        String home = System.getProperty("user.home");

        Path configPath = Paths.get(home, "volandouyFiles", "app.properties");
        try (InputStream is = Files.newInputStream(configPath)) {
            props.load(is);
        } catch (NoSuchFileException e) {
            throw new RuntimeException("No se encontró el archivo de configuración: " + configPath, e);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando configuración desde: " + configPath, e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
