
@import com.alura.hotelalura.repository.dto.ReservaInfo
@import com.alura.hotelalura.ssr.CookieController

@param ReservaInfo infoList

<!doctype html>
<html lang="en">
@template.partes.head()
<body>

    
    <section class="container-fluid">
         <div class="row">
             @template.partes.siderbar()

             <div class="col-sm p-3 min-vh-100">
                 <!-- content -->
                 @template.partes.titulos(CookieController.getMyUsuario(),
                                          "Buscar Reservación",
                                          "bi-search fs-1")


             </div>

         </div>
   </section>

@template.partes.footer()
</body>
</html>