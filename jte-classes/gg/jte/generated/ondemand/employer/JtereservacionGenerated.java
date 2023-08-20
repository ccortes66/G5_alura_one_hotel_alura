package gg.jte.generated.ondemand.employer;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.model.type.HabitacionTipo;
import com.alura.hotelalura.repository.dto.ReservaInfo;
import com.alura.hotelalura.ssr.dto.RtsReservacionEmpresa;
import com.alura.hotelalura.ssr.CookieController;
import java.text.DecimalFormat;
public final class JtereservacionGenerated {
	public static final String JTE_NAME = "employer/reservacion.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,4,5,7,7,7,12,12,12,18,18,21,22,24,44,44,49,49,61,61,62,62,62,62,62,62,62,62,62,62,62,62,63,63,70,70,75,75,84,84,86};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, RtsReservacionEmpresa respuesta) {
		jteOutput.writeContent("\n\n<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n\n    \n    <section class=\"container-fluid\">\n         <div class=\"row\">\n             ");
		gg.jte.generated.ondemand.partes.JtesiderbarEmpleadoGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n             <div class=\"col-sm p-3 min-vh-100\">\n                 ");
		jteOutput.writeContent("\n                 ");
		gg.jte.generated.ondemand.partes.JtetitulosGenerated.render(jteOutput, jteHtmlInterceptor, respuesta.usuario(), "Generar Habitaciones", "bi-card-list fs-1");
		jteOutput.writeContent("\n\n                 <form action=\"/generar/habitacion/tipo\" method=\"post\" class=\"row g-3\" >\n                     <h4 class=\"text-capitalize\">Generar Tipo Habitacion </h4>\n                     <div class=\"col-auto\">\n                         <label for=\"staticEmail2\">Categoria</label>\n                         <input type=\"text\" required class=\"form-control\" name=\"nombre\" pattern=\"[A-Za-z]+\">\n                     </div>\n                     <div class=\"col-auto\">\n                         <label for=\"inputPassword2\" >Precio por día</label>\n                         <input type=\"text\" required class=\"form-control\" id=\"inputPassword2\" name=\"precioUnitario\" pattern=\"[0-9]*\\.?[0-9]+\">\n                     </div>\n                     <div class=\"col-auto\">\n                         <label for=\"inputPassword2\" >Puntos</label>\n                         <input type=\"number\" required class=\"form-control\" id=\"inputPassword2\" name=\"puntoUnitario\">\n                     </div>\n                     <div class=\"col-auto\">\n                         <button type=\"submit\" class=\"btn btn-outline-success mt-4\">Agregar</button>\n                     </div>\n                 </form>\n                 ");
		if (respuesta.confirm()[0]) {
			jteOutput.writeContent("\n                     <div class=\"alert alert-info alert-dismissible fade show mt-4\" role=\"alert\">\n                         <strong>¡Tipo Habitación creada!</strong> Se registran los datos en el sistema\n                         <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n                     </div>\n                 ");
		}
		jteOutput.writeContent("\n                 <hr>\n\n                 <form action=\"/generar/habitacion\" method=\"post\"  class=\"row g-2 my-2\">\n                     <h4 class=\"text-capitalize\"> Generar Habitacion </h4>\n                     <div class=\"col-auto\">\n                         <label for=\"staticEmail2\">Numero</label>\n                         <input type=\"number\" required name=\"numero\" class=\"form-control\" id=\"staticEmail2\" >\n                     </div>\n                     <div class=\"col-auto\">\n                         <label for=\"inputPassword2\" >Habitacion tipo</label>\n                         <select class=\"form-select\" id=\"categoria\" name=\"categoria\" aria-label=\"Default select example\" required>\n                             ");
		for (HabitacionTipo categoria : respuesta.list() ) {
			jteOutput.writeContent("\n                                 <option class=\"text-capitalize\"");
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
			jteOutput.writeContent("</option>\n                             ");
		}
		jteOutput.writeContent("\n                         </select>\n                     </div>\n                     <div class=\"col-auto\">\n                         <button type=\"submit\" class=\"btn btn-outline-success mt-4\">Agregar</button>\n                     </div>\n                 </form>\n                 ");
		if (respuesta.confirm()[1]) {
			jteOutput.writeContent("\n                     <div class=\"alert alert-info alert-dismissible fade show mt-4\" role=\"alert\">\n                         <strong>!Habitación creada!</strong> Se registran los datos en el sistema\n                         <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n                     </div>\n                 ");
		}
		jteOutput.writeContent("\n                 <hr>\n\n\n             </div>\n\n         </div>\n   </section>\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		RtsReservacionEmpresa respuesta = (RtsReservacionEmpresa)params.get("respuesta");
		render(jteOutput, jteHtmlInterceptor, respuesta);
	}
}
