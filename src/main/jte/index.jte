@import com.alura.hotelalura.model.Usuario
@import com.alura.hotelalura.repository.dto.ReservaInfo
@import java.text.DecimalFormat
@import java.util.List
@import com.alura.hotelalura.ssr.CookieController

@param List<ReservaInfo> infoList

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
                                          "Mis reservaciones",
                                          "bi-arrow-repeat fs-1")

                 <table class="table">
                     <thead class="thead-dark">
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
                     @if(infoList != null)
                         @for(ReservaInfo reservaInfo: infoList)
                             <tr>
                                 <td class="text-capitalize">${reservaInfo.reserva()}</td>
                                 <td class="text-capitalize">${String.valueOf(reservaInfo.checkIn())}</td>
                                 <td class="text-capitalize">${String.valueOf(reservaInfo.checkOut())}</td>
                                 <td class="text-capitalize">${new DecimalFormat("#,###.00").format(reservaInfo.precio())}</td>
                                 <td class="text-capitalize">${reservaInfo.categoria()}</td>
                                 <td class="text-capitalize">${reservaInfo.habitacion().toString()}</td>
                             </tr>
                         @endfor
                     @endif
                     </tbody>
                 </table>
             </div>

         </div>
   </section>

@template.partes.footer()
</body>
</html>