package pl.coderslab.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    @Query("select a from AnswerEntity a where a.questionEntity.id = :questionId")
    List<AnswerEntity> findAllByQuestionId(@Param("questionId") Long questionId);
}
