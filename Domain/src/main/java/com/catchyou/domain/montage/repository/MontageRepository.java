package com.catchyou.domain.montage.repository;

import com.catchyou.domain.common.Status;
import com.catchyou.domain.interview.entity.Interview;
import com.catchyou.domain.montage.entity.Montage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MontageRepository extends JpaRepository<Montage, Long> {
    Optional<Montage> findById(Long id);
    Optional<Montage> findByInterviewAndSelected(Interview interview, Status selected);

    List<Montage> findAllByInterview(Interview interview);
}
