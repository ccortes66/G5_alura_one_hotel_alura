package com.alura.hotelalura.model.type;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MetodoPago
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private Long id;

    @Column(length = 100, unique = true)
    @NonNull
    private String nombre;


}
