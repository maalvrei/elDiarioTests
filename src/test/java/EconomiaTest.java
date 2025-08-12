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

@Feature("Navegación desde el home hasta la página de economía")
public class EconomiaTest extends BaseTest {

    ElDiarioHomePage homePage;

    @BeforeEach
    void setupHomePage() { homePage = new ElDiarioHomePage(page); }

    @Step("{0}")
    public void step(String stepName) { }

    @Test
    @Story("Navegación a economía")
    @Description("Este test verifica que al hacer clic en 'Economía', el usuario es redirigido a la URL correcta.")
    void alHacerClicEnElEnlaceDeEconomia_deberiaNavegarALaSeccionCorrecta() {
        step("Paso 1: Navegar a la página principal y aceptar cookies");
        homePage.navegar();

        step("Paso 2: Verificar el título de la página principal");
        String expectedTitle = "elDiario.es - Noticias de actualidad - Periodismo a pesar de todo";
        assertEquals(expectedTitle, homePage.getTitulo());

        step("Paso 3: Hacer clic en la sección 'Política'");
        homePage.clicEconomia();

        step("Paso 4: Verificar que la URL es la correcta");
        assertTrue(homePage.getUrlActual().contains("economia"));

        step("Paso 5: Tomar captura de pantalla final");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/resultado_eldiario_economia.png")));
    }
}
