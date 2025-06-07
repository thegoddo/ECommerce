package lnct.project.ECommerce.services.impl;

import lnct.project.ECommerce.payload.CartDetailDto;
import lnct.project.ECommerce.payload.CartHelp;
import lnct.project.ECommerce.repositories.CartRepo;
import lnct.project.ECommerce.repositories.UserRepo;
import lnct.project.ECommerce.services.CartDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

public class CartDetailsServiceImpl implements CartDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartRepo cartRepo;

    @Override
    public CartDetailDto addProduct(CartHelp cartHelp) {
        int productId = cartHelp.getProductId();
        int quantity = cartHelp.getQuantity();
        String userEmail = cartHelp.getUserEmail();

        // get user

        return null;
    }
}
