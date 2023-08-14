package gg.jte.generated.ondemand;
import com.alura.hotelalura.repository.dto.ReservaInfo;
import com.alura.hotelalura.ssr.CookieController;
import java.text.DecimalFormat;
import com.alura.hotelalura.ssr.dto.ListarBusqueda;
public final class JtebuscarGenerated {
	public static final String JTE_NAME = "buscar.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,8,8,8,14,14,17,18,20,25,25,26,26,27,27,27,27,27,27,27,27,27,28,28,29,29,45,45,46,46,46,47,47,47,48,48,48,49,49,49,50,50,50,51,51,51,52,52,58,58,63,63,69,75,77,77,79};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ListarBusqueda busqueda) {
		jteOutput.writeContent("<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n\n    \n    <section class=\"container-fluid\">\n         <div class=\"row\">\n             ");
		gg.jte.generated.ondemand.partes.JtesiderbarGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n             <div class=\"col-sm p-3 min-vh-100\">\n                 ");
		jteOutput.writeContent("\n                 ");
		gg.jte.generated.ondemand.partes.JtetitulosGenerated.render(jteOutput, jteHtmlInterceptor, CookieController.getMyUsuario(), "Buscar Reservación", "bi-search fs-1");
		jteOutput.writeContent("\n                 <form action=\"/busqueda\" method=\"get\">\n                     <label for=\"exampleDataList\" class=\"form-label\">Buscar</label>\n                     <input class=\"form-control\" list=\"datalistOptions\" name=\"busqueda\" id=\"exampleDataList\" placeholder=\"Type to search...\">\n                         <datalist id=\"datalistOptions\">\n                             ");
		if (busqueda.lista() != null) {
			jteOutput.writeContent("\n                               ");
			for (ReservaInfo reservaInfo: busqueda.lista() ) {
				jteOutput.writeContent("\n                                   <option");
				var __jte_html_attribute_0 = reservaInfo.reserva();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("option", "value");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("option", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent("></option>\n                               ");
			}
			jteOutput.writeContent("\n                             ");
		}
		jteOutput.writeContent("\n                         </datalist>\n                </form>\n                    <table class=\"table table-hover mt-3\">\n                     <thead >\n                         <tr>\n                             <th scope=\"col\">reserva</th>\n                             <th scope=\"col\">checkIn</th>\n                             <th scope=\"col\">checkOut</th>\n                             <th scope=\"col\">precio</th>\n                             <th scope=\"col\">categoria</th>\n                             <th scope=\"col\">habitacion</th>\n                         </tr>\n                     </thead>\n                     <tbody>\n                     <tr>\n                         ");
		if (busqueda.info() != null) {
			jteOutput.writeContent("\n                             <td class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(busqueda.info().reserva());
			jteOutput.writeContent("</td>\n                             <td class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(String.valueOf(busqueda.info().checkIn()));
			jteOutput.writeContent("</td>\n                             <td class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(String.valueOf(busqueda.info().checkOut()));
			jteOutput.writeContent("</td>\n                             <td class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(new DecimalFormat("#,###.00").format(busqueda.info().precio()));
			jteOutput.writeContent("</td>\n                             <td class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(busqueda.info().categoria());
			jteOutput.writeContent("</td>\n                             <td class=\"text-capitalize\">");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(busqueda.info().habitacion().toString());
			jteOutput.writeContent("</td>\n                         ");
		}
		jteOutput.writeContent("\n                     </tr>\n\n                     </tbody>\n                 </table>\n\n                 ");
		if (busqueda.info() == null && busqueda.lista() == null) {
			jteOutput.writeContent("\n                     <div class=\"alert alert-info alert-dismissible fade show mt-4\" role=\"alert\">\n                         <strong>Error 404</strong> No existe registros\n                         <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n                     </div>\n                 ");
		}
		jteOutput.writeContent("\n             </div>\n\n         </div>\n   </section>\n\n    ");
		jteOutput.writeContent("\n    <script>\n\n\n\n    </script>\n    ");
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n</body>\n</html>>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ListarBusqueda busqueda = (ListarBusqueda)params.get("busqueda");
		render(jteOutput, jteHtmlInterceptor, busqueda);
	}
}
