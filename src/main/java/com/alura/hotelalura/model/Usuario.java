package com.alura.hotelalura.model;

import com.alura.hotelalura.model.type.Nacionalidad;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private Long id;

    @Column(length = 30, unique = true,nullable = false)
    @Setter(AccessLevel.NONE)
    @NonNull
    private String dni;

    @Column(length = 50,nullable = false)
    @NonNull
    private String nombre;

    @Column(length = 50, nullable = false)
    @NonNull
    private String apellido;

    @NonNull
    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @ManyToOne
    private Nacionalidad nacionalidad;

    @Column(length = 20)
    @NonNull
    private String telefono;


}
