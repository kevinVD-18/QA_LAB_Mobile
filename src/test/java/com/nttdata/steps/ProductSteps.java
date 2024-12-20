package com.nttdata.steps;

import com.nttdata.screens.ProductsScreen;
import org.junit.Assert;

public class ProductSteps {
    ProductsScreen productsScreen;

    public void logoIsDisplayed(){
        Assert.assertTrue( "No se mostró el logo", productsScreen.isLogoPresent());
    }
    public void validateListProduct(){
        Assert.assertTrue( "No se mostró el card de products", productsScreen.isProductPresent());
    }

    public void addProductToCart(String productName){
        productsScreen.selectProduct(productName);

    }

    public void validateCart(int cantidad){
        productsScreen.validateCart(cantidad);
    }

}
