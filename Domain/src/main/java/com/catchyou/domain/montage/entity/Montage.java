package com.catchyou.domain.montage.entity;

import com.catchyou.domain.common.Status;
import com.catchyou.domain.interview.entity.Interview;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Montage")
public class Montage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "montage_id")
    private Long id;    //파일명과 같음

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "interview_id", referencedColumnName = "id")
    private Interview interview;

    @Enumerated(EnumType.STRING)
    private Status selected;    //선택된 몽타주인지

    public void updateSelected(){
        this.selected = Status.Y;
    }
}
