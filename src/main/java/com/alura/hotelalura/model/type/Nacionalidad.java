package com.alura.hotelalura.model.type;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Nacionalidad
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private Integer id;

    @Column(length = 100, unique = true)
    @NonNull
    private String nombre;


}
