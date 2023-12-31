package com.fgieracki;

import com.fgieracki.data.InputPerson;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader implements DataReader{

    @Override
    public List<InputPerson> readData(String fileName) {
        System.out.println("Reading data from CSV file...");

        List<InputPerson> data = new ArrayList<>();

        try(CSVReader csvReader = new CSVReader(new FileReader(fileName))){
            ColumnPositionMappingStrategy<InputPerson> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(InputPerson.class);

            String[] memberFieldsToBindTo = {"name", "surname"};
            strategy.setColumnMapping(memberFieldsToBindTo);

            CsvToBean<InputPerson> csvToBean = new CsvToBean<>();
            csvToBean.setMappingStrategy(strategy);
            csvToBean.setCsvReader(csvReader);
            List<InputPerson> list = csvToBean.parse();

            for (InputPerson person : list) {
                data.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
