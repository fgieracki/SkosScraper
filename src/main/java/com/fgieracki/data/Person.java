package com.fgieracki.data;

import com.opencsv.bean.CsvBindAndJoinByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import lombok.Setter;

@Data
public class Person {

    @CsvBindByName(column = "Imię")
    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByName(column = "Nazwisko")
    @CsvBindByPosition(position = 1)
    private String surname;
    @CsvBindByName(column = "Tytuł")
    @CsvBindByPosition(position = 2)
    private String title;
    @CsvBindByName(column = "Stanowisko")
    @CsvBindByPosition(position = 3)
    private String position;
    @CsvBindByName(column = "Grupa")
    @CsvBindByPosition(position = 4)
    private String group;

    @CsvBindByName(column = "Funkcja")
    @CsvBindByPosition(position = 5)
    private String function;
    @CsvBindByName(column = "Siedziba")
    @CsvBindByPosition(position = 6)
    private String location;
    @CsvBindByName(column = "Telefon")
    @CsvBindByPosition(position = 7)
    private String phone;
    @CsvBindByName(column = "URL")
    @CsvBindByPosition(position = 8)
    private String url;

    @CsvBindByName(column = "Jednostka")
    @CsvBindByPosition(position = 9)
    private String department;

    @CsvBindByName(column = "E-mail")
    @CsvBindByPosition(position = 10)
    private String email;

    public Person() {
        url = "";
        name = "";
        surname = "";
        title = "";
        email = "";
        position = "";
        group = "";
        function = "";
        location = "";
        phone = "";
        department = "";
    }

    public static Person getHeaders() {
        Person result = new Person();
        result.setName("Imię");
        result.setSurname("Nazwisko");
        result.setTitle("Tytuł");
        result.setPosition("Stanowisko");
        result.setGroup("Grupa");
        result.setFunction("Funkcja");
        result.setLocation("Siedziba");
        result.setPhone("Telefon");
        result.setUrl("URL");
        result.setDepartment("Jednostka");
        result.setEmail("E-mail");

        return result;
    }

    public void setTitle(String newTitle) {
        if(newTitle.contains(name)) {
            newTitle = newTitle.replace(name, "");
        }
        if(newTitle.contains(surname)) {
            newTitle = newTitle.replace(surname, "");
        }
        newTitle = newTitle.trim();
        if(newTitle.startsWith(",")) {
            newTitle = newTitle.substring(1);
        }
        this.title = newTitle;
    }
}
