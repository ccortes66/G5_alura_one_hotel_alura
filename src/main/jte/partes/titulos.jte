@import com.alura.hotelalura.model.Usuario

@param Usuario user
@param String titulo
@param String icon

    <div class="d-flex justify-content-between ">

        <h2 class="text-capitalize">
            @if(icon != null)
                <i class="${icon}"></i>
            @endif
            ${titulo}
        </h2>

        <p class="text-capitalize mt-3">
            Bienvenido ${user.getNombre()}
        </p>
    </div>
<hr/>

