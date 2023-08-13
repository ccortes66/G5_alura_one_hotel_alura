package com.alura.hotelalura.model;

import com.alura.hotelalura.model.type.HabitacionTipo;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Habitacion
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
               cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @NonNull
    private HabitacionTipo tipo;

    @Column(unique = true)
    @NonNull
    private Integer numero;

    private Boolean reservado = false;
}
