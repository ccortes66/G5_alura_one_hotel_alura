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
                                          "Generar Habitaciones",
                                          "bi-card-list fs-1")

                 <form class="row g-3">
                     <h4 class="text-capitalize">Generar Tipo Habitacion </h4>
                     <div class="col-auto">
                         <label for="staticEmail2">Categoria</label>
                         <input type="text" required class="form-control" name="nombre" pattern="[A-Za-z]+">
                     </div>
                     <div class="col-auto">
                         <label for="inputPassword2" >Precio por día</label>
                         <input type="text" required class="form-control" id="inputPassword2" name="precioUnitario" pattern="[0-9]*\.?[0-9]+">
                     </div>
                     <div class="col-auto">
                         <label for="inputPassword2" >Puntos</label>
                         <input type="number" required class="form-control" id="inputPassword2" name="puntoUnitario">
                     </div>
                     <div class="col-auto">
                         <button type="submit" class="btn btn-outline-success mt-4">Agregar</button>
                     </div>
                 </form>
                 <hr>

                 <form class="row g-2 my-2">
                     <h4 class="text-capitalize"> Generar Habitacion </h4>
                     <div class="col-auto">
                         <label for="staticEmail2">Numero</label>
                         <input type="number" required class="form-control" id="staticEmail2" >
                     </div>
                     <div class="col-auto">
                         <label for="inputPassword2" >Habitacion tipo</label>
                         <select class="form-select" id="categoria" name="categoria" aria-label="Default select example" required>
                             @for(HabitacionTipo categoria : respuesta.list() )
                                 <option class="text-capitalize" value="${categoria.getNombre()}">${categoria.getNombre()}</option>
                             @endfor
                         </select>
                     </div>
                     <div class="col-auto">
                         <button type="submit" class="btn btn-outline-success mt-4">Agregar</button>
                     </div>
                 </form>
                 <hr>


                 <form action="/busqueda" class="row g-2 my-2" method="get">
                     <h4 class="text-capitalize">Eliminar Reserva</h4>
                     <div class="input-group col-sm-9">
                         <div class="form-outline">
                             <input type="search" id="form1" name="buscar" class="form-control" />
                         </div>
                         <button type="submit"  class="btn btn-primary">
                             <i class="fas fa-search"></i>
                         </button>
                     </div>
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
                     <!--
                     <tr>
                         @if(busqueda.info() != null)
                             <td class="text-capitalize">${busqueda.info().reserva()}</td>
                             <td class="text-capitalize">${String.valueOf(busqueda.info().checkIn())}</td>
                             <td class="text-capitalize">${String.valueOf(busqueda.info().checkOut())}</td>
                             <td class="text-capitalize">${new DecimalFormat("#,###.00").format(busqueda.info().precio())}</td>
                             <td class="text-capitalize">${busqueda.info().categoria()}</td>
                             <td class="text-capitalize">${busqueda.info().habitacion().toString()}</td>
                             <td class="text-capitalize"><a id="btnEliminar" class="btn btn-outline-danger btn-block">Eliminar</a></td>
                         @endif
                     </tr>
                     --->
                     </tbody>
                 </table>

             </div>

         </div>
   </section>

@template.partes.footer()
</body>
</html>