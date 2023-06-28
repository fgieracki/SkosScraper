package com.fgieracki;

import com.fgieracki.data.InputPerson;
import com.fgieracki.data.Person;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        DataReader dataReader = new CsvReader();
        List<InputPerson> inputPersons = new ArrayList<>();
        dataReader.readData("test.csv", inputPersons);

        List<String> urlList = new ArrayList<>();
        generateUrls(inputPersons, urlList);

        WebScraper webScraper = new WebScraper(urlList);
        List<Person> results = webScraper.scrapePages();

        System.out.println(results);

        DataWriter dataWriter = new CsvWriter();
        dataWriter.writeData("results.csv", results);
    }

    private static void generateUrls(List<InputPerson> input, List<String> output) {
        for (InputPerson person : input) {
            String url = "https://skos.agh.edu.pl/search/?nazwisko=" + person.getSurname() + "&imie=" + person.getName() + "&tytul=";
            output.add(url);
        }
    }
}