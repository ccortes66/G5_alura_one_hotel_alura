@import com.alura.hotelalura.model.Usuario
@import com.alura.hotelalura.repository.dto.ReservaInfo
@import java.text.DecimalFormat
@import java.util.List

@param Usuario user
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
                 <h2>Mis reservaciones</h2>
                 <hr />
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
                                 <td>${reservaInfo.reserva()}</td>
                                 <td>${String.valueOf(reservaInfo.checkIn())}</td>
                                 <td>${String.valueOf(reservaInfo.checkOut())}</td>
                                 <td>${new DecimalFormat("#,###.00").format(reservaInfo.precio())}</td>
                                 <td>${reservaInfo.categoria()}</td>
                                 <td>${reservaInfo.habitacion().toString()}</td>
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