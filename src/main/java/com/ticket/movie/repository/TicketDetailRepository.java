package com.ticket.movie.repository;

import com.ticket.movie.model.TicketDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDetailRepository extends JpaRepository<TicketDetails, Integer> {
}
