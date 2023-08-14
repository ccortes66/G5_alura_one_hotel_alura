package gg.jte.generated.ondemand;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.ReservaInfo;
import com.alura.hotelalura.ssr.dto.ListarCatrgoriaMetodo;
import com.alura.hotelalura.ssr.CookieController;
import java.text.DecimalFormat;
public final class JtereservacionGenerated {
	public static final String JTE_NAME = "reservacion.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,4,6,6,6,13,13,13,19,19,22,23,25,43,43,44,44,45,45,45,45,45,45,45,45,45,45,45,45,46,46,47,47,54,54,55,55,56,56,56,56,56,56,56,56,56,56,56,56,57,57,58,58,69,69,71,71,73,73,73,73,73,73,76,76,80,80,80,81,81,81,82,82,82,87,87,89,89,97,97,99};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Usuario user, ReservaInfo reservaInfo, ListarCatrgoriaMetodo metodo) {
		jteOutput.writeContent("\n\n<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n\n    \n    <section class=\"container-fluid\">\n         <div class=\"row\">\n             ");
		gg.jte.generated.ondemand.partes.JtesiderbarGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n             <div class=\"col-sm p-3 min-vh-100\">\n                 ");
		jteOutput.writeContent("\n                 ");
		gg.jte.generated.ondemand.partes.JtetitulosGenerated.render(jteOutput, jteHtmlInterceptor, CookieController.getMyUsuario(), "Generar Reservación", "bi-card-list fs-1");
		jteOutput.writeContent("\n\n                 <form action=\"/generar/reservacion\" method=\"post\">\n                  <div class=\"row g-3\">\n\n                     <div class=\"col-auto\">\n                         <label for=\"checkOut\" class=\"form-label\">Check In</label>\n                         <input type=\"date\" class=\"form-control\" name=\"checkIn\" required  aria-label=\"First name\">\n                     </div>\n\n                     <div class=\"col-auto\">\n                         <label for=\"checkOut\" class=\"form-label\">Check Out</label>\n                         <input type=\"date\" class=\"form-control\" id=\"checkOut\" name=\"checkOut\" required aria-label=\"Last name\">\n                     </div>\n\n                     <div class=\"col-auto\">\n                         <label for=\"categoria\" class=\"form-label\">Categoría</label>\n                         <select class=\"form-select\" id=\"categoria\" name=\"categoria\" aria-label=\"Default select example\" required>\n                             ");
		if (metodo.habitacionTipo() != null) {
			jteOutput.writeContent("\n                                 ");
			for (var categoria: metodo.habitacionTipo()) {
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
		jteOutput.writeContent("\n                         </select>\n                     </div>\n\n                     <div class=\"col-auto\">\n                         <label for=\"metodoPago\" class=\"form-label\">Método de pago</label>\n                         <select class=\"form-select\" id=\"metodoPago\" name=\"metodoPago\" aria-label=\"Default select example\" required>\n                             ");
		if (metodo.metodoPago() != null) {
			jteOutput.writeContent("\n                                 ");
			for (var pago: metodo.metodoPago()) {
				jteOutput.writeContent("\n                                     <option");
				var __jte_html_attribute_1 = pago.getNombre();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("option", "value");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("option", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">");
				jteOutput.setContext("option", null);
				jteOutput.writeUserContent(pago.getNombre());
				jteOutput.writeContent("</option>\n                                 ");
			}
			jteOutput.writeContent("\n                             ");
		}
		jteOutput.writeContent("\n                         </select>\n                     </div>\n\n                      <div class=\"col-auto\">\n\n                          <input class=\"btn btn-outline-primary btn-block \" style=\"margin-top:33%\"  type=\"submit\" value=\"Reservar\">\n                      </div>\n\n                 </div>\n                 </form>\n                 ");
		if (metodo.response() != null) {
			jteOutput.writeContent("\n\n                     ");
			if (metodo.info() == null) {
				jteOutput.writeContent("\n                         <div class=\"alert alert-danger alert-dismissible fade show mt-4\" role=\"alert\">\n                             <strong>Error ");
				jteOutput.setContext("strong", null);
				jteOutput.writeUserContent(metodo.response().status());
				jteOutput.writeContent("</strong> ");
				jteOutput.setContext("div", null);
				jteOutput.writeUserContent(metodo.response().Detail());
				jteOutput.writeContent("\n                             <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n                         </div>\n                     ");
			} else {
				jteOutput.writeContent("\n                         <div class=\"alert alert-success alert-dismissible fade show mt-4\" role=\"alert\">\n                             <h4 class=\"alert-heading\">¡La reserva se ha realizado con éxito!</h4>\n                             <p> Esperamos que disfrute de su estancia en el Hotel Alura. Aquí tiene los detalles de su reserva:</p>\n                             <p> ID: ");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(metodo.info().reserva());
				jteOutput.writeContent(" </p>\n                             <p> Precio (en USD):");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(new DecimalFormat("#,###.00").format(metodo.info().valor()));
				jteOutput.writeContent(" </p>\n                             <p> Número de habitación: ");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(metodo.info().habitacion());
				jteOutput.writeContent("</p>\n                             <hr>\n                             <p class=\"mb-0\">Whenever you need to, be sure to use margin utilities to keep things nice and tidy.</p>\n                             <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n                         </div>\n                     ");
			}
			jteOutput.writeContent("\n\n                 ");
		}
		jteOutput.writeContent("\n\n\n             </div>\n\n         </div>\n   </section>\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Usuario user = (Usuario)params.get("user");
		ReservaInfo reservaInfo = (ReservaInfo)params.get("reservaInfo");
		ListarCatrgoriaMetodo metodo = (ListarCatrgoriaMetodo)params.get("metodo");
		render(jteOutput, jteHtmlInterceptor, user, reservaInfo, metodo);
	}
}
