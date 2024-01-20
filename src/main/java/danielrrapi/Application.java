package danielrrapi;

import com.github.javafaker.Faker;
import danielrrapi.model.Libro;
import danielrrapi.model.Periodicita;
import danielrrapi.model.Rivista;
import danielrrapi.model.abstracts.Articolo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.io.File;

public class Application {

    public static void main(String[] args) {
        Supplier<Libro> libroSupplier = () -> {
            Faker faker = new Faker(Locale.ITALY);
            int isbn = faker.random().nextInt(1, 500000);
            String isbnString = Integer.toString(isbn);
            String titolo = faker.book().title();
            int anno = faker.random().nextInt(1900, 2023);
            int numeroPagine = faker.random().nextInt(10, 700);
            String autore = faker.book().author();
            String genere = faker.book().genre();
            return new Libro(isbnString, titolo, anno, numeroPagine, autore, genere);
        };
        Supplier<Rivista> rivistaSupplier = () -> {
            Faker faker = new Faker(Locale.ITALY);
            int isbn = faker.random().nextInt(1, 500000);
            String isbnString = Integer.toString(isbn);
            String titolo = faker.book().title();
            int anno = faker.random().nextInt(1900, 2023);
            int numeroPagine = faker.random().nextInt(10, 700);
            return new Rivista(isbnString, titolo, anno, numeroPagine, Periodicita.SETTIMANALE);
        };
        Map<String, Articolo> archivio = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Rivista rivista = rivistaSupplier.get();
            Libro libro = libroSupplier.get();
            archivio.put(rivista.getCodiceIsbn(), rivista);
            archivio.put(libro.getCodiceIsbn(), libro);
        }
        System.out.println("---------------------------ARCHIVIO----------------------");
        archivio.forEach((key, value) -> System.out.println(key +  ", " + value));

        System.out.println("---------------------------RICERCA PER ISBN-----------------------------");
        Articolo articoloCorrispondente = archivio.get("xyz");

        System.out.println("---------------------------RICERCA PER ANNO DI PUBBLICAZIONE----------------------");
        Map<String, Articolo> articoloFiltratoPerAnno = archivio.entrySet().stream().filter(articolo -> articolo.getValue().getAnnoPubblicazione() == 2020).collect(Collectors.toMap(k -> k.getValue().getCodiceIsbn(), k -> k.getValue()));
        articoloFiltratoPerAnno.forEach((key, value) -> System.out.println(key +  ", " + value));

        System.out.println("---------------------------RICERCA PER AUTORE----------------------");
        Map<String, Articolo> articoliFiltratiPerAutore = archivio.entrySet().stream().filter(articolo -> articolo instanceof Libro ).filter(articolo -> ((Libro) articolo).getAutore() == "Pippo").collect(Collectors.toMap(k -> k.getValue().getCodiceIsbn(), k -> k.getValue()));

        System.out.println("---------------------------SCRITTURA SU FILE----------------------");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("archivio.txt"))) {
            for (Map.Entry<String, Articolo> entry : archivio.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Dati scritti correttamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------LETTURA DA FILE----------------------");
    }
}
