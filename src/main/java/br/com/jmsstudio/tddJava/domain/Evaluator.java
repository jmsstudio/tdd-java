package br.com.jmsstudio.tddJava.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Evaluates an auction
 */
public class Evaluator {

    private Double maxBid = Double.NEGATIVE_INFINITY;
    private Double minBid = Double.POSITIVE_INFINITY;
    private List<Bid> biggestBids = new ArrayList<>();

    public void evaluate(Auction auction) {
        auction.getBids().stream().forEach(bid -> {
            if (bid.getValue() > maxBid) {
                maxBid = bid.getValue();
            }
            if (bid.getValue() < minBid) {
                minBid = bid.getValue();
            }
        });

        calculateMaximumBids(auction);
    }

    private void calculateMaximumBids(Auction auction) {
        this.biggestBids = auction.getBids().stream()
                        .sorted(Comparator.comparingDouble(Bid::getValue).reversed())
                        .limit(3)
                        .collect(Collectors.toList());
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

    public List<Bid> getThreeBiggestBids() {
        return biggestBids;
    }
}
