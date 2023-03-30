package es.bifacia.printerstudio.manager;

import es.bifacia.printerstudio.pojo.Card;
import es.bifacia.printerstudio.util.ExcelUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelManager {
    private static final Logger logger = LogManager.getLogger();

    public ExcelManager() {
        super();
    }

    /**
     * Gets the cards relation that are present in an Excel file.
     * @param filePath Path to the cards file.
     * @return List of cards.
     * @throws Exception
     */
    public List<Card> getCardsFromFile(final String filePath) throws Exception {
        final List<Card> cards = new ArrayList<>();
        try {
            try (final FileInputStream inputStream = new FileInputStream(new File(filePath))) {
                final Workbook workbook = new XSSFWorkbook(inputStream);
                final Sheet sheet = workbook.getSheetAt(0);
                int i = 1;
                boolean first = true;
                for (final Row row : sheet) {
                    if (first) {
                        first = false;
                    } else {
                        final Card card = this.parseCardInformation(row, i);
                        if (card != null) {
                            cards.add(card);
                        }
                    }
                    i++;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error getting cards information from Excel file. " + ex.getMessage());
        }
        return cards;
    }

    /**
     * Gets the information of a card from a row of the Excel file.
     * @param row Row with the card infomation.
     * @return Object with the information of the card.
     */
    private Card parseCardInformation(final Row row, final int rowNumber) {
        Card card = null;
        try {
            final String name = ExcelUtils.getStringValue(row.getCell(0));
            if (name != null && !name.isEmpty()) {
                card = new Card();
                card.setName(name);
                card.setExpansion(ExcelUtils.getStringValue(row.getCell(1)));
                card.setCopies(ExcelUtils.getIntValue(row.getCell(2)));
                card.setBack(ExcelUtils.getStringValue(row.getCell(3)));
                card.setRow(rowNumber);
            }
        } catch (Exception ex) {
            logger.error("Error parsing card information for row " + rowNumber);
            throw new RuntimeException(ex);
        }
        return card;
    }
}
