package steps;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import inciDashboard.entities.User;
import inciDashboard.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pageobjects.PO_LoginView;
import pageobjects.PO_View;

public class CheckIncidencesStep {

    User user;
    WebDriver webDriver;
    String url = "http://localhost:8090/";

    @Dado("^el operario \"([^\"]*)\" con contraseña \"([^\"]*)\" para hitorial$")
    public void elOperarioConContraseñaParaHitorial(String arg0, String arg1) throws Throwable {
        System.out.println("Creamos el usuario");
        user = new User();
        user.setEmail(arg0);
        user.setPassword(arg1);
    }

    @Cuando("^el agente hace login correctamente para historial$")
    public void elAgenteHaceLoginCorrectamenteParaHistorial() throws Throwable {
        webDriver = new HtmlUnitDriver();
        webDriver.get(url);
        PO_View.checkElement(webDriver, Constants.TEXT_TYPE, "Login");
        System.out.println("Logeamos");
        PO_LoginView.fillForm(webDriver, user.getEmail(), user.getPassword());
    }

    @Entonces("^se muestra correctamente el historial de incidencias$")
    public void seMuestraCorrectamenteElHistorialDeIncidencias() throws Throwable {
        PO_View.checkElement(webDriver, Constants.TEXT_TYPE, "Incidencia en el bosque");
    }
}
