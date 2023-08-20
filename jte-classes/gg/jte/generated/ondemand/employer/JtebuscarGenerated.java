package gg.jte.generated.ondemand.employer;
import com.alura.hotelalura.ssr.dto.ListarBusquedaEmpleado;
public final class JtebuscarGenerated {
	public static final String JTE_NAME = "employer/buscar.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,5,5,5,11,11,14,15,17,37,37,38,38,38,39,39,39,40,40,40,41,41,41,42,42,42,43,43,43,46,46,52,52,57,57,63,85,87,87,89};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ListarBusquedaEmpleado listarBusquedaEmpleado) {
		jteOutput.writeContent("<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n\n    \n    <section class=\"container-fluid\">\n         <div class=\"row\">\n             ");
		gg.jte.generated.ondemand.partes.JtesiderbarEmpleadoGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n             <div class=\"col-sm p-3 min-vh-100\">\n                 ");
		jteOutput.writeContent("\n                 ");
		gg.jte.generated.ondemand.partes.JtetitulosGenerated.render(jteOutput, jteHtmlInterceptor, listarBusquedaEmpleado.usuario(), "Buscar Reservación", "bi-search fs-1");
		jteOutput.writeContent("\n                 <form action=\"/busqueda/reservacion\" method=\"get\">\n                     <label for=\"exampleDataList\" class=\"form-label\">Buscar</label>\n                     <input class=\"form-control\" list=\"datalistOptions\" name=\"busqueda\" id=\"campoBusqueda\" placeholder=\"Type to search...\">\n                 </form>\n\n                    <table class=\"table table-hover mt-3\">\n                     <thead >\n                         <tr>\n                             <th scope=\"col\">Reserva</th>\n                             <th scope=\"col\">CheckIn</th>\n                             <th scope=\"col\">CheckOut</th>\n                             <th scope=\"col\">Categoria</th>\n                             <th scope=\"col\">Método de Pago</th>\n                             <th scope=\"col\">Habitación</th>\n                             <th scope=\"col\">Accion</th>\n                         </tr>\n                     </thead>\n                     <tbody>\n                     <tr id=\"myResult\">\n                         ");
		if (listarBusquedaEmpleado.infoEmpleados() != null) {
			jteOutput.writeContent("\n                             <td  class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(listarBusquedaEmpleado.infoEmpleados().reserva());
			jteOutput.writeContent("</td>\n                             <td class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(String.valueOf(listarBusquedaEmpleado.infoEmpleados().checkIn()));
			jteOutput.writeContent("</td>\n                             <td class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(String.valueOf(listarBusquedaEmpleado.infoEmpleados().checkOut()));
			jteOutput.writeContent("</td>\n                             <td class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(listarBusquedaEmpleado.infoEmpleados().categoria());
			jteOutput.writeContent("</td>\n                             <td class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(listarBusquedaEmpleado.infoEmpleados().metodoPago());
			jteOutput.writeContent("</td>\n                             <td class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(listarBusquedaEmpleado.infoEmpleados().numero());
			jteOutput.writeContent("</td>\n                             <td class=\"text-capitalize\"><a id=\"btnEliminar\" class=\"btn btn-outline-danger btn-block\">Eliminar</a></td>\n\n                         ");
		}
		jteOutput.writeContent("\n                     </tr>\n\n                     </tbody>\n                 </table>\n\n                 ");
		if (listarBusquedaEmpleado.infoEmpleados() == null && !listarBusquedaEmpleado.confirm()) {
			jteOutput.writeContent("\n                     <div class=\"alert alert-info alert-dismissible fade show mt-4\" role=\"alert\">\n                         <strong>Error 404</strong> No existe registros\n                         <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n                     </div>\n                 ");
		}
		jteOutput.writeContent("\n             </div>\n\n         </div>\n   </section>\n\n    ");
		jteOutput.writeContent("\n    <script>\n        let data = document.querySelector(\"#btnEliminar\");\n\n         data.addEventListener(\"click\",()=>{\n         Swal.fire({\n            title: '¿Estás seguro?',\n            text: \"¡Esta acción no se puede deshacer!\",\n            icon: 'warning',\n            showCancelButton: true,\n            confirmButtonText: 'Sí',\n            cancelButtonText: 'No',\n            reverseButtons: false\n         }).then((resultado)=>{\n            if(resultado.isConfirmed){\n               let reservaValor = document.querySelector(\"#myResult\")\n               let elementoLista = reservaValor.getElementsByTagName(\"td\");\n               window.location.href = \"/eliminar/reservacion?busqueda=\"+elementoLista[0].innerHTML;\n            }\n          })\n      });\n    </script>\n    ");
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n</body>\n</html>>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ListarBusquedaEmpleado listarBusquedaEmpleado = (ListarBusquedaEmpleado)params.get("listarBusquedaEmpleado");
		render(jteOutput, jteHtmlInterceptor, listarBusquedaEmpleado);
	}
}
