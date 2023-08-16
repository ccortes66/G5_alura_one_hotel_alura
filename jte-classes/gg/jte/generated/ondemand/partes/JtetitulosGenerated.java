package gg.jte.generated.ondemand.partes;
import com.alura.hotelalura.model.Usuario;
public final class JtetitulosGenerated {
	public static final String JTE_NAME = "partes/titulos.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,9,9,9,10,10,10,10,10,10,10,10,10,11,11,12,12,12,16,16,16,21};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Usuario user, String titulo, String icon) {
		jteOutput.writeContent("\n    <div class=\"d-flex justify-content-between \">\n\n        <h2 class=\"text-capitalize\">\n            ");
		if (icon != null) {
			jteOutput.writeContent("\n                <i");
			var __jte_html_attribute_0 = icon;
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
				jteOutput.writeContent(" class=\"");
				jteOutput.setContext("i", "class");
				jteOutput.writeUserContent(__jte_html_attribute_0);
				jteOutput.setContext("i", null);
				jteOutput.writeContent("\"");
			}
			jteOutput.writeContent("></i>\n            ");
		}
		jteOutput.writeContent("\n            ");
		jteOutput.setContext("h2", null);
		jteOutput.writeUserContent(titulo);
		jteOutput.writeContent("\n        </h2>\n\n        <p class=\"text-capitalize mt-2\">\n            <i class=\"bi-person-circle h2 \"></i> ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(user.getNombre());
		jteOutput.writeContent("\n        </p>\n    </div>\n<hr/>\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Usuario user = (Usuario)params.get("user");
		String titulo = (String)params.get("titulo");
		String icon = (String)params.get("icon");
		render(jteOutput, jteHtmlInterceptor, user, titulo, icon);
	}
}
