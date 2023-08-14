package gg.jte.generated.ondemand;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.ReservaInfo;
import java.text.DecimalFormat;
import java.util.List;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,10,10,10,16,16,19,34,34,35,35,37,37,37,38,38,38,39,39,39,40,40,40,41,41,41,42,42,42,44,44,45,45,53,53,55};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Usuario user, List<ReservaInfo> infoList) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n\n    \n    <section class=\"container-fluid\">\n         <div class=\"row\">\n             ");
		gg.jte.generated.ondemand.partes.JtesiderbarGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n             <div class=\"col-sm p-3 min-vh-100\">\n                 ");
		jteOutput.writeContent("\n                 <h2>Mis reservaciones</h2>\n                 <hr />\n                 <table class=\"table\">\n                     <thead class=\"thead-dark\">\n                     <tr>\n                         <th scope=\"col\">reserva</th>\n                         <th scope=\"col\">checkIn</th>\n                         <th scope=\"col\">checkOut</th>\n                         <th scope=\"col\">precio</th>\n                         <th scope=\"col\">categoria</th>\n                         <th scope=\"col\">habitacion</th>\n                     </tr>\n                     </thead>\n                     <tbody>\n                     ");
		if (infoList != null) {
			jteOutput.writeContent("\n                         ");
			for (ReservaInfo reservaInfo: infoList) {
				jteOutput.writeContent("\n                             <tr>\n                                 <td class=\"text-capitalize\">");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(reservaInfo.reserva());
				jteOutput.writeContent("</td>\n                                 <td class=\"text-capitalize\">");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(String.valueOf(reservaInfo.checkIn()));
				jteOutput.writeContent("</td>\n                                 <td class=\"text-capitalize\">");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(String.valueOf(reservaInfo.checkOut()));
				jteOutput.writeContent("</td>\n                                 <td class=\"text-capitalize\">");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(new DecimalFormat("#,###.00").format(reservaInfo.precio()));
				jteOutput.writeContent("</td>\n                                 <td class=\"text-capitalize\">");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(reservaInfo.categoria());
				jteOutput.writeContent("</td>\n                                 <td class=\"text-capitalize\">");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(reservaInfo.habitacion().toString());
				jteOutput.writeContent("</td>\n                             </tr>\n                         ");
			}
			jteOutput.writeContent("\n                     ");
		}
		jteOutput.writeContent("\n                     </tbody>\n                 </table>\n             </div>\n\n         </div>\n   </section>\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Usuario user = (Usuario)params.get("user");
		List<ReservaInfo> infoList = (List<ReservaInfo>)params.get("infoList");
		render(jteOutput, jteHtmlInterceptor, user, infoList);
	}
}
