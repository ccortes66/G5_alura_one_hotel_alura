@import com.alura.hotelalura.model.Usuario
@import com.alura.hotelalura.model.type.HabitacionTipo
@import com.alura.hotelalura.repository.dto.ReservaInfo
@import com.alura.hotelalura.ssr.dto.RtsReservacionEmpresa
@import com.alura.hotelalura.ssr.CookieController
@import java.text.DecimalFormat

@param RtsReservacionEmpresa respuesta


<!doctype html>
<html lang="en">
@template.partes.head()
<body>

    
    <section class="container-fluid">
         <div class="row">
             @template.partes.siderbarEmpleado()

             <div class="col-sm p-3 min-vh-100">
                 <!-- content -->
                 @template.partes.titulos(respuesta.usuario(),
                                          "Generar método de pago",
                                          "bi bi-wallet fs-1")

                 <form action="/administar/pagos" method="post" class="row g-4" >
                     <h4 class="text-capitalize">Generar método de pago</h4>
                     <div class="col-auto">
                         <label for="staticEmail2">Metodo</label>
                         <input type="text" required class="form-control" name="nombre" pattern="[A-Za-z]+">
                     </div>
                     <div class="col-auto">
                         <button type="submit" class="btn btn-outline-success mt-4">Agregar</button>
                     </div>
                 </form>
                 @if(respuesta.confirm()[0])
                     <div class="alert alert-info alert-dismissible fade show mt-4" role="alert">
                         <strong>¡Método de pago generado!</strong> Se registran los datos en el sistema
                         <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                     </div>
                 @endif



             </div>

         </div>
   </section>

@template.partes.footer()
</body>
</html>