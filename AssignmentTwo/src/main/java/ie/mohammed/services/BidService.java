package ie.mohammed.services;

import java.util.List;
import java.util.Optional;

import ie.mohammed.model.Bid;

/**
 * Service for data management in a table "bets"
 */

public interface BidService {
	/**
     * Find all bets
     * @return list of found bets
     */
    List<Bid> findAll();

    /**
     * Find bet by ID
     * @param id
     * @return Optional of found result
     */
    Optional<Bid> findById(int id);

    /**
     * Find bets by auction ID
     * @param id
     * @return list of found bets
     */
    List<Bid> findByAuctionId(Long id);

    /**
     * Find bets by username
     * @param userName
     * @return list of found bets
     */
    List<Bid> findByUserName(String userName);

    /**
     * Save bet to DB
     * @param bet
     * @return saved bet
     */
    Bid save(final Bid bet);

    /**
     * Delete bet by ID
     * @param id
     */
    void deleteById(int id);

}
