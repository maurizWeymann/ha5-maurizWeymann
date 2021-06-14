package htw.berlin.wi.prog2.ui;

import htw.berlin.wi.prog2.domain.SandwichBuilder;
import htw.berlin.wi.prog2.parsing.CountingInputParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SandwichBotCommandsIntegrationTest {

  private SandwichBotCommands classUnderTest;

  @BeforeEach
  void setup() {
    classUnderTest = new SandwichBotCommands(
        new CountingInputParser(),
        new SandwichBuilder(),
        new InputEventPublisher());
  }

  @Test
  @DisplayName("should understand a simple order and list the sandwich's individual ingredients and it's price")
  void testSimpleOrder() {
    String simpleOrder = "Ich hätte gerne ein Sandwich mit Ciabatta, Salami, Eisbergsalat, Tomate und Mayo";

    var actual = classUnderTest.order(simpleOrder);

    var expectedMessage = "In Ordnung. Dein 1. Sandwich mit [Ciabatta, Salami, Eisbergsalat, " +
                          "Tomate, Mayo] kostet 2.90 EUR.";
    assertTrue(actual.startsWith(expectedMessage));
  }
}
