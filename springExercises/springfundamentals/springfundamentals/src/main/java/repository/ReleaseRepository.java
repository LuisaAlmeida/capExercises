package repository;

import org.springframework.data.repository.CrudRepository;
import entity.Application;

public interface ReleaseRepository extends CrudRepository<Application, Long> {

}
