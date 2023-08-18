@import com.alura.hotelalura.repository.dto.ReservaInfo
@import java.text.DecimalFormat
@import com.alura.hotelalura.repository.dto.ReservaInfoEmpleados
@import com.alura.hotelalura.ssr.dto.ResultadoListaEmpleado

@param ResultadoListaEmpleado resultadoListaEmpleado


<!doctype html>
<html lang="en">
@template.partes.head()
<body>

<section class="container-fluid">
    <div class="row">
        @template.partes.siderbarEmpleado()

        <div class="col-sm p-3 min-vh-100">

            @if(resultadoListaEmpleado.usuario() != null)
                @template.partes.titulos(resultadoListaEmpleado.usuario(),
                                          "reservaciones",
                                          "bi-arrow-repeat fs-1")

            @endif

            <form action="/busqueda" method="get">
                <p class="text-capitalize">buscar</p>
                <div class="input-group">
                    <div class="form-outline">
                        <input type="search" id="form1" name="buscar" class="form-control" />
                    </div>
                    <button type="submit"  class="btn btn-primary">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
            </form>

            @if(resultadoListaEmpleado.lista() != null)

            <table class="table table-hover mt-3">
                <thead >
                <tr>
                    <th scope="col">Reserva</th>
                    <th scope="col">CheckIn</th>
                    <th scope="col">CheckOut</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">Método de Pago</th>
                    <th scope="col">Habitación</th>
                </tr>
                </thead>
                <tbody>
                @if(resultadoListaEmpleado.lista().size() != 0)
                    @for(ReservaInfoEmpleados reservaInfo: resultadoListaEmpleado.lista())
                        <tr>

                            <td class="text-capitalize">${reservaInfo.reserva()}</td>
                            <td class="text-capitalize">${String.valueOf(reservaInfo.checkIn())}</td>
                            <td class="text-capitalize">${String.valueOf(reservaInfo.checkOut())}</td>
                            <td class="text-capitalize">${reservaInfo.categoria()}</td>
                            <td class="text-capitalize">${reservaInfo.metodoPago()}</td>
                            <td class="text-capitalize">${reservaInfo.numero()}</td>

                         </tr>
                    @endfor
                @endif
                </tbody>
            </table>

          @endif

        </div>

    </div>
</section>

@template.partes.footer()
</body>
</html>