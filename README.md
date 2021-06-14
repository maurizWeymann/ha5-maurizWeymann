# Hausaufgabe Nr. 5

In dieser Hausaufgabe geht es um Framework-Benutzung und Ereignisbehandlung.

## Abgabe

Nehmen Sie das Assignment auf Github Classroom an und wählen Sie Ihren Namen aus der Liste aus.
Dadurch wird ein (dieses) Repository für Sie erstellt. 
Klonen Sie das Repo und pushen Sie Ihre Lösungen in mehreren sinnvoll zusammengefassten Commits (z.B. einer pro Teilaufgabe, es können aber auch gerne mehr sein).
Sie müssen Ihre Lösung bis zum 22. Juni um 23:59 Uhr gepusht haben, alle Tests müssen grün sein, und Sie müssen spätestens in der darauf folgenden von Ihnen belegten Übungsgruppe Ihre Lösung kurz demonstrieren.

## Aufgabenstellung

Lesen Sie als Vorbereitung die Dokumentation zu Spring Shell: https://docs.spring.io/spring-shell/docs/2.0.0.RELEASE/reference/htmlsingle/

1.	Fügen Sie in der Datei `build.gradle` die Implementierungs-Abhängigkeit `spring-shell-starter` in der Version `2.0.1.RELEASE` hinzu. Lassen Sie `gradle build` laufen und führen Sie dann `java -jar app/build/libs/app-0.0.1-SNAPSHOT.jar` aus. Nutzen Sie das `help` Kommando, um zu sehen, welche Befehle es gibt.
2.	Fügen Sie in der Klasse `SandwichBotCommands` die Annotations `@ShellComponent`, `@ShellMethod` und `@ShellOption` hinzu. Fügen Sie außerdem die `@Service`-Annotation an die zwei Parser- und Builder-Klassen an, die Sie der Klasse `SandwichBotCommands` als Abhängigkeiten zur Verfügung stellen möchten und testen Sie wieder mit `gradle build` und `java -jar ...`, ob neue Befehle hinzugekommen sind.
3.	Implementieren Sie die Methode `order()` entsprechend dem weiter unten spezifizierten Verhalten, sodass die entsprechenden Tests grün werden. Nutzen Sie dafür Code aus der `CommandLineUI` Klasse aus den vorherigen Hausaufgaben. Implementieren Sie dann die Methode `confirm()` ebenfalls und probieren Sie die Befehle im Shell-Programm aus.
4.	Erstellen Sie eine neue Klasse `SandwichBotPromptProvider` (in etwa wie hier beschrieben: https://docs.spring.io/spring-shell/docs/2.0.0.RELEASE/reference/htmlsingle/#_promptprovider) und nutzen Sie dann die Klassen `InputEvent` und `InputEventPublisher` um aus der `order()`-Methode Nachrichten an den `SandwichBotPromptProvider` zu senden, sodass der Prompt rot wird, wenn der Sandwichbot etwas nicht verstanden hat und wieder grün, wenn doch.
    Demonstrieren Sie in der Übung, wie der Prompt je nach Eingabe die Farbe ändert. (Achtung: Im IntelliJ-Terminal wird die Farbe des Prompts nicht angezeigt)
5.  Implementieren Sie nun eine Kommunikation zwischen `SandwichBotCommands` und `SandwichBotPromptProvider` nach dem Observer-Entwurfsmuster, sodass der Prompt immer die Anzahl der Sandwiches in der laufenden Bestellung anzeigt, also z.B. `SANDWICH-BOT(2):>` nach dem zweiten erstellen Sandwich. 
    Lassen Sie dazu zuerst diese beiden Klassen das `Observable` bzw. das `Observer` Interface aus dem gleichen Package implementieren und rufen Sie `notifyObservers` auf, sobald sich die Anzahl der erstellten Sandwiches ändert. 
    Nutzen Sie im `SandwichBotPromptProvider` die Annotationen `@Autowired` und `@PostConstruct` um sich erst eine Instanz von `SandwichBotCommands` zu holen und sich dann als Observer bei dieser zu registrieren. 
    In der `update`-Methode können Sie nun einfach auf das nicht-private Attribut `orderedSandwiches` von `SandwichBotCommands` (bzw. auf dessen Grösse) zugreifen.
    

# Anleitung
...zur Benutzung des Sandwichbot Shell-Programms (erwartetes Verhalten).

Nach dem Start der Anwendung kann eine Bestellung wie folgt aufgegeben werden:

```bash
SANDWICH-BOT:>order --text 'Ich hätte gerne ein Sandwich mit Ciabatta, Salami und Mayo'
In Ordnung. Dein 1. Sandwich mit [Ciabatta, Salami, Mayo] kostet 1.90 EUR.
Gib <confirm> ein, um die Bestellung abzuschliessen oder bestelle ein weiteres Sandwich mit <order -t '...'>
SANDWICH-BOT:>order -t 'Ich hätte gerne ein weiteres Sandwich mit Vollkorn, Tomate, Eisbergsalat und Mayo'
In Ordnung. Dein 2. Sandwich mit [Vollkorn, Eisbergsalat, Tomate, Mayo] kostet 1.90 EUR.
Gib <confirm> ein, um die Bestellung abzuschliessen oder bestelle ein weiteres Sandwich mit <order -t '...'>
SANDWICH-BOT:>order 'Ich hätte gerne noch ein Sandwich mit Ciabatta und Salami, Salami, Salami, Mayo. Yummy'
In Ordnung. Dein 3. Sandwich mit [Ciabatta, Salami, Salami, Salami, Mayo] kostet 3.70 EUR.
Gib <confirm> ein, um die Bestellung abzuschliessen oder bestelle ein weiteres Sandwich mit <order -t '...'>
SANDWICH-BOT:>
```

Zum Abschluss der Bestellung ist das Kommando `confirm` zu verwenden:

```bash
SANDWICH-BOT:>confirm
Vielen Dank für deine Bestellung. Du hast 3 Sandwiches bestellt. Die Gesamtsumme beträgt 7.50 EUR.
```

Das Programm kann mit `exit` beendet werden:

```bash
SANDWICH-BOT:>exit
```
