Projekt zaliczeniowy na studia podyplomowe: Metody wytwarzania oprogramowania 2025/2026

Program ma za zadanie generować raporty pokazujące czas pracy, zadania w projektach

Wymagania do programu:

1. Raporty maja być wyświetlane w konsoli w formie czytelnej tabeli
2. Po wyświetleniu raportu w konsoli powinien być wygenerowany plik .xlsx
3. Informacja o błędnych danych powinna zostać wyświetlona w konsoli
4. Błędne dane powinny zostać pominięte w finalnym raporcie
5. Raporty będą generowane na podstawie plików .xlsx z folderu ...ReporterAGH2026\src\main\resources
6. Do każdej klasy powinny zostać stworzone testy
7. Raporty uruchamia się przy pomocy komend w konsoli
8. Aplikacja jest uruchamiana dla konkretnego raportu

---
Raport 1:
Raport pokazuje czas zalogowany przez danego pracownika

Zestawienie czasu pracy pracowników za okres: 2026-01-01 - 2026-06-30

|   Nazwa Pracownika  |   Czas [godziny]  |
|---------------------|-------------------|
|   Kowalski_Jan      |       55          |
|   Nowak_Piotr       |       78          |

Raport jest uruchamiany komendą: -r1

---

Raport 2:
Raport pokazuje czas zalogowany do danego projektu

Zestawienie czasu pracy w projektach za okres: 2026-01-01 - 2026-06-30

|   Nazwa Projektu    |   Czas [godziny]  |
|---------------------|-------------------|
|   Projekt1          |       389         |
|   Projekt2          |       257         |

Raport jest uruchamiany komendą: -r2

---

Raport 3:
Raport pokazuje czas w godzinach i procentach zalogowany do projeku przez pracownika

Zestawienie projektów dla pracownik za okres: 2026-01-01 - 2026-06-30
Kokos Jan

|   Nazwa Projektu    |   Czas [godziny]  |    Czas [%]         |
|---------------------|-------------------|-------------------- |
|   Projekt1          |       200         |         50%         |
|   Projekt2          |       200         |         50%         |

Raport jest uruchamiany komendą: -r3 -emp Nazwisko Imię -from DD.MM.RRRR -to DD.MM.RRR
Przykład: -r3 -emp Kokos Jan

---

Raport 4:
Raport pokazuje czas zalogowany do zadań w projekcie

Zestawienie zadań w projekcie za okres: 2026-01-01 - 2026-06-30
Projekt1

|   Nazwa Zadania     |   Czas [godziny]  |
|---------------------|-------------------|
|   Zadanie1          |       23          |
|   Zadanie2          |       17          |

Raport jest uruchamiany komendą: -r4 -emp NazwaProjektu -from DD.MM.RRRR -to DD.MM.RRR
Przykład: -r4 -emp Projekt1

---

Raport 5:
Raport pokazuje czas zalogowany do zadań przez danego pracownika

Zestawienie zadań dla pracownika za okres: 2026-01-01 - 2026-06-30
Kokos Jan

|   Nazwa Zadania     |   Czas [godziny]  |
|---------------------|-------------------|
|   Zadanie1          |       389         |
|   Zadanie2          |       257         |

Raport jest uruchamiany komendą: -r5 -emp Nazwisko Imię -from DD.MM.RRRR -to DD.MM.RRR
Przykład: -r5 -emp Kokos Jan -from 12.01.2012 -to 17.01.2012

---


Osługa błędów:
|**Klasa**|**Nazwa błędu**|
|---------|---------------|
|App|Brak podanych parametrów w konsoli - komunikat: "Please provide params to generate report"|
|XlsxReader|Informacja - pusta komórka|
|XlsxReader|Informacja -komórka z nieporawną datą|
|XlsxReader|Informacja -komórka z niepoprawnym czasem (, zamiast .)|
|XlsxReader|Informacja - komórka z niedodatnik czasem (<=0)|
|ResultPrinter|Brak danych do wyświetlenia (pusty excel lub brak danych w podanym zakresie) - komunikat: "Zestawienie czasu pracy pracowników - Brak Danych !"|
|CliParser|Podanych kilka typów raportu - komunikat: "Multiple report types detected. You can generate only 1 report at the same time"|
|CliParser|Brak podanych danych pracownika w konsoli "Employee name not specified"|
|ResultPrinter|Brak danych do wyświetlenia (pusty excel lub brak danych w podanym zakresie) - komunikat: "Zestawienie czasu pracy pracowników - Brak Danych !"|
|ReportOrchestrator|Nieprawidłowy format daty - komunikat: "Invalid date format."|
|ReportOrchestrator|Nieprawidłowy typ raportu - komunikat: "Invalid report type. Report type -r10 does not exist."|
|ReportOrchestrator|Nieprawidłowe parametry - komunikat: "Wrong report params: " + e.getMessage()|


