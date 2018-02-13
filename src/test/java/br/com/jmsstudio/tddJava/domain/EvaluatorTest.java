package br.com.jmsstudio.tddJava.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Evaluator")
class EvaluatorTest {

    private Evaluator evaluator;

    @BeforeEach
    void setup() {
        evaluator = new Evaluator();
    }

    @Test
    void shouldEvaluateAuctionWithOneBid() {
        Auction auction = new Auction("test");
        auction.bid(new Bid(new User("john"), 200));

        evaluator.evaluate(auction);

        assertEquals(200, evaluator.getMaxBid(), 0.001);
        assertEquals(200, evaluator.getMinBid(), 0.001);
    }

    @Test
    void shouldEvaluateAuctionWithBidsInDescendingOrder() {
        User john = new User("john");
        User mary = new User("mary");
        User joseph = new User("joseph");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(john, 500));
        auction.bid(new Bid(joseph, 200));
        auction.bid(new Bid(mary, 100));

        evaluator.evaluate(auction);

        assertEquals(500, evaluator.getMaxBid(), 0.001);
        assertEquals(100, evaluator.getMinBid(), 0.001);
    }

    @Test
    void shouldEvaluateAuctionWithBidsInAscendingOrder() {
        User john = new User("john");
        User mary = new User("mary");
        User joseph = new User("joseph");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(mary, 100));
        auction.bid(new Bid(joseph, 200));
        auction.bid(new Bid(john, 500));

        evaluator.evaluate(auction);

        assertEquals(500, evaluator.getMaxBid(), 0.001);
        assertEquals(100, evaluator.getMinBid(), 0.001);
    }

    @Test
    void shouldEvaluateAuctionWithBidsInRandomOrder() {
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

        evaluator.evaluate(auction);

        assertEquals(500, evaluator.getMaxBid(), 0.001);
        assertEquals(100, evaluator.getMinBid(), 0.001);
    }

    @Test
    void shouldCalculateTheAverageOfBids() {
        User john = new User("john");
        User mary = new User("mary");
        User joseph = new User("joseph");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(john, 500));
        auction.bid(new Bid(mary, 100));
        auction.bid(new Bid(joseph, 200));

        assertEquals((500 + 100 + 200) / 3.0, evaluator.getAverageBid(auction), 0.01);
    }

    @Test
    void shouldGetThreeBiggestBidsFromFiveBids() {
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

        evaluator.evaluate(auction);

        final List<Bid> biggestBids = evaluator.getThreeBiggestBids();
        assertEquals(3, biggestBids.size());
        assertEquals(500, biggestBids.get(0).getValue());
        assertEquals(300, biggestBids.get(1).getValue());
        assertEquals(200, biggestBids.get(2).getValue());
    }

    @Test
    void shouldGetThreeBiggestBidsFromTwoBids() {
        User john = new User("john");
        User mary = new User("mary");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(mary, 200));
        auction.bid(new Bid(john, 300));

        evaluator.evaluate(auction);

        final List<Bid> biggestBids = evaluator.getThreeBiggestBids();
        assertEquals(2, biggestBids.size());
        assertEquals(300, biggestBids.get(0).getValue());
        assertEquals(200, biggestBids.get(1).getValue());
    }

    @Test
    void shouldGetThreeBiggestBidsFromAuctionWithNoBids() {

        Auction auction = new Auction("product 1");

        assertThrows(RuntimeException.class, () -> evaluator.evaluate(auction));
    }

    @Test
    void shouldFailWhenTryingToBidWithNegativeValue() {
        User john = new User("john");

        Auction auction = new Auction("product 1");

        assertThrows(IllegalArgumentException.class, () -> auction.bid(new Bid(john, -1)));
    }

    @Test
    void shouldFailWhenTryingToBidWithValueZero() {
        User john = new User("john");

        Auction auction = new Auction("product 1");

        assertThrows(IllegalArgumentException.class, () -> auction.bid(new Bid(john, 0)));
    }

    @Test
    void shouldNotAcceptTwoBidsInSequenceFromTheSameUser() {
        User john = new User("john");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(john, 300));
        auction.bid(new Bid(john, 500));

        assertEquals(1, auction.getBids().size());
        assertEquals(300, auction.getBids().get(0).getValue());
    }

    @Test
    @DisplayName("Should not accept more than 5 bids from a user")
    void shouldNotAcceptMoreThanFiveBidsFromAUser() {
        User john = new User("john");
        User mary = new User("mary");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(john, 300));
        auction.bid(new Bid(mary, 350));
        auction.bid(new Bid(john, 400));
        auction.bid(new Bid(mary, 450));
        auction.bid(new Bid(john, 500));
        auction.bid(new Bid(mary, 550));
        auction.bid(new Bid(john, 600));
        auction.bid(new Bid(mary, 650));
        auction.bid(new Bid(john, 700));
        auction.bid(new Bid(mary, 750));
        auction.bid(new Bid(john, 800));

        evaluator.evaluate(auction);

        assertEquals(10, auction.getBids().size());
        assertEquals(750, evaluator.getMaxBid());

    }

    @Test
    void shouldAllowUsersToDuplicateBids() {
        User john = new User("john");
        User mary = new User("mary");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(john, 300));
        auction.bid(new Bid(mary, 350));
        auction.doubleBid(john);

        evaluator.evaluate(auction);

        assertEquals(3, auction.getBids().size());
        assertEquals(600, evaluator.getMaxBid());
    }

    @Test
    void shouldAllowUsersToDuplicateBidsExceptWhenThereAreNoBids() {
        User john = new User("john");
        User mary = new User("mary");

        Auction auction = new Auction("product 1");
        auction.doubleBid(john);

        assertEquals(0, auction.getBids().size());
    }

    @Test
    void shouldAllowUsersToDuplicateBidsButTheUserCannotMakeTwoBidsInSequence() {
        User john = new User("john");
        User mary = new User("mary");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(john, 300));
        auction.bid(new Bid(mary, 350));
        auction.doubleBid(mary);

        evaluator.evaluate(auction);

        assertEquals(2, auction.getBids().size());
        assertEquals(350, evaluator.getMaxBid());
    }

    @Test
    void shouldAllowUsersToDuplicateBidsButTheUserCannotMakeMoreThanFiveBids() {
        User john = new User("john");
        User mary = new User("mary");

        Auction auction = new Auction("product 1");
        auction.bid(new Bid(john, 300));
        auction.bid(new Bid(mary, 350));
        auction.doubleBid(john);
        auction.doubleBid(mary);
        auction.doubleBid(john);
        auction.doubleBid(mary);
        auction.doubleBid(john);
        auction.doubleBid(mary);
        auction.doubleBid(john);//4800
        auction.doubleBid(mary);//5600
        auction.doubleBid(john);

        evaluator.evaluate(auction);

        assertEquals(10, auction.getBids().size());
        assertEquals(5600, evaluator.getMaxBid());

    }


}
