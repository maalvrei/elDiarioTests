import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import java.nio.file.Paths;
import com.test.base.BaseTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.elDiarioTest.pages.ElDiarioHomePage; // <-- Usamos el Page Object correcto
import io.qameta.allure.Description; // <-- Importaciones de Allure
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Feature("Navegación del Home hacia la página de Política") // Característica principal que se prueba
public class PoliticaTest extends BaseTest {

    ElDiarioHomePage homePage; // <-- Usamos la clase correcta

    @BeforeEach
    void setupHomePage() {
        homePage = new ElDiarioHomePage(page);
    }

    @Step("{0}")
    public void step(String stepName) {
        // Este método está vacío a propósito. Allure usa la anotación @Step
        // para registrar el texto que le pasamos como un paso en el reporte.
    }

    @Test
    @Story("Navegación a política")
    @Description("Este test verifica que al hacer clic en 'Política', el usuario es redirigido a la URL correcta.")
    void alHacerClicEnElEnlaceDePolitica_deberiaNavegarALaSeccionCorrecta() {
        step("Paso 1: Navegar a la página principal y aceptar cookies");
        homePage.navegar();

        step("Paso 2: Verificar el título de la página principal");
        String expectedTitle = "elDiario.es - Noticias de actualidad - Periodismo a pesar de todo";
        assertEquals(expectedTitle, homePage.getTitulo());

        step("Paso 3: Hacer clic en la sección 'Política'");
        homePage.clicPolitica();

        step("Paso 4: Verificar que la URL es la correcta");
        assertTrue(homePage.getUrlActual().contains("politica"));

        step("Paso 5: Tomar captura de pantalla final");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/resultado_eldiario_politica.png")));
    }
}