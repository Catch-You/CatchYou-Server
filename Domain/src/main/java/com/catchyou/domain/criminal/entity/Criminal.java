package com.catchyou.domain.criminal.entity;

import com.catchyou.domain.common.BaseTimeEntity;
import com.catchyou.domain.common.Status;
import com.catchyou.domain.criminal.enums.CrimeType;
import com.catchyou.domain.criminal.enums.Region;
import com.catchyou.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Criminal")
public class Criminal extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "criminal_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;  //작성자(경찰)

    @Column(name = "title", nullable = false)
    private String title;   //제목

    private String summary; //사건 개요

    private String description; //인상착의

    private String criminalCode;    //사건 코드

    @Enumerated(EnumType.STRING)
    private Region region;  //사건 발생 지역

    @Enumerated(EnumType.STRING)
    private CrimeType crimeType;    //사건 종류

    @Enumerated(EnumType.STRING)
    private Status status;  //Y면 공개, N이면 비공개

    @Enumerated(EnumType.STRING)
    private Status selectStatus;    //몽타주 작성 완료 : Y면 완료, N이면 미완료

    //여기에 몽타주 객체 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private User director;  //작성자(경찰)

    public void updateCriminal(String title, String summary,
                               String description, Region region, CrimeType crimeType,
                               Status status) {
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.region = region;
        this.crimeType = crimeType;
        this.status = status;
    }

    public void updateCriminalCode(String criminalCode){
        this.criminalCode = criminalCode;
    }

    private void updateSelectStatus(){   //완료 상태를 Y로 업데이트
        this.selectStatus = Status.Y;
    }

    public void updateDirector(User director){
        this.director = director;
    }


}
