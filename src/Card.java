public class Card implements Comparable<Card> {
    private char suit;
    private char rank;
    private int value;

    public Card(char rank, char suit) {
        this.suit = suit;
        this.rank = rank;

        switch (rank) {
            case 'A':
                value = 14;
                break;
            case 'K':
                value = 13;
                break;
            case 'Q':
                value = 12;
                break;
            case 'J':
                value = 11;
                break;
            case 'T':
                value = 10;
                break;
            default:
                value = Character.getNumericValue(rank);
                break;
        }
    }

    public int compareTo(Card c) {
        return this.value - c.value;
    }

    public String toString() {
        return ""+rank+suit;
    }

    public char getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
}
