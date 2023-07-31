package org.zerock.ex1.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb1_name")
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 200, nullable = true)
    private String memoText;
}
