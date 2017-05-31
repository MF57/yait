package edu.agh.yait.persistence.repositories;

import edu.agh.yait.persistence.model.Ticket;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Krzysztof Podsiadło on 26.04.17.
 */
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    Ticket findByHash(String hash);
}
