package gg.jte.generated.ondemand;
import com.alura.hotelalura.repository.dto.ReservaInfo;
import com.alura.hotelalura.ssr.CookieController;
public final class JtebuscarGenerated {
	public static final String JTE_NAME = "buscar.jte";
	public static final int[] JTE_LINE_INFO = {1,1,2,4,4,4,8,8,8,14,14,17,18,20,28,28,30};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ReservaInfo infoList) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n\n    \n    <section class=\"container-fluid\">\n         <div class=\"row\">\n             ");
		gg.jte.generated.ondemand.partes.JtesiderbarGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n             <div class=\"col-sm p-3 min-vh-100\">\n                 ");
		jteOutput.writeContent("\n                 ");
		gg.jte.generated.ondemand.partes.JtetitulosGenerated.render(jteOutput, jteHtmlInterceptor, CookieController.getMyUsuario(), "Buscar Reservación", "bi-search fs-1");
		jteOutput.writeContent("\n\n\n             </div>\n\n         </div>\n   </section>\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ReservaInfo infoList = (ReservaInfo)params.get("infoList");
		render(jteOutput, jteHtmlInterceptor, infoList);
	}
}
