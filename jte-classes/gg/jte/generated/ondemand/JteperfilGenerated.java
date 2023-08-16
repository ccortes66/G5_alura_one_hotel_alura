package gg.jte.generated.ondemand;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.ssr.CookieController;
import java.time.LocalDate;
import java.time.Period;
import java.util.TreeSet;
public final class JteperfilGenerated {
	public static final String JTE_NAME = "perfil.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,4,7,7,7,11,11,11,17,17,20,21,23,34,34,34,36,36,37,37,37,38,38,39,39,39,67,67,67,68,68,68,80,80,80,91,91,91,118,118,118,118,118,118,118,118,118,130,130,131,131,132,132,132,132,132,132,132,132,132,132,132,132,133,133,134,134,162,183,185,185,187};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, TreeSet<String> mysPaises) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n\n\n<section class=\"container-fluid\">\n    <div class=\"row\">\n        ");
		gg.jte.generated.ondemand.partes.JtesiderbarGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n        <div class=\"col-sm p-3 min-vh-100\">\n            ");
		jteOutput.writeContent("\n            ");
		gg.jte.generated.ondemand.partes.JtetitulosGenerated.render(jteOutput, jteHtmlInterceptor, CookieController.getIsCliente().getUsuario(), "Perfil", "bi-person-circle fs-1");
		jteOutput.writeContent("\n\n            <div class=\"container py-3\">\n\n                <div class=\"row\">\n                    <div class=\"col-lg-4\">\n                        <div class=\"card mb-4\">\n                            <div class=\"card-body text-center\">\n                                <img src=\"https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp\" alt=\"avatar\"\n                                     class=\"rounded-circle img-fluid\" style=\"width: 150px;\">\n                                <h5 class=\"my-3\">\n                                    ");
		jteOutput.setContext("h5", null);
		jteOutput.writeUserContent(CookieController.getMyUsuario().getNombre());
		jteOutput.writeContent("\n                                </h5>\n                                ");
		if (CookieController.getIsCliente().getVip() != null) {
			jteOutput.writeContent("\n                                    <p class=\"text-muted mb-1\">VIP Numero ");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(CookieController.getIsCliente().getVip());
			jteOutput.writeContent("</p>\n                                ");
		}
		jteOutput.writeContent("\n                                <p class=\"text-muted mb-4\"> Hotel alura puntos ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(CookieController.getIsCliente().getPuntos());
		jteOutput.writeContent("</p>\n\n                            </div>\n                        </div>\n\n                        <div class=\"card mb-4 mb-lg-0\">\n                            <div class=\"card-body p-0\">\n                                <ul class=\"list-group list-group-flush rounded-3\">\n                                    <li class=\"list-group-item d-flex justify-content-between align-items-center p-3\">\n                                        <p class=\"mb-0\">Eliminar Cuenta</p>\n                                        <a id=\"btnEliminar\" class=\"btn btn-outline-danger btn-block\">Eliminar</a>\n                                    </li>\n\n                                </ul>\n                            </div>\n                        </div>\n\n\n                    </div>\n                    <div class=\"col-lg-8\">\n                        <div class=\"card mb-4\">\n                            <div class=\"card-body\">\n                                <div class=\"row\">\n                                    <div class=\"col-sm-3\">\n                                        <p class=\"mb-0\">Nombre completo</p>\n                                    </div>\n                                    <div class=\"col-sm-9\">\n                                        <p class=\"text-muted mb-0\">\n                                            ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(CookieController.getMyUsuario().getNombre());
		jteOutput.writeContent("\n                                            ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(CookieController.getMyUsuario().getApellido());
		jteOutput.writeContent("\n                                        </p>\n                                    </div>\n                                </div>\n                                <hr>\n                                \n                                <div class=\"row\">\n                                    <div class=\"col-sm-3\">\n                                        <p class=\"mb-0\">Edada</p>\n                                    </div>\n                                    <div class=\"col-sm-9\">\n                                        <p class=\"text-muted mb-0\">\n                                            ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(Period.between(CookieController.getMyUsuario().getFechaNacimiento(),LocalDate.now()).getYears());
		jteOutput.writeContent("\n                                        </p>\n                                    </div>\n                                </div>\n                                <hr>\n                                <div class=\"row\">\n                                    <div class=\"col-sm-3\">\n                                        <p class=\"mb-0\">Teléfono</p>\n                                    </div>\n                                    <div class=\"col-sm-9\">\n                                        <p class=\"text-muted mb-0\">\n                                            ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(CookieController.getIsCliente().getUsuario().getTelefono());
		jteOutput.writeContent("\n                                        </p>\n                                    </div>\n                                </div>\n                                <hr>\n                                <div class=\"row\">\n                                    <div class=\"col-sm-3\">\n                                        <p class=\"mb-0\">Nacionalidad</p>\n                                    </div>\n                                    <div class=\"col-sm-9\">\n                                        <p class=\"text-muted mb-0\"></p>\n                                    </div>\n                                </div>\n\n                            </div>\n                        </div>\n\n                        <div class=\"col-lg-12\">\n                            <div class=\"card mb-4\">\n                                <div class=\"card-body\">\n                                   <form action=\"/perfil\" method=\"post\">\n                                    <div class=\"row\">\n                                        <div class=\"col-sm-3\">\n                                            <p class=\"mb-0\">Teléfono</p>\n                                        </div>\n                                        <div class=\"col-sm-9\">\n                                            <p class=\"text-muted mb-0\">\n                                                <input type=\"tel\" id=\"telefono\" name=\"telefono\" required class=\"form-control\"");
		var __jte_html_attribute_0 = CookieController.getIsCliente().getUsuario().getTelefono();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" />\n                                            </p>\n                                        </div>\n                                    </div>\n                                    <hr>\n                                    <div class=\"row\">\n                                        <div class=\"col-sm-3\">\n                                            <p class=\"mb-0\">Nacionalidad</p>\n                                        </div>\n                                        <div class=\"col-sm-9\">\n                                            <p class=\"text-muted mb-0\">\n                                                <select class=\"form-select\" id=\"metodoPago\" name=\"metodoPago\" aria-label=\"Default select example\" required>\n                                                    ");
		if (!mysPaises.isEmpty()) {
			jteOutput.writeContent("\n                                                        ");
			for (String valor: mysPaises) {
				jteOutput.writeContent("\n                                                            <option");
				var __jte_html_attribute_1 = valor;
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("option", "value");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("option", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">");
				jteOutput.setContext("option", null);
				jteOutput.writeUserContent(valor);
				jteOutput.writeContent("</option>\n                                                        ");
			}
			jteOutput.writeContent("\n                                                    ");
		}
		jteOutput.writeContent("\n                                                </select>\n                                            </p>\n                                        </div>\n                                    </div>\n                                    <hr>\n\n                                       <div class=\"row\">\n\n                                           <div class=\"col-sm-3\">\n                                               <input type=\"submit\" class=\"btn btn-outline-secondary btn-block\" value=\"Editar\">\n                                           </div>\n                                       </div>\n\n                                   </form>\n                                </div>\n                            </div>\n\n\n                        </div>\n                    </div>\n                </div>\n            </div>\n\n        </div>\n\n    </div>\n</section>\n");
		jteOutput.writeContent("\n<script>\n    let data = document.querySelector(\"#btnEliminar\");\n\n     data.addEventListener(\"click\",()=>{\n     Swal.fire({\n        title: '¿Estás seguro?',\n        text: \"¡Esta acción no se puede deshacer!\",\n        icon: 'warning',\n        showCancelButton: true,\n        confirmButtonText: 'Sí',\n        cancelButtonText: 'No',\n        reverseButtons: false\n     }).then((resultado)=>{\n        if(resultado.isConfirmed){\n           window.location.href = \"/eliminar\";\n        }\n      })\n  });\n</script>\n\n");
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		TreeSet<String> mysPaises = (TreeSet<String>)params.get("mysPaises");
		render(jteOutput, jteHtmlInterceptor, mysPaises);
	}
}
