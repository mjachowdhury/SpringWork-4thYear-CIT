package ie.mohammed.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.mohammed.model.Auction;

public interface AuctionDao extends JpaRepository<Auction, Integer> {

	/**
     * Finds all finished auctions
     * @param finished
     * @return
     */
    List<Auction> findByFinished(boolean finished);

    List<Auction> findByJobCategoryName(String categoryName);

    List<Auction> findByJobNameContains(String searchTag);

    List<Auction> findByOwnerUserName(String userName);

    List<Auction> findByEndTimeIsLessThan(Date date);
}
