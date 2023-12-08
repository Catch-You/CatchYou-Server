package com.catchyou.domain.interview.repository;

import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.interview.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    Optional<Interview> findById(Long id);

    List<Interview> findAllByCriminal(Criminal criminal);

    Optional<Interview> findByCriminalAndSelected(Criminal criminal, Status selected);
}
