# Skos Scraper

version: 1.0 BETA

## Table of contents

- [Description](#description)
- [Usage](#usage)
- [Example](#example)
- [Authors](#authors)
- [License](#license)

## Description

This is a simple web scraper which takes csv file with data format:
```csv
name, surname
```
and retrieves data from [skos.agh.edu.pl](http://skos.agh.edu.pl) website. It returns csv file with data format:
```csv
name, surname, title, position, group, function, location, phone, skos-url, department, email
```
## Usage

To run this application you need to have installed Java Runtime Environment (JRE) version 8 or higher. You can download it from [here](https://www.java.com/en/download/).

Open terminal and type:
```bash
java -jar skos-scraper.jar <input-file> <output-file>
```
where:
```
<input-file> - path to input csv file
<output-file> - path to output csv file
``` 

## Example

```bash
java -jar skos-scraper.jar input.csv output.csv
```

## Authors

* **Filip Gieracki** - [fgieracki](//github.com/fgieracki)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
