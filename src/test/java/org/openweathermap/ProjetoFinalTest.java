package org.openweathermap.driver;

import org.openweathermap.driver.Api;
import org.openweathermap.driver.Browser;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjetoFinalTest {


    @BeforeAll
    static void inicio() {
        Browser.abrirChrome("https://openweathermap.org/");
    }

    @Test
    @Order(2)
    void validarSite() {
        boolean validate = Browser.elementoExiste(By.xpath("//*[@id='weather-widget']//input"));
        assertTrue(validate);
        System.out.println("Validado que estamos no site do OpenWeather");
    }

    @Test
    @Order(3)
    void pesquisar()  {
        Browser.digitar(By.xpath("//div[@id='weather-widget']//input"), "London");
        Browser.clicar(By.xpath("//button[text()='Search']"));
        System.out.println("Validado que estamos na pagina de pesquisa do OpenWeather");
    }

    @Test
    @Order(1)
    void validarNomeDaCidade()  {
        String cidade = "London";
        String valorApi = Api.currentWeather(cidade);
        assertEquals(cidade,valorApi);
        System.out.printf("Validado que retornou o valor da cidade: %s solicitado\n",valorApi);
    }

    @AfterAll
    static void fim() {
        Browser.fecharChrome();
    }


}
