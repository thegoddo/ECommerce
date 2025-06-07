package lnct.project.ECommerce.services;

import lnct.project.ECommerce.payload.CartDetailDto;
import lnct.project.ECommerce.payload.CartHelp;
import org.springframework.stereotype.Service;

@Service
public interface CartDetailsService {

    //add product
    public CartDetailDto addProduct(CartHelp cartHelp);
}
