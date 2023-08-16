@import com.alura.hotelalura.model.Usuario
@import com.alura.hotelalura.ssr.CookieController
@import java.time.LocalDate
@import java.time.Period
@import java.util.TreeSet


@param TreeSet<String> mysPaises

<!doctype html>
<html lang="en">
@template.partes.head()
<body>


<section class="container-fluid">
    <div class="row">
        @template.partes.siderbar()

        <div class="col-sm p-3 min-vh-100">
            <!-- content -->
            @template.partes.titulos(CookieController.getIsCliente().getUsuario(),
                                          "Perfil",
                                          "bi-person-circle fs-1")

            <div class="container py-3">

                <div class="row">
                    <div class="col-lg-4">
                        <div class="card mb-4">
                            <div class="card-body text-center">
                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                                     class="rounded-circle img-fluid" style="width: 150px;">
                                <h5 class="my-3">
                                    ${CookieController.getMyUsuario().getNombre()}
                                </h5>
                                @if(CookieController.getIsCliente().getVip() != null)
                                    <p class="text-muted mb-1">VIP Numero ${CookieController.getIsCliente().getVip()}</p>
                                @endif
                                <p class="text-muted mb-4"> Hotel alura puntos ${CookieController.getIsCliente().getPuntos()}</p>

                            </div>
                        </div>

                        <div class="card mb-4 mb-lg-0">
                            <div class="card-body p-0">
                                <ul class="list-group list-group-flush rounded-3">
                                    <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                        <p class="mb-0">Eliminar Cuenta</p>
                                        <a id="btnEliminar" class="btn btn-outline-danger btn-block">Eliminar</a>
                                    </li>

                                </ul>
                            </div>
                        </div>


                    </div>
                    <div class="col-lg-8">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Nombre completo</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
                                            ${CookieController.getMyUsuario().getNombre()}
                                            ${CookieController.getMyUsuario().getApellido()}
                                        </p>
                                    </div>
                                </div>
                                <hr>
                                
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Edada</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
                                            ${Period.between(CookieController.getMyUsuario().getFechaNacimiento(),LocalDate.now()).getYears()}
                                        </p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Teléfono</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
                                            ${CookieController.getIsCliente().getUsuario().getTelefono()}
                                        </p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Nacionalidad</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0"></p>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="col-lg-12">
                            <div class="card mb-4">
                                <div class="card-body">
                                   <form action="/perfil" method="post">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Teléfono</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">
                                                <input type="tel" id="telefono" name="telefono" required class="form-control" value="${CookieController.getIsCliente().getUsuario().getTelefono()}" />
                                            </p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Nacionalidad</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">
                                                <select class="form-select" id="metodoPago" name="metodoPago" aria-label="Default select example" required>
                                                    @if(!mysPaises.isEmpty())
                                                        @for(String valor: mysPaises)
                                                            <option value="${valor}">${valor}</option>
                                                        @endfor
                                                    @endif
                                                </select>
                                            </p>
                                        </div>
                                    </div>
                                    <hr>

                                       <div class="row">

                                           <div class="col-sm-3">
                                               <input type="submit" class="btn btn-outline-secondary btn-block" value="Editar">
                                           </div>
                                       </div>

                                   </form>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>

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
           window.location.href = "/eliminar";
        }
      })
  });
</script>

@endraw

@template.partes.footer()
</body>
</html>