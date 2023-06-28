package com.fgieracki;

import com.fgieracki.data.InputPerson;
import com.fgieracki.data.Person;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WebScraper {
    List<Person> people = new ArrayList<>();
    List<String> pagesToDiscover = new ArrayList<>();
    Set<String> pagesDiscovered = new HashSet<>();

    WebScraper(List<String> pagesToDiscover) {
        this.pagesToDiscover = pagesToDiscover;
    }

    public List<Person> scrapePages() {
        while (!pagesToDiscover.isEmpty()) {
            String page = pagesToDiscover.remove(0);
            pagesDiscovered.add(page);
            System.out.println("Scraping page: " + page);
            Document doc = null;
            try {
                doc = Jsoup
                        .connect(page)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:64.0) Gecko/20100101 Firefox/64.0")
                        .header("Accept-Language", "*")
                        .get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if(doc.location().contains("search")) {
                System.out.println("Scraping person page: " + page);
                List<String> newUrlsList = scrapePeopleListPage(doc);
                for(String url : newUrlsList) {
                    if(!pagesDiscovered.contains(url) && !pagesToDiscover.contains(url)) {
                        pagesToDiscover.add(url);
                    }
                }
            } else {
                people.add(scrapePersonPage(doc));
            }

        }
        return people;
    }


    private List<String> scrapePeopleListPage(Document document) {
        List<String> result = new ArrayList<>();
        Elements tableRows = document.select("table.lista-osob > tbody > tr");

        for(int i = 0; i < tableRows.size(); i++) {
            String url = tableRows
                    .get(i)
                    .select("th")
                    .get(0)
                    .select("a")
                    .attr("href");
            result.add(url);
        }

        return result;
    }
    private Person scrapePersonPage(Document document) {
        Person result = new Person();
        result.setUrl(document.location());
        result.setName(document.select("span.given-name").text());
        result.setSurname(document.select("span.family-name").text());
        result.setTitle(document.select("h1").text());
        result.setEmail(document.select("a.email").text());
        extractWorkPlaceData(document, result);

        System.out.println("Scraped person: " + result.getName() + " " + result.getSurname());
        return result;
    }

    private void extractWorkPlaceData(Document document, Person result){
        Elements tableRows = document.select("table.info-osoba > tbody > tr");

        for(Element row : tableRows) {
            String key = row.select("th").text();
            String value = row.select("td").text();
            switch (key) {
                case "Stanowisko":
                    result.setPosition(result.getPosition().isEmpty()? value : result.getPosition() + "; " + value);
                    break;
                case "Grupa":
                    result.setGroup(result.getGroup().isEmpty()? value : result.getGroup() + "; " + value);
                    break;
                case "Siedziba":
                    result.setLocation(result.getLocation().isEmpty()? value : result.getLocation() + "; " + value);
                    break;
                case "Telefon":
                    result.setPhone(result.getPhone().isEmpty()? value : result.getPhone() + "; " + value);
                    break;
                case "Jednostka":
                    result.setDepartment(result.getDepartment().isEmpty()? value : result.getDepartment() + "; " + value);
                    break;
                case "Funkcja":
                    result.setFunction(result.getFunction().isEmpty()? value : result.getFunction() + "; " + value);
                    break;
                default:
                    System.out.println("Unknown key: " + key);
            }
        }
    }

}
