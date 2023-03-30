package es.bifacia.printerstudio.manager;

import es.bifacia.printerstudio.pojo.Card;
import es.bifacia.printerstudio.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class MainManager {

    public MainManager() {
        super();
    }

    public void launchFullProcess() {
        FileUtils.printFolderContent("./");
        final List<Card> cards = getCardsList();
    }

    private List<Card> getCardsList() {
        final List<Card> cards = new ArrayList<>();

        return cards;
    }
}
