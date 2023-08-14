@import com.alura.hotelalura.model.Usuario
@param Usuario user

<div class="col-sm p-3 min-vh-100">
<nav class="navbar navbar-expand-lg bg-body-tertiary container-fluid border-body">
    <div class="container-fluid ">
        <a class="navbar-brand" href="#">Hotel Alura</a>
        <button class="navbar-toggler" type="button"  data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>

    <div class="collapse navbar-collapse d-flex flex-column-reverse" id="navbarSupportedContent">

        <div class="d-flex " role="search">
            <p class="me-2">Bienvenido</p>
            <p class="text-capitalize me-3">${user.getNombre()} </p>
        </div>
    </div>
</nav>
</div>