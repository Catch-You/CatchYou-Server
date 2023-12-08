package com.catchyou.domain.montage.adaptor;

import com.catchyou.core.annotation.Adaptor;
import com.catchyou.core.exception.BaseException;
import com.catchyou.domain.common.Status;
import com.catchyou.domain.interview.entity.Interview;
import com.catchyou.domain.montage.entity.Montage;
import com.catchyou.domain.montage.repository.MontageRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.catchyou.domain.montage.exception.MontageErrorCode.NOT_FOUND_MONTAGE;
import static com.catchyou.domain.montage.exception.MontageErrorCode.NOT_FOUND_SELECTED_MONTAGE;

@Adaptor
@RequiredArgsConstructor
public class MontageAdaptor {
    private final MontageRepository montageRepository;

    public Montage save(Montage montage) {
        return montageRepository.save(montage);
    }

    public Montage findById(Long id){
        return montageRepository.findById(id)
                .orElseThrow(() -> new BaseException(NOT_FOUND_MONTAGE));
    }

    public List<Montage> findByInterview(Interview interview){
        return montageRepository.findAllByInterview(interview);
    }

    public Montage findSelectedMontage(Interview interview){
        return montageRepository.findByInterviewAndSelected(interview, Status.Y)
                .orElseThrow(() -> new BaseException(NOT_FOUND_SELECTED_MONTAGE));
    }
}
