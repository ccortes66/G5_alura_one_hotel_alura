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

        <p class="text-capitalize mt-2">
            <i class="bi-person-circle h2 "></i>
            @if(user != null)
                 ${user.getNombre()}
            @else
                 User
            @endif
        </p>
    </div>
<hr/>

