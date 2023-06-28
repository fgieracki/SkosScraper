package com.fgieracki;

import com.fgieracki.data.Person;

import java.io.IOException;
import java.util.List;

public interface DataWriter {
    void writeData(String fileName, List<Person> data);
}
