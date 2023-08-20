package gg.jte.generated.ondemand.employer;
import com.alura.hotelalura.repository.dto.ReservaInfo;
import java.text.DecimalFormat;
import com.alura.hotelalura.repository.dto.ReservaInfoEmpleados;
import com.alura.hotelalura.ssr.dto.ResultadoListaEmpleado;
public final class JteindexGenerated {
	public static final String JTE_NAME = "employer/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,10,10,10,15,15,19,19,20,22,24,24,38,38,52,52,53,53,56,56,56,57,57,57,58,58,58,59,59,59,60,60,60,61,61,61,64,64,65,65,69,69,76,76,78};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ResultadoListaEmpleado resultadoListaEmpleado) {
		jteOutput.writeContent("\n\n<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n\n<section class=\"container-fluid\">\n    <div class=\"row\">\n        ");
		gg.jte.generated.ondemand.partes.JtesiderbarEmpleadoGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n        <div class=\"col-sm p-3 min-vh-100\">\n\n            ");
		if (resultadoListaEmpleado.usuario() != null) {
			jteOutput.writeContent("\n                ");
			gg.jte.generated.ondemand.partes.JtetitulosGenerated.render(jteOutput, jteHtmlInterceptor, resultadoListaEmpleado.usuario(), "reservaciones", "bi-arrow-repeat fs-1");
			jteOutput.writeContent("\n\n            ");
		}
		jteOutput.writeContent("\n\n            <form action=\"/busqueda\" method=\"get\">\n                <p class=\"text-capitalize\">buscar</p>\n                <div class=\"input-group\">\n                    <div class=\"form-outline\">\n                        <input type=\"number\" id=\"form1\" name=\"buscar\" class=\"form-control\" />\n                    </div>\n                    <button type=\"submit\"  class=\"btn btn-primary\">\n                        <i class=\"fas fa-search\"></i>\n                    </button>\n                </div>\n            </form>\n\n            ");
		if (resultadoListaEmpleado.lista() != null) {
			jteOutput.writeContent("\n\n            <table class=\"table table-hover mt-3\">\n                <thead >\n                <tr>\n                    <th scope=\"col\">Reserva</th>\n                    <th scope=\"col\">CheckIn</th>\n                    <th scope=\"col\">CheckOut</th>\n                    <th scope=\"col\">Categoria</th>\n                    <th scope=\"col\">Método de Pago</th>\n                    <th scope=\"col\">Habitación</th>\n                </tr>\n                </thead>\n                <tbody>\n                ");
			if (resultadoListaEmpleado.lista().size() != 0) {
				jteOutput.writeContent("\n                    ");
				for (ReservaInfoEmpleados reservaInfo: resultadoListaEmpleado.lista()) {
					jteOutput.writeContent("\n                        <tr>\n\n                            <td class=\"text-capitalize\">");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(reservaInfo.reserva());
					jteOutput.writeContent("</td>\n                            <td class=\"text-capitalize\">");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(String.valueOf(reservaInfo.checkIn()));
					jteOutput.writeContent("</td>\n                            <td class=\"text-capitalize\">");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(String.valueOf(reservaInfo.checkOut()));
					jteOutput.writeContent("</td>\n                            <td class=\"text-capitalize\">");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(reservaInfo.categoria());
					jteOutput.writeContent("</td>\n                            <td class=\"text-capitalize\">");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(reservaInfo.metodoPago());
					jteOutput.writeContent("</td>\n                            <td class=\"text-capitalize\">");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(reservaInfo.numero());
					jteOutput.writeContent("</td>\n\n                         </tr>\n                    ");
				}
				jteOutput.writeContent("\n                ");
			}
			jteOutput.writeContent("\n                </tbody>\n            </table>\n\n          ");
		}
		jteOutput.writeContent("\n\n        </div>\n\n    </div>\n</section>\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ResultadoListaEmpleado resultadoListaEmpleado = (ResultadoListaEmpleado)params.get("resultadoListaEmpleado");
		render(jteOutput, jteHtmlInterceptor, resultadoListaEmpleado);
	}
}
