package gg.jte.generated.ondemand.employer;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.ssr.dto.ConsultaInfoReservacion;
import com.alura.hotelalura.ssr.CookieController;
import java.text.DecimalFormat;
public final class JteconsultarGenerated {
	public static final String JTE_NAME = "employer/consultar.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,11,11,11,17,17,20,21,23,41,41,42,42,43,43,43,43,43,43,43,43,43,43,43,43,44,44,45,45,56,56,59,59,59,59,59,59,63,63,76,76,76,77,77,77,78,78,78,79,79,79,86,86,94,94,96};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Usuario user, ConsultaInfoReservacion reservacion) {
		jteOutput.writeContent("\n\n<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n\n    \n    <section class=\"container-fluid\">\n         <div class=\"row\">\n             ");
		gg.jte.generated.ondemand.partes.JtesiderbarEmpleadoGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n             <div class=\"col-sm p-3 min-vh-100\">\n                 ");
		jteOutput.writeContent("\n                 ");
		gg.jte.generated.ondemand.partes.JtetitulosGenerated.render(jteOutput, jteHtmlInterceptor, reservacion.usuario(), "Consultar Precio", "bi-cash-stack fs-1");
		jteOutput.writeContent("\n\n                 <form action=\"/consultar/reservacion/empleado\" method=\"post\">\n                  <div class=\"row g-3\">\n\n                     <div class=\"col-auto\">\n                         <label for=\"checkOut\" class=\"form-label\">Check In</label>\n                         <input type=\"date\" class=\"form-control\" id=\"checkIn\" name=\"checkIn\" required  aria-label=\"First name\">\n                     </div>\n\n                     <div class=\"col-auto\">\n                         <label for=\"checkOut\" class=\"form-label\">Check Out</label>\n                         <input type=\"date\" class=\"form-control\" id=\"checkOut\" name=\"checkOut\" required aria-label=\"Last name\">\n                     </div>\n\n                     <div class=\"col-auto\">\n                         <label for=\"categoria\" class=\"form-label\">Categoría</label>\n                         <select class=\"form-select\" id=\"categoria\" name=\"categoria\" aria-label=\"Default select example\" required>\n                             ");
		if (reservacion.habitacionTipo() != null) {
			jteOutput.writeContent("\n                                 ");
			for (var categoria: reservacion.habitacionTipo()) {
				jteOutput.writeContent("\n                                     <option class=\"text-capitalize\"");
				var __jte_html_attribute_0 = categoria.getNombre();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("option", "value");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("option", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">");
				jteOutput.setContext("option", null);
				jteOutput.writeUserContent(categoria.getNombre());
				jteOutput.writeContent("</option>\n                                 ");
			}
			jteOutput.writeContent("\n                             ");
		}
		jteOutput.writeContent("\n                         </select>\n                     </div>\n\n\n                      <div class=\"col-auto\">\n                          <input class=\"btn btn-outline-success btn-block \" style=\"margin-top:33%\"  type=\"submit\" value=\"Consultar\">\n                      </div>\n\n                 </div>\n                 </form>\n                 ");
		if (reservacion.response() != null) {
			jteOutput.writeContent("\n\n                     <div class=\"alert alert-danger alert-dismissible fade show mt-4\" role=\"alert\">\n                             <strong>Error ");
			jteOutput.setContext("strong", null);
			jteOutput.writeUserContent(reservacion.response().status());
			jteOutput.writeContent("</strong> ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(reservacion.response().Detail());
			jteOutput.writeContent("\n                             <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n                     </div>\n\n                 ");
		} else if (reservacion.reserva() != null) {
			jteOutput.writeContent("\n\n                     <div class=\"card mb-3 mt-4\" style=\"max-width: 540px;\">\n                         <div class=\"row g-0\">\n                             <div class=\"col-md-4\">\n                                 <img src=\"https://i.ibb.co/vVhTHRy/logo.png\" class=\"img-fluid rounded-start\" alt=\"...\">\n                             </div>\n                             <div class=\"col-md-8\">\n                                 <div class=\"card-body\">\n                                     <h5 class=\"card-title\">Consultar Precio</h5>\n                                     <p class=\"card-text\">Cálculo del costo según la duración de la estadía .</p>\n\n                                    <ul class=\"list-unstyled\">\n                                        <li id=\"myInicio\" class=\"card-text\">Check In:");
			jteOutput.setContext("li", null);
			jteOutput.writeUserContent(String.valueOf(reservacion.reserva().checkIn()));
			jteOutput.writeContent("</li>\n                                        <li id=\"myFin\" class=\"card-text\">Check Out:");
			jteOutput.setContext("li", null);
			jteOutput.writeUserContent(String.valueOf(reservacion.reserva().checkOut()));
			jteOutput.writeContent(" </li>\n                                        <li id=\"myCategoria\" class=\"card-text\">Categoria: ");
			jteOutput.setContext("li", null);
			jteOutput.writeUserContent(reservacion.reserva().tipo());
			jteOutput.writeContent("</li>\n                                        <li class=\" card-text\">Precio(USD) $");
			jteOutput.setContext("li", null);
			jteOutput.writeUserContent(reservacion.reserva().metodoPago());
			jteOutput.writeContent("</li>\n                                     </ul>\n\n                                 </div>\n                             </div>\n                         </div>\n                     </div>\n                 ");
		}
		jteOutput.writeContent("\n\n             </div>\n\n         </div>\n   </section>\n\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Usuario user = (Usuario)params.get("user");
		ConsultaInfoReservacion reservacion = (ConsultaInfoReservacion)params.get("reservacion");
		render(jteOutput, jteHtmlInterceptor, user, reservacion);
	}
}
