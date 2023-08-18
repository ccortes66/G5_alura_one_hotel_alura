@import com.alura.hotelalura.repository.dto.ReservaInfo
@import com.alura.hotelalura.ssr.CookieController
@import java.text.DecimalFormat
@import com.alura.hotelalura.ssr.dto.ListarBusqueda

@param ListarBusqueda busqueda
<!doctype html>
<html lang="en">
@template.partes.head()
<body>

    
    <section class="container-fluid">
         <div class="row">
             @template.partes.siderbar()

             <div class="col-sm p-3 min-vh-100">
                 <!-- content -->
                 @template.partes.titulos(busqueda.usuario(),
                                          "Buscar Reservación",
                                           "bi-search fs-1")
                 <form action="/busqueda" method="get">
                     <label for="exampleDataList" class="form-label">Buscar</label>
                     <input class="form-control" list="datalistOptions" name="busqueda" id="exampleDataList" placeholder="Type to search...">
                         <datalist id="datalistOptions">
                             @if(busqueda.lista() != null)
                               @for(ReservaInfo reservaInfo: busqueda.lista() )
                                   <option value="${reservaInfo.reserva()}"></option>
                               @endfor
                             @endif
                         </datalist>
                </form>
                    <table class="table table-hover mt-3">
                     <thead >
                         <tr>
                             <th scope="col">reserva</th>
                             <th scope="col">checkIn</th>
                             <th scope="col">checkOut</th>
                             <th scope="col">precio</th>
                             <th scope="col">categoria</th>
                             <th scope="col">habitacion</th>
                         </tr>
                     </thead>
                     <tbody>
                     <tr>
                         @if(busqueda.info() != null)
                             <td class="text-capitalize">${busqueda.info().reserva()}</td>
                             <td class="text-capitalize">${String.valueOf(busqueda.info().checkIn())}</td>
                             <td class="text-capitalize">${String.valueOf(busqueda.info().checkOut())}</td>
                             <td class="text-capitalize">${new DecimalFormat("#,###.00").format(busqueda.info().precio())}</td>
                             <td class="text-capitalize">${busqueda.info().categoria()}</td>
                             <td class="text-capitalize">${busqueda.info().habitacion().toString()}</td>
                         @endif
                     </tr>

                     </tbody>
                 </table>

                 @if(busqueda.info() == null && busqueda.lista() == null)
                     <div class="alert alert-info alert-dismissible fade show mt-4" role="alert">
                         <strong>Error 404</strong> No existe registros
                         <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                     </div>
                 @endif
             </div>

         </div>
   </section>

    @raw
    <script>



    </script>
    @endraw

@template.partes.footer()
</body>
</html>>