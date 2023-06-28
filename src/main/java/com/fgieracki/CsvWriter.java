package com.fgieracki;

import com.fgieracki.data.Person;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class CsvWriter implements DataWriter {
    @Override
    public void writeData(String fileName, List<Person> data) {
        System.out.println("Writing data to CSV file...");
        Path path = Paths.get(fileName);
        try (var writer = Files.newBufferedWriter(path)) {
            MappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(Person.class);

            StatefulBeanToCsv beanWriter = new StatefulBeanToCsvBuilder<Person>(writer)
                    .withMappingStrategy(strategy)
                    .build();
            beanWriter.write(Person.getHeaders());
            beanWriter.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }
}
