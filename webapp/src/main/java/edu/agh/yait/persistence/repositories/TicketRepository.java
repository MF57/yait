package edu.agh.yait.persistence.repositories;

import edu.agh.yait.persistence.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Krzysztof Podsiadło on 26.04.17.
 */
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findByHash(String hash);
}
