@import com.alura.hotelalura.model.Usuario
@import com.alura.hotelalura.ssr.dto.ConsultaInfoReservacion
@import com.alura.hotelalura.ssr.CookieController
@import java.text.DecimalFormat

@param Usuario user
@param ConsultaInfoReservacion reservacion


<!doctype html>
<html lang="en">
@template.partes.head()
<body>

    
    <section class="container-fluid">
         <div class="row">
             @template.partes.siderbar()

             <div class="col-sm p-3 min-vh-100">
                 <!-- content -->
                 @template.partes.titulos(reservacion.usuario(),
                                          "Consultar Precio",
                                           "bi-cash-stack fs-1")

                 <form action="/consultar/reservacion" method="post">
                  <div class="row g-3">

                     <div class="col-auto">
                         <label for="checkOut" class="form-label">Check In</label>
                         <input type="date" class="form-control" id="checkIn" name="checkIn" required  aria-label="First name">
                     </div>

                     <div class="col-auto">
                         <label for="checkOut" class="form-label">Check Out</label>
                         <input type="date" class="form-control" id="checkOut" name="checkOut" required aria-label="Last name">
                     </div>

                     <div class="col-auto">
                         <label for="categoria" class="form-label">Categoría</label>
                         <select class="form-select" id="categoria" name="categoria" aria-label="Default select example" required>
                             @if(reservacion.habitacionTipo() != null)
                                 @for(var categoria: reservacion.habitacionTipo())
                                     <option class="text-capitalize" value="${categoria.getNombre()}">${categoria.getNombre()}</option>
                                 @endfor
                             @endif
                         </select>
                     </div>


                      <div class="col-auto">
                          <input class="btn btn-outline-success btn-block " style="margin-top:33%"  type="submit" value="Consultar">
                      </div>

                 </div>
                 </form>
                 @if(reservacion.response() != null)

                     <div class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
                             <strong>Error ${reservacion.response().status()}</strong> ${reservacion.response().Detail()}
                             <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                     </div>

                 @elseif(reservacion.reserva() != null)

                     <div class="card mb-3 mt-4" style="max-width: 540px;">
                         <div class="row g-0">
                             <div class="col-md-4">
                                 <img src="https://i.ibb.co/vVhTHRy/logo.png" class="img-fluid rounded-start" alt="...">
                             </div>
                             <div class="col-md-8">
                                 <div class="card-body">
                                     <h5 class="card-title">Consultar Precio</h5>
                                     <p class="card-text">Cálculo del costo según la duración de la estadía .</p>

                                    <ul class="list-unstyled">
                                        <li id="myInicio" class="card-text">Check In:${String.valueOf(reservacion.reserva().checkIn())}</li>
                                        <li id="myFin" class="card-text">Check Out:${String.valueOf(reservacion.reserva().checkOut())} </li>
                                        <li id="myCategoria" class="card-text">Categoria: ${reservacion.reserva().tipo()}</li>
                                        <li class=" card-text">Precio(USD) $${reservacion.reserva().metodoPago()}</li>
                                     </ul>

                                     <a href="/generar/reservacion" class="card-link">reservar ahora</a>
                                 </div>
                             </div>
                         </div>
                     </div>
                 @endif

             </div>

         </div>
   </section>


@template.partes.footer()
</body>
</html>