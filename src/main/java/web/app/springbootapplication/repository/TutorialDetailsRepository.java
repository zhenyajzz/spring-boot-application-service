package web.app.springbootapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.app.springbootapplication.entity.TutorialDetails;

@Repository
public interface TutorialDetailsRepository extends JpaRepository<TutorialDetails,Long> {
}
