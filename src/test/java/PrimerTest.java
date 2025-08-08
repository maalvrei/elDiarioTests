import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import java.nio.file.Paths;
import com.test.base.BaseTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eldiarioTest.pages.ElDiarioHomePage; // <-- Usamos el Page Object correcto

public class PrimerTest extends BaseTest {

    ElDiarioHomePage homePage; // <-- Usamos la clase correcta

    @BeforeEach
    void setupHomePage() {
        homePage = new ElDiarioHomePage(page); // 'page' ya está inicializada por BaseTest
    }

    @Test
    void alHacerClicEnElEnlaceDePolitica_deberiaNavegarALaSeccionCorrecta() {
        homePage.navegar();

        String expectedTitle = "elDiario.es - Noticias de actualidad - Periodismo a pesar de todo";
        assertEquals(expectedTitle, homePage.getTitulo());

        homePage.clicPolitica();

        assertTrue(homePage.getUrlActual().contains("politica"));

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/resultado_eldiario_politica.png")));
    }
}