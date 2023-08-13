package com.alura.hotelalura.model;

import com.alura.hotelalura.model.type.EmpleadoTipo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Empleado
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @NonNull
    private Usuario usuario;

    @Column(length = 10)
    private String permisos;

    @ManyToOne
    private EmpleadoTipo tipo;





}
