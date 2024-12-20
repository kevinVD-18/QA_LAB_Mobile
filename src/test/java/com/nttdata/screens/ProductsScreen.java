package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsScreen extends PageObject {

    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Displays all products of catalog\"]/android.view.ViewGroup[1]")
    private List<WebElement> listProducts;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"App logo and name\"]")
    private WebElement lblLogo;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/priceTV\"]")
    private WebElement lblPriceProduct;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    private WebElement btnAdd;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]")
    private WebElement btnAddCart;
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Displays number of items in your cart\"]")
    private WebElement btnCart;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/priceTV\"]")
    private WebElement lblPriceProductCart;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/totalPriceTV\"]")
    private WebElement lblTotalProduct;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/noTV\"]")
    private WebElement lblQantity;

    public boolean isProductPresent() {
        return listProducts != null && !listProducts.isEmpty();
    }

    public boolean isLogoPresent() {
        waitFor(ExpectedConditions.visibilityOf(lblLogo));
        return lblLogo.isDisplayed();
    }

    public void selectProduct(String productContentDesc) {
        WebDriver driver = getDriver();
        WebElement productElement = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='" + productContentDesc + "']"));
        productElement.click();
    }

    public double convertText(String valor) {
        return Double.parseDouble(valor.replaceAll("[^0-9.]", ""));
    }

    public void validateCart(int quantity) {

        //obtener texto del precio del producto
        Double lblPriceProductValue = convertText(lblPriceProduct.getText());

        //hacer click a cantidad de producto
        for (int i = 0; i < quantity - 1; i++) {
            btnAdd.click();
        }

        btnAddCart.click();
        btnCart.click();

        //obtener texto del precio del producto en el cart
        Double lblPriceProductCarValue = convertText(lblPriceProductCart.getText());

        //obtener texto del total de productos en el cart
        Double lblTotalProductValue = convertText(lblTotalProduct.getText());

        //obtener texto de cantidad de producto en el cart
        int lblQantityValue = Integer.parseInt(lblQantity.getText());

        validateQuantityCart("validating product quantity", quantity, lblQantityValue);
        validateValueCart("Validating product price", lblPriceProductValue, lblPriceProductCarValue);
        validateValueTotalCart("Validating total product price", lblPriceProductCarValue, lblQantityValue,  lblTotalProductValue);

    }
    public void validateValueCart( String message, Double precioProduct , Double precioProductCart) {
        Assert.assertEquals(message,precioProduct,precioProductCart);
    }
    public void validateQuantityCart( String message, int qty , int qtyCart) {
        Assert.assertEquals(message,qty,qtyCart);
    }
    public void validateValueTotalCart( String message, Double precioProduct , int qty , Double precioTotal) {
        double delta = 0.01;
        Assert.assertEquals(message,precioTotal,precioProduct * qty,delta);
    }

}
