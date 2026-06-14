Projekt zaliczeniowy na studia podyplomowe: Metody wytwarzania oprogramowania 2025/2026

Program ma za zadanie generować raporty pokazujące czas pracy.

Wymagania funkcjonalne do programu:

1. Raporty maja być wyświetlane w konsoli w formie czytelnej tabeli
2. Po wyświetleniu raportu w konsoli powinien być wygenerowany plik .xlsx
3. Informacja o błędnych danych powinna zostać wyświetlona w konsoli
4. Błędne dane powinny zostać pominięte w finalnym raporcie
5. Raporty będą generowane na podstawie plików .xlsx z folderu ...ReporterAGH2026\src\main\resources
6. Do każdej klasy powinny zostać stworzone testy automatyczne
7. Kod programu powinien być napisany w języku angielskim
8. Raporty uruchamia się przy pomocy komend w konsoli
9. Aplikacja jest uruchamiana dla konkretnego raportu
    - komenda: "java -jar App.java -r1" wywołuje raport 1
    - komenda: "java -jar App.java -r2" wywołuje raport 2
    - komenda: "java -jar App.java -r3" wywołuje raport 3
    - komenda: "java -jar App.java -r4" wywołuje raport 4
    - komenda: "java -jar App.java -r5" wywołuje raport 5


Raport 1:
Raport pokazuje czas zalogowany przez danego pracownika

Zestawienie czasu pracy pracowników za okres: 2026-01-01 - 2026-06-30
___________________________________________
|   Nazwa Pracownika  |   Czas [godziny]  |
|---------------------|-------------------|
|   Kowalski_Jan      |       55          |
|   Nowak_Piotr       |       78          |

===============================================================================================================

Raport 2:
Raport pokazuje czas zalogowany do danego projektu

Zestawienie czasu pracy w projektach za okres: 2026-01-01 - 2026-06-30
___________________________________________
|   Nazwa Projektu    |   Czas [godziny]  |
|---------------------|-------------------|
|   Projekt1          |       389         |
|   Projekt2          |       257         |

===============================================================================================================

Raport 3:
Raport pokazuje czas w godzinach i procentach zalogowany do projeku przez pracownika

Zestawienie projektów dla pracownik za okres: 2026-01-01 - 2026-06-30
Kowalski Jan
_________________________________________________________________
|   Nazwa Projektu    |   Czas [godziny]  |    Czas [%]         |
|---------------------|-------------------|-------------------- |
|   Projekt1          |       200         |         50%         |
|   Projekt2          |       200         |         50%         |

===============================================================================================================

Raport 4:
Raport pokazuje czas zalogowany do zadań w projekcie

Zestawienie zadań w projekcie za okres: 2026-01-01 - 2026-06-30
Projekt 1
___________________________________________
|   Nazwa Zadania     |   Czas [godziny]  |
|---------------------|-------------------|
|   Zadanie1          |       23          |
|   Zadanie2          |       17          |

===============================================================================================================

Raport 5:
Raport pokazuje czas zalogowany do zadań przez danego pracownika

Zestawienie zadań dla pracownika za okres: 2026-01-01 - 2026-06-30
Kowalski Jan
___________________________________________
|   Nazwa Zadania     |   Czas [godziny]  |
|---------------------|-------------------|
|   Zadanie1          |       389         |
|   Zadanie2          |       257         |

===============================================================================================================