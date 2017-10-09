import java.io.*;
import java.util.*;

public class Hand {

    private Card[] cards;
    private int handValue;

    public Hand(long n) {
        loadCards(n);
        Arrays.sort(cards);
        handValue = valueOfHand();
    }

    private void loadCards(long n) {
        cards = new Card[5];
        try {
            FileReader reader = new FileReader("p054_poker.txt");
            char c;
            int i = 0;
            reader.skip(n*15);
            while(i<5) {
                c = (char) reader.read();
                if (c == ' ') {
                    continue;
                }

                cards[i] = new Card(c,(char) reader.read());
                i++;
            }
            reader.close();
        } catch(IOException e) {
            System.out.println("File could not be found");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Card c : cards) {
            sb.append(c);
            sb.append(' ');
        }
        return sb.toString();
    }

    private boolean isFlush() {
        char c0 = cards[0].getSuit();
        for(Card c: cards) {
            if(c.getSuit() != c0) {
                return false;
            }
        }
        return true;
    }

    private int noOfValue(int value) {
        int sum = 0;
        for(Card c : cards) {
            if (value == c.getValue()) {
                sum++;
            }
        }
        return sum;
    }

    private boolean isStraight() {
        int oldVal = -1;
        for (Card c:cards) {
            if(oldVal == -1 || c.getValue() == oldVal+1) {
                oldVal = c.getValue();
                continue;
            }
            return false;
        }
        return true;
    }

    private Card highCard() {
        Card best = null;
        for (Card c : cards) {
            if (best == null || best.getValue() < c.getValue()) {
                best = c;
            }
        }
        return best;
    }

    private int valueOfHand() {
        boolean halfHouse = false;
        boolean fullHouse = false;
        int val = highCard().getValue();
        for(int i = 2; i<15; i++) {
            if (noOfValue(i) == 2) {
                val += 1000 + 10*i;
                halfHouse = true;
            }
            if (noOfValue(i) == 3) {
                val += 3000 + 10*i;
                fullHouse = true;
            }
            val += noOfValue(i) == 4 ? 7000+10*i : 0;
        }
        if(halfHouse && fullHouse) {
            val += 2000;
        }
        val += isStraight() ? 4000 : 0;
        val += isFlush() ? 5000 : 0;
        return val;
    }

    public String type() {
        if (handValue > 9014) {
            return  "???";
        } else if (handValue == 9014) {
            return "Royal Straight Flush";
        } else if (handValue > 9000) {
            return "Straight Flush";
        } else if (handValue > 7000) {
            return "Four of a Kind";
        } else if (handValue > 6000) {
            return "Full House";
        } else if (handValue > 5000) {
            return "Flush";
        } else if (handValue > 4000) {
            return "Straight";
        } else if (handValue > 3000) {
            return "Three of a kind";
        } else if (handValue > 2000) {
            return "Two Pair";
        } else if (handValue > 1000) {
            return "One Pair";
        } else {
            return "High Card: " + highCard();
        }

    }

    public int getHandValue() {
        return handValue;
    }
}
