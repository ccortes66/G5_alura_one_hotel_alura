package com.alura.hotelalura.model;

import com.alura.hotelalura.model.type.MetodoPago;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Reserva
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private Long id;

    @Column(unique = true)
    @Setter(AccessLevel.NONE)
    private String reserva = String.valueOf(UUID.randomUUID());

    @ManyToOne(fetch = FetchType.LAZY,
               cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @NonNull
    private Cliente cliente;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private MetodoPago metodoPago;

    @OneToOne(fetch = FetchType.LAZY,
              cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Habitacion habitacion;

    @NonNull
    private LocalDate checkIn;

    @NonNull
    private LocalDate checkOut;

    private BigDecimal valorReserva;


}
