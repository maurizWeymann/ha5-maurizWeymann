package htw.berlin.wi.prog2.ui;

import htw.berlin.wi.prog2.data.Menu;
import htw.berlin.wi.prog2.data.MenuUtils;
import htw.berlin.wi.prog2.domain.Ingredient;
import htw.berlin.wi.prog2.domain.Sandwich;
import htw.berlin.wi.prog2.domain.SandwichBuilder;
import htw.berlin.wi.prog2.parsing.ExtendableInputParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO (2) Annotations @ShellComponent, @ShellMethod, und @ShellOption verwenden
public class SandwichBotCommands {

  final List<Sandwich> orderedSandwiches;

  private final ExtendableInputParser parser;
  private final SandwichBuilder builder;
  private final InputEventPublisher inputEventPublisher;
  private final Menu menu;

  public SandwichBotCommands(ExtendableInputParser parser, SandwichBuilder builder, InputEventPublisher inputEventPublisher) {
    this.inputEventPublisher = inputEventPublisher;
    this.orderedSandwiches = new ArrayList<>();
    this.parser = parser;
    this.builder = builder;
    this.menu = Menu.getInstance();
  }

  public String order(String inputLine) {
    // TODO (3) parser, builder, menu, orderedSandwiches nutzen, um Bestellungen aufzunehmen
    return "In Ordnung ...";
    // TODO (4) Klasse SandwichBotPromptProvider erstellen und dann inputEventPublisher für Prompt-Updates nutzen
    // TODO (5) Observer-Muster implementieren und hier die notifyObservers-Methode aufrufen
  }

  public String confirm() {
    // TODO (3) Preis der gesamten Bestellung mithilfe von orderedSandwiches berechnen
    return "Vielen Dank...";
  }
}
