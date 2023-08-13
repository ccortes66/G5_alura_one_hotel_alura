package gg.jte.generated.ondemand.partes;
public final class JtesiderbarGenerated {
	public static final String JTE_NAME = "partes/siderbar.jte";
	public static final int[] JTE_LINE_INFO = {47,47,47,47,47,47};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("\n\n        <div class=\"col-sm-auto bg-light sticky-top\">\n            <div class=\"d-flex flex-sm-column flex-row flex-nowrap bg-light align-items-center sticky-top\">\n                <a href=\"/listar/reservaciones\" class=\"d-block p-3 link-dark text-decoration-none\" title=\"\" data-bs-toggle=\"tooltip\" data-bs-placement=\"right\" data-bs-original-title=\"Icon-only\">\n                    <i class=\"bi-bootstrap fs-1\"></i>\n                </a>\n                <ul class=\"nav nav-pills nav-flush flex-sm-column flex-row flex-nowrap mb-auto mx-auto text-center justify-content-between w-100 px-3 align-items-center\">\n                    <li class=\"nav-item\">\n                        <a href=\"#\" class=\"nav-link py-3 px-2\" title=\"\" data-bs-toggle=\"tooltip\" data-bs-placement=\"right\" data-bs-original-title=\"Home\">\n                            <i class=\"bi-house fs-1\"></i>\n                        </a>\n                    </li>\n                    <li>\n                        <a href=\"#\" class=\"nav-link py-3 px-2\" title=\"\" data-bs-toggle=\"tooltip\" data-bs-placement=\"right\" data-bs-original-title=\"Dashboard\">\n                            <i class=\"bi-speedometer2 fs-1\"></i>\n                        </a>\n                    </li>\n                    <li>\n                        <a href=\"#\" class=\"nav-link py-3 px-2\" title=\"\" data-bs-toggle=\"tooltip\" data-bs-placement=\"right\" data-bs-original-title=\"Orders\">\n                            <i class=\"bi-table fs-1\"></i>\n                        </a>\n                    </li>\n                    <li>\n                        <a href=\"#\" class=\"nav-link py-3 px-2\" title=\"\" data-bs-toggle=\"tooltip\" data-bs-placement=\"right\" data-bs-original-title=\"Products\">\n                            <i class=\"bi-heart fs-1\"></i>\n                        </a>\n                    </li>\n                    <li>\n                        <a href=\"#\" class=\"nav-link py-3 px-2\" title=\"\" data-bs-toggle=\"tooltip\" data-bs-placement=\"right\" data-bs-original-title=\"Customers\">\n                            <i class=\"bi-people fs-1\"></i>\n                        </a>\n                    </li>\n                </ul>\n                <div class=\"dropdown\">\n                    <a href=\"#\" class=\"d-flex align-items-center justify-content-center p-3 link-dark text-decoration-none dropdown-toggle\" id=\"dropdownUser3\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">\n                        <i class=\"bi-person-circle h2\"></i>\n                    </a>\n                    <ul class=\"dropdown-menu text-small shadow\" aria-labelledby=\"dropdownUser3\">\n                        <li><a href=\"/cerrar\" class=\"dropdown-item\" href=\"#\">Salir</a></li>\n                        <li><a class=\"dropdown-item\" href=\"#\">Perfil</a></li>\n                    </ul>\n                </div>\n            </div>\n        </div>\n\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}