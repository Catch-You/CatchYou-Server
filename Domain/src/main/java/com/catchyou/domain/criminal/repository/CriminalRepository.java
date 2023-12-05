package com.catchyou.domain.criminal.repository;

import com.catchyou.domain.criminal.entity.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CriminalRepository extends JpaRepository<Criminal, Long> {
    Optional<Criminal> findById(Long id);

    boolean existsById(Long id);
}
