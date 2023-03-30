package es.bifacia.printerstudio.pojo;

public class Card {
    private String name = null;
    private String expansion = null;
    private int copies = 0;
    private String back = null;

    public Card() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
