package repository;

import org.springframework.data.repository.CrudRepository;
import entity.Application;

public interface ApplicationRepository extends CrudRepository<Application, Long>{

}
