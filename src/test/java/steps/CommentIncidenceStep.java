package steps;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import inciDashboard.entities.User;
import inciDashboard.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pageobjects.PO_CommentsView;
import pageobjects.PO_LoginView;
import pageobjects.PO_NavView;
import pageobjects.PO_View;

import java.util.List;

public class CommentIncidenceStep {
    User user;
    WebDriver webDriver;
    String url = "http://localhost:8090/";
    @Dado("^el operario \"([^\"]*)\" con contraseña \"([^\"]*)\"$")
    public void elOperarioConContraseña(String arg0, String arg1) throws Throwable {
        System.out.println("Creamos el usuario");
        user = new User();
        user.setEmail(arg0);
        user.setPassword(arg1);
    }

    @Cuando("^el operario hace login correctamente$")
    public void elOperarioHaceLoginCorrectamente() throws Throwable {
        webDriver = new HtmlUnitDriver();
        webDriver.get(url);
        PO_View.checkElement(webDriver, Constants.TEXT_TYPE, "Login");
        System.out.println("Logeamos");
        PO_LoginView.fillForm(webDriver, user.getEmail(), user.getPassword());
    }

    @Y("^hace click en la opcion de aniadir comentario una incidencia, dentro de una incidencia seleccionada$")
    public void haceClickEnLaOpcionDeAniadirComentarioUnaIncidenciaDentroDeUnaIncidenciaSeleccionada() throws Throwable {
        By button = By.xpath("//tr[contains(text(), 'Incidencia en el bosque')]/following-sibling::*[6]");
        WebElement element = webDriver.findElement(button);
        element.click();
        System.out.println("Hacemos click en nuevo comentario");
        PO_NavView.clickOption(webDriver, Constants.NEWCOMMENT_STRING, Constants.CLASS_STRING, Constants.BTN_PRIMARY_STRING);
    }

    @Y("^crea un comentario$")
    public void creaUnComentario() throws Throwable {
        System.out.println("Creamos un comentario");
        PO_CommentsView.fillForm(webDriver, "Prueba test");
    }

    @Entonces("^aparece el comentario creado en la lista$")
    public void apareceElComentarioCreadoEnLaLista() throws Throwable {
        System.out.println("Comprobamos que aparece el comentario");
        PO_View.checkElement(webDriver, Constants.TEXT_TYPE, "Prueba test");
    }
}
