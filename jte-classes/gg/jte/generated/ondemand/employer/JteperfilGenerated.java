package gg.jte.generated.ondemand.employer;
import com.alura.hotelalura.model.Empleado;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.ssr.CookieController;
import com.alura.hotelalura.ssr.dto.ListarPerfil;
import java.time.LocalDate;
import java.time.Period;
import java.util.TreeSet;
public final class JteperfilGenerated {
	public static final String JTE_NAME = "employer/perfil.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,4,5,6,9,9,9,13,13,13,19,19,22,23,25,36,36,36,43,44,54,54,54,55,55,55,67,67,67,78,78,78,89,89,89,108,108,108,108,108,108,108,108,108,128,136,156,158,158,160};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Empleado listarPerfil) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n");
		gg.jte.generated.ondemand.partes.JteheadGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n<body>\n\n\n<section class=\"container-fluid\">\n    <div class=\"row\">\n        ");
		gg.jte.generated.ondemand.partes.JtesiderbarEmpleadoGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n\n        <div class=\"col-sm p-3 min-vh-100\">\n            ");
		jteOutput.writeContent("\n            ");
		gg.jte.generated.ondemand.partes.JtetitulosGenerated.render(jteOutput, jteHtmlInterceptor, listarPerfil.getUsuario(), "Perfil", "bi-person-circle fs-1");
		jteOutput.writeContent("\n\n            <div class=\"container py-3\">\n\n                <div class=\"row\">\n                    <div class=\"col-lg-4\">\n                        <div class=\"card mb-4\">\n                            <div class=\"card-body text-center\">\n                                <img class=\"avatar avatar-128 bg-light rounded-circle text-white p-2\"\n                                     src=\"https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg\" alt=\"d\">\n                                <h5 class=\"my-3\">\n                                    ");
		jteOutput.setContext("h5", null);
		jteOutput.writeUserContent(listarPerfil.getUsuario().getNombre());
		jteOutput.writeContent("\n                                </h5>\n\n                            </div>\n                        </div>\n\n                    </div>\n                    ");
		jteOutput.writeContent("\n                    ");
		jteOutput.writeContent("\n                    <div class=\"col-lg-8\">\n                        <div class=\"card mb-4\">\n                            <div class=\"card-body\">\n                                <div class=\"row\">\n                                    <div class=\"col-sm-3\">\n                                        <p class=\"mb-0\">Nombre completo</p>\n                                    </div>\n                                    <div class=\"col-sm-9\">\n                                        <p class=\"text-muted mb-0\">\n                                            ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(listarPerfil.getUsuario().getNombre());
		jteOutput.writeContent("\n                                            ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(listarPerfil.getUsuario().getApellido());
		jteOutput.writeContent("\n                                        </p>\n                                    </div>\n                                </div>\n                                <hr>\n\n                                <div class=\"row\">\n                                    <div class=\"col-sm-3\">\n                                        <p class=\"mb-0\">Edad</p>\n                                    </div>\n                                    <div class=\"col-sm-9\">\n                                        <p class=\"text-muted mb-0\">\n                                            ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(Period.between(listarPerfil.getUsuario().getFechaNacimiento(),LocalDate.now()).getYears());
		jteOutput.writeContent("\n                                        </p>\n                                    </div>\n                                </div>\n                                <hr>\n                                <div class=\"row\">\n                                    <div class=\"col-sm-3\">\n                                        <p class=\"mb-0\">Teléfono</p>\n                                    </div>\n                                    <div class=\"col-sm-9\">\n                                        <p class=\"text-muted mb-0\">\n                                            ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(listarPerfil.getUsuario().getTelefono());
		jteOutput.writeContent("\n                                        </p>\n                                    </div>\n                                </div>\n                                <hr>\n                                <div class=\"row\">\n                                    <div class=\"col-sm-3\">\n                                        <p class=\"mb-0\">Cargo</p>\n                                    </div>\n                                    <div class=\"col-sm-9\">\n                                        <p class=\"text-muted mb-0\">\n                                            ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(listarPerfil.getTipoEmpleado());
		jteOutput.writeContent("\n                                        </p>\n                                    </div>\n                                </div>\n\n                            </div>\n                        </div>\n\n                        <div class=\"col-lg-12\">\n                            <div class=\"card mb-4\">\n                                <div class=\"card-body\">\n                                    <form action=\"/perfil\" method=\"post\">\n\n                                        <div class=\"row\">\n                                            <div class=\"col-sm-3\">\n                                                <p class=\"mb-0\">Teléfono</p>\n                                            </div>\n                                            <div class=\"col-sm-9\">\n                                                <p class=\"text-muted mb-0\">\n                                                    <input type=\"tel\" id=\"telefono\" name=\"telefono\" required class=\"form-control\"");
		var __jte_html_attribute_0 = listarPerfil.getUsuario().getTelefono();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" />\n                                                </p>\n                                            </div>\n                                        </div>\n                                        <hr>\n\n\n                                        <div class=\"row\">\n\n                                            <div class=\"col-sm-3\">\n                                                <input type=\"submit\" class=\"btn btn-outline-secondary btn-block\" value=\"Editar\">\n                                            </div>\n                                        </div>\n\n                                    </form>\n                                </div>\n                            </div>\n                        </div>\n\n                    </div>\n                    ");
		jteOutput.writeContent("\n                </div>\n            </div>\n\n        </div>\n\n    </div>\n</section>\n");
		jteOutput.writeContent("\n<script>\n    let data = document.querySelector(\"#btnEliminar\");\n\n     data.addEventListener(\"click\",()=>{\n     Swal.fire({\n        title: '¿Estás seguro?',\n        text: \"¡Esta acción no se puede deshacer!\",\n        icon: 'warning',\n        showCancelButton: true,\n        confirmButtonText: 'Sí',\n        cancelButtonText: 'No',\n        reverseButtons: false\n     }).then((resultado)=>{\n        if(resultado.isConfirmed){\n           window.location.href = \"/eliminar\";\n        }\n      })\n  });\n</script>\n");
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.partes.JtefooterGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Empleado listarPerfil = (Empleado)params.get("listarPerfil");
		render(jteOutput, jteHtmlInterceptor, listarPerfil);
	}
}
