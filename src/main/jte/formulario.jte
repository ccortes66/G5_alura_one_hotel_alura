@import com.alura.hotelalura.ssr.error.ErrorResponse
@import com.alura.hotelalura.ssr.CookieController

@param ErrorResponse response


<!doctype html>
<html lang="en">
@template.partes.headFormulario()
<body>
<section class="vh-100" style="background-color: #eee;">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Registro</p>

                                <form class="mx-1 mx-md-4" method="post" action="/registrar">
                                    @if(response != null)
                                        <div class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
                                            <strong>Error ${response.status()} </strong> ${response.Detail()}
                                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                        </div>
                                    @endif

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fa-solid fa-id-card fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="dni" name="dni" required class="form-control" />
                                            <label class="form-label" for="dni">Dni</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="nombre" name="nombre" required class="form-control" />
                                            <label class="form-label" for="nombre">Nombre</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="apellido" name="apellido" required class="form-control" />
                                            <label class="form-label" for="apellido">Apellido</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required class="form-control" />
                                            <label class="form-label" for="fechaNacimiento">Fecha Nacimiento</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <select class="form-select" id="categoria" name="nacionalidad" aria-label="Default select example" required>
                                                @for(String pais : CookieController.getMysPaises())
                                                    <option class="text-capitalize" value="${pais}">${pais}</option>
                                                @endfor
                                            </select>
                                            <label class="form-label" for="categoria">Nacionalidad</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="username" name="username" required class="form-control" />
                                            <label class="form-label" for="username">username</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" id="password" name="password" required class="form-control" />
                                            <label class="form-label" for="password">Contraseña</label>
                                        </div>
                                    </div>



                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" id="password2" name="password2" required class="form-control" />
                                            <label class="form-label" for="password2">Confirmar Contraseña</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-key-non fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline mb-4  h-captcha" data-sitekey="fd57c86b-3201-4b83-af0a-12f67c8fe5a1"></div>
                                    </div>

                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <input type="submit" class="btn btn-outline-success btn-lg" value="Registrar">
                                    </div>

                                </form>

                            </div>
                            <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                     class="img-fluid" alt="Sample image">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

@template.partes.footer()

</body>
</html>