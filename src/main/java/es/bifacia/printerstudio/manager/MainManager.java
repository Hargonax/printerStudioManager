package es.bifacia.printerstudio.manager;

import es.bifacia.printerstudio.pojo.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainManager {

    @Value("${cards.excel.file}")
    private String cardsFilePath;

    public MainManager() {
        super();
    }

    public void launchFullProcess() throws Exception {
        final ValidationManager validationManager = new ValidationManager();
        final List<Card> cards = getCardsList();
        if (cards != null) {
            validationManager.validateCards(cards);
        }
    }

    private List<Card> getCardsList() throws Exception {
        final ExcelManager manager = new ExcelManager();
        return manager.getCardsFromFile(cardsFilePath);
    }
}
