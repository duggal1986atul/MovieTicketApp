package com.ticket.movie.repository;

import com.ticket.movie.model.TicketDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDetailRepository extends JpaRepository<TicketDetail, Integer> {
}
