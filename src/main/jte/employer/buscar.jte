@import com.alura.hotelalura.ssr.dto.ListarBusquedaEmpleado

@param ListarBusquedaEmpleado listarBusquedaEmpleado
<!doctype html>
<html lang="en">
@template.partes.head()
<body>

    
    <section class="container-fluid">
         <div class="row">
             @template.partes.siderbarEmpleado()

             <div class="col-sm p-3 min-vh-100">
                 <!-- content -->
                 @template.partes.titulos(listarBusquedaEmpleado.usuario(),
                                          "Buscar Reservación",
                                           "bi-search fs-1")
                 <form action="/busqueda/reservacion" method="get">
                     <label for="exampleDataList" class="form-label">Buscar</label>
                     <input class="form-control" list="datalistOptions" name="busqueda" id="campoBusqueda" placeholder="Type to search...">
                 </form>

                    <table class="table table-hover mt-3">
                     <thead >
                         <tr>
                             <th scope="col">Reserva</th>
                             <th scope="col">CheckIn</th>
                             <th scope="col">CheckOut</th>
                             <th scope="col">Categoria</th>
                             <th scope="col">Método de Pago</th>
                             <th scope="col">Habitación</th>
                             <th scope="col">Accion</th>
                         </tr>
                     </thead>
                     <tbody>
                     <tr id="myResult">
                         @if(listarBusquedaEmpleado.infoEmpleados() != null)
                             <td  class="text-capitalize">${listarBusquedaEmpleado.infoEmpleados().reserva()}</td>
                             <td class="text-capitalize">${String.valueOf(listarBusquedaEmpleado.infoEmpleados().checkIn())}</td>
                             <td class="text-capitalize">${String.valueOf(listarBusquedaEmpleado.infoEmpleados().checkOut())}</td>
                             <td class="text-capitalize">${listarBusquedaEmpleado.infoEmpleados().categoria()}</td>
                             <td class="text-capitalize">${listarBusquedaEmpleado.infoEmpleados().metodoPago()}</td>
                             <td class="text-capitalize">${listarBusquedaEmpleado.infoEmpleados().numero()}</td>
                             <td class="text-capitalize"><a id="btnEliminar" class="btn btn-outline-danger btn-block">Eliminar</a></td>

                         @endif
                     </tr>

                     </tbody>
                 </table>

                 @if(listarBusquedaEmpleado.infoEmpleados() == null && !listarBusquedaEmpleado.confirm())
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
        let data = document.querySelector("#btnEliminar");

         data.addEventListener("click",()=>{
         Swal.fire({
            title: '¿Estás seguro?',
            text: "¡Esta acción no se puede deshacer!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Sí',
            cancelButtonText: 'No',
            reverseButtons: false
         }).then((resultado)=>{
            if(resultado.isConfirmed){
               let reservaValor = document.querySelector("#myResult")
               let elementoLista = reservaValor.getElementsByTagName("td");
               window.location.href = "/eliminar/reservacion?busqueda="+elementoLista[0].innerHTML;
            }
          })
      });
    </script>
    @endraw

@template.partes.footer()
</body>
</html>>