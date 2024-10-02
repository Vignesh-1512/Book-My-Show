package BookMyShow.repository;

import BookMyShow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User getByEmailId(String emailId);
    User getByMobile(Long mobile);
}
