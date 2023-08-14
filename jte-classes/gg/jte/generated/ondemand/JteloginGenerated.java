package gg.jte.generated.ondemand;
import com.alura.hotelalura.ssr.error.ErrorResponse;
public final class JteloginGenerated {
	public static final String JTE_NAME = "login.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,5,5,5,19,19,21,21,21,21,21,21,24,24,29,35,42,53,53,56};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ErrorResponse response) {
		jteOutput.writeContent("<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n<section class=\"vh-100\">\n    <div class=\"container py-5 h-100\">\n        <div class=\"row d-flex align-items-center justify-content-center h-100\">\n\n            <div class=\"col-md-8 col-lg-7 col-xl-6\">\n                <img src=\"https://i.ibb.co/pjVYvXr/19199469.jpg\"\n                     class=\"img-fluid\" alt=\"Phone image\"/>\n            </div>\n\n            <div class=\"col-md-7 col-lg-5 col-xl-5 offset-xl-1\">\n                <form action=\"/authetication\" method=\"post\">\n\n                    ");
		if (response != null) {
			jteOutput.writeContent("\n                        <div class=\"alert alert-danger alert-dismissible fade show mt-4\" role=\"alert\">\n                            <strong>Error ");
			jteOutput.setContext("strong", null);
			jteOutput.writeUserContent(response.status());
			jteOutput.writeContent(" </strong> ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(response.Detail());
			jteOutput.writeContent("\n                            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n                        </div>\n                    ");
		}
		jteOutput.writeContent("\n\n                    <div class=\"divider d-flex align-items-center my-4\">\n                        <p class=\"text-center fw-bold mx-3 mb-0 text-muted\">Login</p>\n                    </div>\n                    ");
		jteOutput.writeContent("\n                    <div class=\"form-outline mb-4\">\n                        <input type=\"text\" id=\"username\"  name=\"username\" class=\"form-control form-control-lg\" required />\n                        <label class=\"form-label\" for=\"username\" >Username</label>\n                    </div>\n\n                    ");
		jteOutput.writeContent("\n                    <div class=\"form-outline mb-4\">\n                        <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control form-control-lg\" required />\n                        <label class=\"form-label\" for=\"password\" >Password</label>\n                    </div>\n\n\n                    ");
		jteOutput.writeContent("\n                    <button type=\"submit\" class=\"btn btn-outline-success btn-lg btn-block mb-2\" style=\"width:100%\">Ingresar</button>\n                    <a href=\"/registrar\" class=\"btn btn-outline-primary btn-lg btn-block\" style=\"width:100%\">Registar</a>\n\n                </form>\n            </div>\n        </div>\n    </div>\n</section>\n\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ErrorResponse response = (ErrorResponse)params.get("response");
		render(jteOutput, jteHtmlInterceptor, response);
	}
}
