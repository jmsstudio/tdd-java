package br.com.jmsstudio.tddJava.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluatorTest {

    @Test
    public void shouldEvaluateAuctionWithOneBid() {
        Auction auction = new Auction("test");
        auction.bid(new Bid(new User("john"), 200));

        Evaluator evaluator = new Evaluator();

        evaluator.evaluate(auction);

        assertEquals(200, evaluator.getMaxBid(), 0.001);
        assertEquals(200, evaluator.getMinBid(), 0.001);
    }

    @Test
    public void shouldEvaluateAuctionAndCalculateMaxAndMinValuesCorrectly() {
        User john = new User("john");
        User mary = new User("mary");
        User joseph = new User("joseph");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(john, 500));
        auction.bid(new Bid(mary, 100));
        auction.bid(new Bid(joseph, 200));

        Evaluator evaluator = new Evaluator();

        evaluator.evaluate(auction);

        assertEquals(500, evaluator.getMaxBid(), 0.001);
        assertEquals(100, evaluator.getMinBid(), 0.001);
    }

    @Test
    public void shouldCalculateTheAverageOfBidsCorrectly() {
        User john = new User("john");
        User mary = new User("mary");
        User joseph = new User("joseph");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(john, 500));
        auction.bid(new Bid(mary, 100));
        auction.bid(new Bid(joseph, 200));

        Evaluator evaluator = new Evaluator();

        assertEquals((500 + 100 + 200) / 3.0, evaluator.getAverageBid(auction), 0.01);
    }
}
