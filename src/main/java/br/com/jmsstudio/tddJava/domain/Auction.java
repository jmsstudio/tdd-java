package br.com.jmsstudio.tddJava.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Auction {

    private static final long MAX_BIDS_PER_USER = 5;
    private String description;
	private List<Bid> bids;
	
	public Auction(String description) {
		this.description = description;
		this.bids = new ArrayList<Bid>();
	}
	
	public void bid(Bid bid) {
        if (canUserBid(bid.getUser())) {
            bids.add(bid);
        }
    }

    private Bid getLastBidFromUser(User user) {
        long count = getBids().stream().filter(bid -> bid.getUser().equals(user)).count();

        Bid lastBid = null;
        if (count > 0) {
            lastBid = getBids().stream().filter(bid -> bid.getUser().equals(user))
                    .skip(count - 1).findFirst().orElse(null);
        }

        return lastBid;
    }

    private boolean canUserBid(User user) {
        final Integer totalBids = getBids().size();
        final Bid lastBid = totalBids > 0 ? getBids().get(totalBids -1) : null;

        boolean canBid = true;

        if (lastBid != null && lastBid.getUser().equals(user)) {
            canBid = false;
        } else {
            final long totalBidsFromUser = getBids().stream()
                    .filter(bid -> bid.getUser().equals(user))
                    .count();
            
            if (totalBidsFromUser >= MAX_BIDS_PER_USER) {
                canBid = false;
            }
        }

        return canBid;
    }

	public String getDescription() {
		return description;
	}

	public List<Bid> getBids() {
		return Collections.unmodifiableList(bids);
	}

    public void doubleBid(User user) {
        Bid lastBidFromUser = getLastBidFromUser(user);
        if (lastBidFromUser != null) {
            this.bid(lastBidFromUser.doubleValue());
        }
    }
}
