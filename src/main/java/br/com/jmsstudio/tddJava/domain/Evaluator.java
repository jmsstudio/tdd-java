package br.com.jmsstudio.tddJava.domain;

/**
 * Evaluates an auction
 */
public class Evaluator {

    private Double maxBid = Double.NEGATIVE_INFINITY;
    private Double minBid = Double.POSITIVE_INFINITY;

    public void evaluate(Auction auction) {
        auction.getBids().stream().forEach(bid -> {
            if (bid.getValue() > maxBid) {
                maxBid = bid.getValue();
            }
            if (bid.getValue() < minBid) {
                minBid = bid.getValue();
            }
        });
    }

    public Double getAverageBid(Auction auction) {
        return auction.getBids().stream().mapToDouble(Bid::getValue).average().getAsDouble();
    }

    public double getMaxBid() {
        return maxBid;
    }

    public double getMinBid() {
        return minBid;
    }
}
