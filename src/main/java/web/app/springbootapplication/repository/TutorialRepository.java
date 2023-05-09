package web.app.springbootapplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.app.springbootapplication.entity.Tutorial;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByTitleContaining(String title);

    List<Tutorial> findByPublished(boolean published);

    Tutorial findByDescription(String description);


    @Query("SELECT t FROM Tutorial t")
    List<Tutorial> findAllFromTutorial();

    @Query("SELECT t FROM Tutorial t WHERE t.published=?1")
    List<Tutorial> findTutorialByPublished(boolean published);

    Page<Tutorial> findByPublished(boolean published, Pageable pageable);

    Page<Tutorial> findByTitleContaining(String title, Pageable pageable);

    List<Tutorial> findByTitleContainingIgnoreCaseAndPublished(String title, boolean published);

    @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%')) AND t.published=:published")
    List<Tutorial> findByTitleContainingCaseInsensitiveAndPublished(String title,boolean published);


}
