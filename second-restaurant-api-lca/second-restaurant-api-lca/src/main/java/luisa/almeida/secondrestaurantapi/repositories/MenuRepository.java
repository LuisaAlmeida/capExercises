package luisa.almeida.secondrestaurantapi.repositories;

import luisa.almeida.secondrestaurantapi.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT u FROM Menu u WHERE u.startDate between :startDate and :endDate")
    public List<Menu> findAllBetweenDates(LocalDate startDate, LocalDate endDate);
    public Optional<Menu> findByUuid(String uuid);
    public Optional<Menu> findByDishName(String dishName);
}
