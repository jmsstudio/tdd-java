package br.com.jmsstudio.tddJava.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

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
    public void shouldEvaluateAuctionWithBidsInDescendingOrder() {
        User john = new User("john");
        User mary = new User("mary");
        User joseph = new User("joseph");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(john, 500));
        auction.bid(new Bid(joseph, 200));
        auction.bid(new Bid(mary, 100));

        Evaluator evaluator = new Evaluator();

        evaluator.evaluate(auction);

        assertEquals(500, evaluator.getMaxBid(), 0.001);
        assertEquals(100, evaluator.getMinBid(), 0.001);
    }

    @Test
    public void shouldEvaluateAuctionWithBidsInAscendingOrder() {
        User john = new User("john");
        User mary = new User("mary");
        User joseph = new User("joseph");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(mary, 100));
        auction.bid(new Bid(joseph, 200));
        auction.bid(new Bid(john, 500));

        Evaluator evaluator = new Evaluator();

        evaluator.evaluate(auction);

        assertEquals(500, evaluator.getMaxBid(), 0.001);
        assertEquals(100, evaluator.getMinBid(), 0.001);
    }

    @Test
    public void shouldEvaluateAuctionWithBidsInRandomOrder() {
        User john = new User("john");
        User mary = new User("mary");
        User joseph = new User("joseph");
        User flint = new User("flint");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(mary, 200));
        auction.bid(new Bid(joseph, 100));
        auction.bid(new Bid(john, 500));
        auction.bid(new Bid(flint, 150));
        auction.bid(new Bid(mary, 300));
        auction.bid(new Bid(joseph, 250));
        auction.bid(new Bid(john, 400));

        Evaluator evaluator = new Evaluator();

        evaluator.evaluate(auction);

        assertEquals(500, evaluator.getMaxBid(), 0.001);
        assertEquals(100, evaluator.getMinBid(), 0.001);
    }

    @Test
    public void shouldCalculateTheAverageOfBids() {
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

    @Test
    public void shouldGetThreeBiggestBidsFromFiveBids() {
        User john = new User("john");
        User mary = new User("mary");
        User joseph = new User("joseph");
        User flint = new User("flint");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(mary, 200));
        auction.bid(new Bid(joseph, 100));
        auction.bid(new Bid(john, 500));
        auction.bid(new Bid(flint, 150));
        auction.bid(new Bid(mary, 300));

        Evaluator evaluator = new Evaluator();

        evaluator.evaluate(auction);

        final List<Bid> biggestBids = evaluator.getThreeBiggestBids();
        assertEquals(3, biggestBids.size());
        assertEquals(500, biggestBids.get(0).getValue());
        assertEquals(300, biggestBids.get(1).getValue());
        assertEquals(200, biggestBids.get(2).getValue());
    }

    @Test
    public void shouldGetThreeBiggestBidsFromTwoBids() {
        User john = new User("john");
        User mary = new User("mary");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(mary, 200));
        auction.bid(new Bid(john, 300));

        Evaluator evaluator = new Evaluator();

        evaluator.evaluate(auction);

        final List<Bid> biggestBids = evaluator.getThreeBiggestBids();
        assertEquals(2, biggestBids.size());
        assertEquals(300, biggestBids.get(0).getValue());
        assertEquals(200, biggestBids.get(1).getValue());
    }

    @Test
    public void shouldGetThreeBiggestBidsFromAuctionWithNoBids() {

        Auction auction = new Auction("product 1");

        Evaluator evaluator = new Evaluator();

        evaluator.evaluate(auction);

        final List<Bid> biggestBids = evaluator.getThreeBiggestBids();
        assertEquals(0, biggestBids.size());
    }

}
