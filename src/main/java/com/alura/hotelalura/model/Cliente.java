package com.alura.hotelalura.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @NonNull
    private Usuario usuario;

    private Integer puntos = 0;

    @Column(columnDefinition = "TINYINT(4) DEFAULT 0")
    private Byte vip;



}
