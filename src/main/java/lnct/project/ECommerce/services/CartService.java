package lnct.project.ECommerce.services;


import lnct.project.ECommerce.payload.CartDto;
import lnct.project.ECommerce.payload.CartHelp;

public interface CartService {

    //Create
    CartDto CreateCart(CartHelp cartHelp);

    //add Product To Cart
    CartDto addProductToCart(CartHelp cartHelp);

    //Get
    CartDto GetCart(String userEmail);

    //delete product

    void RemoveById(Integer ProductId,String userEmail);

    //delete


}
