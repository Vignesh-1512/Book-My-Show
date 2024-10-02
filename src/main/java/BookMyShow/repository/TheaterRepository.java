package BookMyShow.repository;


import BookMyShow.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Integer> {
    Theater getByName(String name);
    Theater getByAddress(String address);
}
