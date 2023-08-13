package com.alura.hotelalura.model.type;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class HabitacionTipo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private Long id;

    @Column(length = 100, unique = true)
    @NonNull
    private String nombre;

    @NonNull
    private BigDecimal precioUnitario;

    @NonNull
    private Integer puntoUnitario;

}
