package lnct.project.ECommerce.services;

import lnct.project.ECommerce.payload.CartDetailDto;
import lnct.project.ECommerce.payload.CartHelp;

public interface CartDetailsService {
    public CartDetailDto addProduct(CartHelp cartHelp);
}
