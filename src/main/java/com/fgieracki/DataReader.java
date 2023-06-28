package com.fgieracki;

import com.fgieracki.data.InputPerson;

import java.util.List;

public interface DataReader {
    void readData(String fileName, List<InputPerson> data);
}
