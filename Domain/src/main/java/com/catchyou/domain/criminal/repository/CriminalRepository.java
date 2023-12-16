package com.catchyou.domain.criminal.repository;

import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CriminalRepository extends JpaRepository<Criminal, Long> {
    Optional<Criminal> findById(Long id);

    boolean existsById(Long id);

    List<Criminal> findAllByUser(User user);

    Optional<Criminal> findByCriminalCode(String criminalCode);

    List<Criminal> findAllByDirector(User director);

    List<Criminal> findAllByStatus(Status status);
}
