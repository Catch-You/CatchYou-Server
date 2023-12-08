package com.catchyou.domain.interview.entity;

import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.entity.Criminal;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private Long id;    //인터뷰 값

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criminal_id")
    private Criminal criminal;

    @Enumerated(EnumType.STRING)
    private Status selectStatus;    //해당 인터뷰에 대해 선택된 몽타주 있는지

    @Enumerated(EnumType.STRING)
    private Status selected;    //선택된 인터뷰인지

    public void updateSelectStatus(){
        this.selectStatus = Status.Y;
    }

    public void updateSelected(){
        this.selected = Status.Y;
    }
}
