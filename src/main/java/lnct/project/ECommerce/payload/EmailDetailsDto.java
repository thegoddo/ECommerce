package lnct.project.ECommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




    @Data
    @AllArgsConstructor
    @NoArgsConstructor


// Class
    public class EmailDetailsDto {

        // Class data members
        private String recipient;
        private String msgBody;
        private String subject;
        //private String attachment;

    }
