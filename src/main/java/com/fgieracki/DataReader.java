package com.fgieracki;

import com.fgieracki.data.InputPerson;

import java.util.List;

public interface DataReader {
    List<InputPerson> readData(String fileName);
}
