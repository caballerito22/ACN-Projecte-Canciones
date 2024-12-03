package com.caballero;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.context.Context;

import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            // Vamos a validar el JSON va?
            JsonNode archivoJSON = JsonLoader.fromFile(new File("src/main/resources/cantantes.json"));
            JsonNode archivoSchema = JsonLoader.fromFile(new File("src/main/resources/schema.json"));

            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema jsonSchema = factory.getJsonSchema(archivoSchema);
            ProcessingReport report = jsonSchema.validate(archivoJSON);
            if (report.isSuccess()) {
                System.out.println("El JSON está bien.");
            } else {
                System.out.println("No va:");
            }

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

                String rutaRSS = "src/main/resources/static/rss.xml";
                generamosRss(lDc, rutaRSS, config.getProperty("nombre"), config.getProperty("descripcion"));
            } else {
                System.out.println("Error al cargar los datos desde el archivo JSON.");
            }

        } catch (ProcessingException | IOException e) {
            System.out.println("Error al validar el schema.");
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

    public static void generamosRss(ListaDeCantantes listaCan, String rutaRss, String nombre, String descripcion) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaRss))) {
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            bw.write("<rss version=\"2.0\">\n");
            bw.write("<channel>\n");
            bw.write("<title>" + nombre + "</title>\n");
            bw.write("<link>src/main/resources/static/index.html</link>\n");
            bw.write("<description>" + descripcion + "</description>\n");

            for (Cantante cantante : listaCan.getCantantes()) {
                for (Cancion cancion : cantante.getCanciones()) {
                    bw.write("<item>\n");
                    bw.write("<title>" + cancion.getTitulo() + "</title>\n");
                    bw.write("<link>src/main/resources/static/detalles_" + cantante.getNombre() + ".html</link>\n");
                    bw.write("<description>\n");
                    bw.write("Artista: " + cantante.getNombre() + "\n");
                    bw.write("Año: " + cancion.getAñoLanzamiento() + "\n");
                    bw.write("Reproducciones: " + cancion.getReproducciones() + "\n");
                    bw.write("</description>\n");
                    bw.write("</item>\n");
                }
            }

            bw.write("</channel>\n");
            bw.write("</rss>\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
