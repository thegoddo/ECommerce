package lnct.project.ECommerce.services;

import lnct.project.ECommerce.payload.CartDto;
import lnct.project.ECommerce.payload.CartHelp;

public interface CartService {
    CartDto CreateCart(CartHelp cartHelp);

    CartDto addProductToCart(CartHelp cartHelp);

    CartDto GetCart(String userEmail);


    void RemoveById(Integer ProductId, String userEmail);
}
