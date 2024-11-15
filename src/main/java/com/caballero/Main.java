package com.caballero;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.context.Context;

import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        // Cargar el archivo de configuración
        Properties config = cargarConfig("src/main/resources/config.ini");

        // Configurar el TemplateEngine de Thymeleaf
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Crear el contexto Thymeleaf
        Context context = new Context();

        // Pasar las propiedades del archivo config.ini al contexto
        context.setVariable("nombre", config.getProperty("nombre"));
        context.setVariable("descripcion", config.getProperty("descripcion"));

        // Cargar los datos de los cantantes desde el archivo JSON
        ListaDeCantantes lDc = cargaDatos("src/main/resources/cantantes.json");

        if (lDc != null) {
            // Establecer la lista de cantantes como variable para el template
            context.setVariable("cantantes", lDc.getCantantes());

            // Generar el HTML principal (por ejemplo, un índice con todos los cantantes)
            String contenidoHTML = templateEngine.process("plantillaCantantes.html", context);
            System.out.println(contenidoHTML);

            // Escribir el HTML generado en un archivo
            escribirHTML(contenidoHTML, "src/main/resources/static/index.html");

            // Generar un archivo HTML para cada cantante con sus canciones
            for (Cantante cantante : lDc.getCantantes()) {
                Context contextDetalles = new Context();
                contextDetalles.setVariable("cantante", cantante);

                // Añadir variables del archivo config.ini al contexto de detalles
                contextDetalles.setVariable("nombre", config.getProperty("nombre"));
                contextDetalles.setVariable("descripcion", config.getProperty("descripcion"));

                // Generar HTML para cada cantante
                String detallesHTML = templateEngine.process("plantillaCanciones.html", contextDetalles);
                String fileName = "src/main/resources/static/detalles_" + cantante.getNombre() + ".html";

                // Escribir cada archivo HTML de detalles
                escribirHTML(detallesHTML, fileName);
            }
        } else {
            System.out.println("Error al cargar los datos desde el archivo JSON.");
        }
    }

    public static Properties cargarConfig(String path) {
        Properties config = new Properties();
        try (InputStream input = new FileInputStream(path)) {
            config.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static ListaDeCantantes cargaDatos(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), ListaDeCantantes.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void escribirHTML(String contenido, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
