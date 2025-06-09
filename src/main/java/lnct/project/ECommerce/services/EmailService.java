package lnct.project.ECommerce.services;

import lnct.project.ECommerce.payload.EmailDetailsDto;


// Interface
public interface EmailService {

    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetailsDto details);

    // Method
    // To send an email with attachment
    //  String sendMailWithAttachment(EmailDetailsDto details);
}
