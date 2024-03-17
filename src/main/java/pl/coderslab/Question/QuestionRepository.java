package pl.coderslab.Question;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query("select a.questionEntity from AnswerEntity a where a.questionEntity.id = :questionId")
    QuestionEntity findQuestionEntityByQuestionId(@Param("questionId") Long questionId);

    @Override
    Optional<QuestionEntity> findById(Long questionId);
}
