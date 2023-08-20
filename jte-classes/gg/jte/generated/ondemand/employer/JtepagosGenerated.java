package gg.jte.generated.ondemand.employer;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.model.type.HabitacionTipo;
import com.alura.hotelalura.repository.dto.ReservaInfo;
import com.alura.hotelalura.ssr.dto.RtsReservacionEmpresa;
import com.alura.hotelalura.ssr.CookieController;
import java.text.DecimalFormat;
public final class JtepagosGenerated {
	public static final String JTE_NAME = "employer/pagos.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,4,5,7,7,7,12,12,12,18,18,21,22,24,36,36,41,41,50,50,52};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, RtsReservacionEmpresa respuesta) {
		jteOutput.writeContent("\n\n<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n\n    \n    <section class=\"container-fluid\">\n         <div class=\"row\">\n             ");
		gg.jte.generated.ondemand.partes.JtesiderbarEmpleadoGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n             <div class=\"col-sm p-3 min-vh-100\">\n                 ");
		jteOutput.writeContent("\n                 ");
		gg.jte.generated.ondemand.partes.JtetitulosGenerated.render(jteOutput, jteHtmlInterceptor, respuesta.usuario(), "Generar método de pago", "bi bi-wallet fs-1");
		jteOutput.writeContent("\n\n                 <form action=\"/administar/pagos\" method=\"post\" class=\"row g-4\" >\n                     <h4 class=\"text-capitalize\">Generar método de pago</h4>\n                     <div class=\"col-auto\">\n                         <label for=\"staticEmail2\">Metodo</label>\n                         <input type=\"text\" required class=\"form-control\" name=\"nombre\" pattern=\"[A-Za-z]+\">\n                     </div>\n                     <div class=\"col-auto\">\n                         <button type=\"submit\" class=\"btn btn-outline-success mt-4\">Agregar</button>\n                     </div>\n                 </form>\n                 ");
		if (respuesta.confirm()[0]) {
			jteOutput.writeContent("\n                     <div class=\"alert alert-info alert-dismissible fade show mt-4\" role=\"alert\">\n                         <strong>¡Método de pago generado!</strong> Se registran los datos en el sistema\n                         <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n                     </div>\n                 ");
		}
		jteOutput.writeContent("\n\n\n\n             </div>\n\n         </div>\n   </section>\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		RtsReservacionEmpresa respuesta = (RtsReservacionEmpresa)params.get("respuesta");
		render(jteOutput, jteHtmlInterceptor, respuesta);
	}
}
