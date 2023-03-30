package es.bifacia.printerstudio.manager;

import es.bifacia.printerstudio.pojo.Card;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationManager {
    private static final int OFFSET_BETWEEN_EXCEL_AND_ARRAYLIST_ROWS = 2;

    public ValidationManager() {
        super();
    }

    /**
     * Validates that the information provided in the Excel for the cards was correct.
     * @param cards List of cards extracted from the Excel file.
     */
    public void validateCards(final List<Card> cards) {
        final StringBuilder stringBuilder = new StringBuilder();
//        String text = checkNumberOfCopiesIsNotZero(cards);
//        if (!text.isEmpty()) {
//            stringBuilder.append("Cards without copies\n").append(text);
//        }
        String text = checkRepeatedCards(cards);
        if (!text.isEmpty()) {
            stringBuilder.append("Repeated cards\n").append(text);
        }
        text = checkCardsWithoutExpansionAlreadyInList(cards);
        if (!text.isEmpty()) {
            stringBuilder.append("Repeated cards without expansion\n").append(text);
        }
        System.out.println(stringBuilder.toString());


    }

    /**
     * Checks if there are cards without the number of copies.
     * @param cards List of cards to check.
     * @return Message with the cards without the number of copies.
     */
    private String checkNumberOfCopiesIsNotZero(final List<Card> cards) {
        final StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (final Card card : cards) {
            if (card.getCopies() <= 0) {
                stringBuilder.append(card.getRow() + ") " + card.getName() + "\n");
            }
        }
        return stringBuilder.toString();
    }

    private String checkRepeatedCards(final List<Card> cards) {
        final StringBuilder stringBuilder = new StringBuilder();
        final Map<String, List<Integer>> cardsMap = new HashMap<>();
        for (final Card card : cards) {
            if (cardsMap.containsKey(card.getName())) {
                final List<Integer> rows = cardsMap.get(card.getName());
                rows.add(card.getRow());
            } else {
                final List<Integer> rows = new ArrayList<>();
                rows.add(card.getRow());
                cardsMap.put(card.getName(), rows);
            }
        }
        for (final Map.Entry<String, List<Integer>> entry : cardsMap.entrySet()) {
            final List<Integer> rows = entry.getValue();
            if (rows.size() > 1) {
                final Map<String, List<Integer>> repeatedMap = new HashMap<>();
                for (final Integer row : rows) {
                    final Card card = cards.get(row - OFFSET_BETWEEN_EXCEL_AND_ARRAYLIST_ROWS);
                    final String expansionName = card.getExpansion();
                    if (repeatedMap.containsKey(expansionName)) {
                        final List<Integer> cardsRows = repeatedMap.get(expansionName);
                        cardsRows.add(card.getRow());
                    } else {
                        final List<Integer> cardsRows = new ArrayList<>();
                        cardsRows.add(card.getRow());
                        repeatedMap.put(expansionName, cardsRows);
                    }
                }
                for (final Map.Entry<String, List<Integer>> expansionEntry : repeatedMap.entrySet()) {
                    final List<Integer> cardRows = expansionEntry.getValue();
                    if (cardRows.size() > 1) {
                        stringBuilder.append(entry.getKey() + " (" + expansionEntry.getKey() + ")").append("\n");
                        for (final Integer cardRow : cardRows) {
                            stringBuilder.append(cardRow).append("\n");
                        }
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    private String checkCardsWithoutExpansionAlreadyInList(final List<Card> cards) {
        final StringBuilder stringBuilder = new StringBuilder();
        final Map<String, Integer> cardsMap = new HashMap<>();
        for (final Card card : cards) {
            if (card.getExpansion() != null && !card.getExpansion().isEmpty()) {
                if (!cardsMap.containsKey(card.getName())) {
                    cardsMap.put(card.getName(), 0);
                }
            } else {
                if (cardsMap.containsKey(card.getName())) {
                    stringBuilder.append(card.getRow()).append("\n");
                }
            }
        }
        return stringBuilder.toString();
    }

}
