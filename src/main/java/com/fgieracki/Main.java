package com.fgieracki;

import com.fgieracki.data.InputPerson;
import com.fgieracki.data.Person;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        if(args.length != 2 && args.length != 1) {
            System.out.println("Wrong number of arguments");
            System.out.println("Usage: java -jar skos-scraper.jar <input_file>.csv <output_file>.csv to retrieve personal data");
            System.out.println("or java -jar skos-scraper.jar <output_file>.csv to retrieve departments data");
            return;
        }

        WebScraper webScraper = null;
        String outputFileName = null;

        if(args.length == 2) {
            webScraper = new WebScraper(readInputFile(args[0]));
            outputFileName = args[1];
        } else if (args.length == 1) {
            webScraper = new WebScraper(getDepartmentListUrl());
            outputFileName = args[0];
        }

        List<Person> results = webScraper.scrapePages();
        DataWriter dataWriter = new CsvWriter();
        dataWriter.writeData(outputFileName, results);
    }

    private static List<String> readInputFile(String inputFileName) {
        DataReader dataReader = new CsvReader();
        List<InputPerson> inputPersons = dataReader.readData(inputFileName);

        return generateUrls(inputPersons);
    }

    private static List<String> generateUrls(List<InputPerson> input) {
        List<String> output = new ArrayList<>();
        for (InputPerson person : input) {
            String url = "https://skos.agh.edu.pl/search/?nazwisko=" + person.getSurname() + "&imie=" + person.getName() + "&tytul=";
            output.add(url);
        }

        return output;
    }

    private static List<String> getDepartmentListUrl() {
        List<String> output = new ArrayList<>();
        String url = "https://skos.agh.edu.pl/jednostki/";
        output.add(url);

        return output;
    }

}