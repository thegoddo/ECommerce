package lnct.project.ECommerce.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private int Userid;

    private String Name;
    private String Email;
    private String rawPassword;
    private String Contact;


    private Date date;

    private String Role;

//     private CartDto cart;

}
