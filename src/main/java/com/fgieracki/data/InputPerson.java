package com.fgieracki.data;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class InputPerson {
    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByPosition(position = 1)
    private String surname;
}
