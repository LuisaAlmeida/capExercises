package repository;

import org.springframework.data.repository.CrudRepository;
import entity.Application;

public interface TicketRepository extends CrudRepository<Application, Long> {

}