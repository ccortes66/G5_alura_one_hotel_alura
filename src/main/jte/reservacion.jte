@import com.alura.hotelalura.model.Usuario
@import com.alura.hotelalura.repository.dto.ReservaInfo
@import com.alura.hotelalura.ssr.dto.ListarCatrgoriaMetodo
@import com.alura.hotelalura.ssr.CookieController
@import java.text.DecimalFormat

@param Usuario user
@param ReservaInfo reservaInfo
@param ListarCatrgoriaMetodo metodo


<!doctype html>
<html lang="en">
@template.partes.head()
<body>

    
    <section class="container-fluid">
         <div class="row">
             @template.partes.siderbar()

             <div class="col-sm p-3 min-vh-100">
                 <!-- content -->
                 @template.partes.titulos(metodo.usuario(),
                                          "Generar Reservación",
                                           "bi-card-list fs-1")

                 <form action="/generar/reservacion" method="post">
                  <div class="row g-3">

                     <div class="col-auto">
                         <label for="checkOut" class="form-label">Check In</label>
                         <input type="date" class="form-control" name="checkIn" required  aria-label="First name">
                     </div>

                     <div class="col-auto">
                         <label for="checkOut" class="form-label">Check Out</label>
                         <input type="date" class="form-control" id="checkOut" name="checkOut" required aria-label="Last name">
                     </div>

                     <div class="col-auto">
                         <label for="categoria" class="form-label">Categoría</label>
                         <select class="form-select" id="categoria" name="categoria" aria-label="Default select example" required>
                             @if(metodo.habitacionTipo() != null)
                                 @for(var categoria: metodo.habitacionTipo())
                                     <option class="text-capitalize" value="${categoria.getNombre()}">${categoria.getNombre()}</option>
                                 @endfor
                             @endif
                         </select>
                     </div>

                     <div class="col-auto">
                         <label for="metodoPago" class="form-label">Método de pago</label>
                         <select class="form-select" id="metodoPago" name="metodoPago" aria-label="Default select example" required>
                             @if(metodo.metodoPago() != null)
                                 @for(var pago: metodo.metodoPago())
                                     <option value="${pago.getNombre()}">${pago.getNombre()}</option>
                                 @endfor
                             @endif
                         </select>
                     </div>

                      <div class="col-auto">

                          <input class="btn btn-outline-primary btn-block " style="margin-top:33%"  type="submit" value="Reservar">
                      </div>

                 </div>
                 </form>
                 @if(metodo.response() != null)

                     @if(metodo.info() == null)
                         <div class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
                             <strong>Error ${metodo.response().status()}</strong> ${metodo.response().Detail()}
                             <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                         </div>
                     @else
                         <div class="alert alert-success alert-dismissible fade show mt-4" role="alert">
                             <h4 class="alert-heading">¡La reserva se ha realizado con éxito!</h4>
                             <p> Esperamos que disfrute de su estancia en el Hotel Alura. Aquí tiene los detalles de su reserva:</p>
                             <p> ID: ${metodo.info().reserva()} </p>
                             <p> Precio (en USD):${new DecimalFormat("#,###.00").format(metodo.info().valor())} </p>
                             <p> Número de habitación: ${metodo.info().habitacion()}</p>
                             <hr>
                             <p class="mb-0">Whenever you need to, be sure to use margin utilities to keep things nice and tidy.</p>
                             <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                         </div>
                     @endif

                 @endif


             </div>

         </div>
   </section>

@template.partes.footer()
</body>
</html>