package com.catchyou.domain.criminal.adaptor;

import com.catchyou.core.annotation.Adaptor;
import com.catchyou.core.exception.BaseException;
import com.catchyou.domain.criminal.entity.Criminal;
import com.catchyou.domain.criminal.repository.CriminalRepository;
import com.catchyou.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.catchyou.domain.criminal.exception.CriminalErrorCode.NOT_FOUND_CRIMINAL;
import static com.catchyou.domain.criminal.exception.CriminalErrorCode.NOT_FOUND_CRIMINAL_CODE;

@Adaptor
@RequiredArgsConstructor
public class CriminalAdaptor {
    private final CriminalRepository criminalRepository;

    public Criminal save(Criminal criminal) {
        return criminalRepository.save(criminal);
    }

    public Criminal findById(Long id) {
        return criminalRepository.findById(id).orElseThrow(() -> new BaseException(NOT_FOUND_CRIMINAL));
    }

    public List<Criminal> findByUser(User user) {
        return criminalRepository.findAllByUser(user);
    }

    public List<Criminal> findByDirector(User director){
        return criminalRepository.findAllByDirector(director);
    }

    public Criminal findByCriminalCode(String criminalCode) {
        return criminalRepository.findByCriminalCode(criminalCode)
                .orElseThrow(() -> new BaseException(NOT_FOUND_CRIMINAL_CODE));
    }

}
