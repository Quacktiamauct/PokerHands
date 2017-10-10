public class PokerHands {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Hand h1;
        Hand h2;
        int noOfWinsPlayer1 = 0;
        int noOfWinsPlayer2 = 0;
        for(int i = 0; i < 2000; i+=2) {
            h1 = new Hand(i);
            h2 = new Hand(i+1);
            noOfWinsPlayer1 += h1.getHandValue() > h2.getHandValue() ? 1 : 0;
            noOfWinsPlayer2 += h1.getHandValue() < h2.getHandValue() ? 1 : 0;
            System.out.println("P1: " + h1.type());
            System.out.println(h1);
            //System.out.println(h1.getHandValue());

            System.out.println("P2: " + h2.type());
            System.out.println(h2);
            //System.out.println(h2.getHandValue());

        }
        System.out.println("No. of wins for Player 1: " + noOfWinsPlayer1);
        System.out.println("No. of wins for Player 2: " + noOfWinsPlayer2);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " milliseconds");
    }
}
