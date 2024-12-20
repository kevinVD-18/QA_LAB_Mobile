package com.nttdata.stepsDefinitions;

import com.nttdata.steps.ProductSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class ProductStepsDefs {
    @Steps
    ProductSteps productSteps;
    int qtity;

    @Given("estoy en la aplicación de SauceLabs")
    public void queEstoyLaAplicacionDeSwagLabs() {
        productSteps.logoIsDisplayed();
    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void validoQueCarguenCorrectamenteLosProductosEnLaGaleria() {
        productSteps.validateListProduct();
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agregoDelSiguienteProducto(int cantidad, String producto) {
        qtity = cantidad;
        productSteps.addProductToCart(producto);
    }

    @Then("valido el carrito de compra actualice correctamente")
    public void validoElCarritoDeCompraActualiceCorrectamente() {
        productSteps.validateCart(qtity);
    }
}
