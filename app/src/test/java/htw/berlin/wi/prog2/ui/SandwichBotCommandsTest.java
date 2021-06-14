package htw.berlin.wi.prog2.ui;

import htw.berlin.wi.prog2.data.Menu;
import htw.berlin.wi.prog2.data.MenuUtils;
import htw.berlin.wi.prog2.domain.Ingredient;
import htw.berlin.wi.prog2.domain.Sandwich;
import htw.berlin.wi.prog2.domain.SandwichBuilder;
import htw.berlin.wi.prog2.parsing.ExtendableInputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SandwichBotCommandsTest {

  @Mock
  private ExtendableInputParser inputParser;

  @Mock
  private SandwichBuilder sandwichBuilder;

  @Mock
  private InputEventPublisher inputEventPublisher;

  @InjectMocks
  private SandwichBotCommands classUnderTest;

  @Test
  @DisplayName("should pass user input to input parser")
  void shouldPassInputToParser() {
    String input = "Ich hätte gerne ein Sandwich mit Salami und Tomate";
    classUnderTest.order(input);
    verify(inputParser).idsAndCountFromInput(matches(input), anyMap());
  }

  @Test
  @DisplayName("should print the whole menu if the input was not understood")
  void printMenu() {
    doReturn(emptyMap()).when(inputParser).idsAndCountFromInput(anyString(), anyMap());
    var actual = classUnderTest.order("irgendwas");
    assertTrue(actual.startsWith("Entschuldigung, ich habe dich nicht verstanden. Wähle aus folgenden Zutaten: "
            + MenuUtils.focusOnNames(Menu.getInstance().getAllArticles())));
  }

  @Test
  @DisplayName("should return single sandwich order summary")
  void returnSingleSandwichOrderSummary() {
    Ingredient bunMock = mock(Ingredient.class);
    doReturn("Ciabatta").when(bunMock).toString();
    Ingredient veggieMock = mock(Ingredient.class);
    doReturn("Mayo").when(veggieMock).toString();
    Sandwich sandwichMock = mock(Sandwich.class);
    doReturn(List.of(bunMock.toString(), veggieMock.toString())).when(sandwichMock).getIngredientNames();
    doReturn(new BigDecimal("3.70")).when(sandwichMock).calculatePrice();
    doReturn(Map.of(2L, 1, 9L, 1)).when(inputParser).idsAndCountFromInput(anyString(), anyMap());
    doReturn(sandwichBuilder).when(sandwichBuilder).add(any());
    doReturn(sandwichMock).when(sandwichBuilder).build();

    String orderSummary = classUnderTest.order("any order");

    var expectedOrderSummary = "In Ordnung. Dein 1. Sandwich mit [Ciabatta, Mayo] kostet 3.70 EUR.\nGib <confirm> ein, " +
                               "um die Bestellung abzuschliessen oder bestelle ein weiteres Sandwich mit <order -t '...'>";
    assertEquals(expectedOrderSummary, orderSummary);
  }

  @Test
  @DisplayName("should return order confirmation")
  void returnOrderConfirmation() {
    Sandwich sandwichMock1 = mock(Sandwich.class);
    Sandwich sandwichMock2 = mock(Sandwich.class);
    doReturn(new BigDecimal("7.50")).when(sandwichMock1).calculatePrice();
    doReturn(new BigDecimal("5.30")).when(sandwichMock2).calculatePrice();
    classUnderTest.orderedSandwiches.addAll(List.of(sandwichMock1, sandwichMock2));

    String actual = classUnderTest.confirm();

    var expectedMessage = "Vielen Dank für deine Bestellung. Du hast 2 Sandwiches bestellt. Die Gesamtsumme beträgt 12.80 EUR.";
    assertEquals(expectedMessage, actual);
  }
}
