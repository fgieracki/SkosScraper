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
        if(args.length != 2){
            System.out.println("Wrong number of arguments");
            System.out.println("Usage: java -jar skos-scraper.jar <input_file>.csv <output_file>.csv");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];
        run(inputFileName, outputFileName);
    }

    private static void run(String inputFileName, String outputFileName){
        DataReader dataReader = new CsvReader();
        List<InputPerson> inputPersons = new ArrayList<>();
        dataReader.readData(inputFileName, inputPersons);

        List<String> urlList = new ArrayList<>();
        generateUrls(inputPersons, urlList);

        WebScraper webScraper = new WebScraper(urlList);
        List<Person> results = webScraper.scrapePages();
        DataWriter dataWriter = new CsvWriter();
        dataWriter.writeData(outputFileName, results);

        System.out.println("Done");
    }

    private static void generateUrls(List<InputPerson> input, List<String> output) {
        for (InputPerson person : input) {
            String url = "https://skos.agh.edu.pl/search/?nazwisko=" + person.getSurname() + "&imie=" + person.getName() + "&tytul=";
            output.add(url);
        }
    }

}