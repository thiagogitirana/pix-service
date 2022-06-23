package com.itau.pixservice.domain.entities;

import com.itau.pixservice.domain.entities.enums.PersonType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class Client {

    private String firstName;
    private String lastName;
    private PersonType personType;
    private List<Account> accounts;

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Client)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Client c = (Client) o;

        // Compare the data members and return accordingly
        return firstName.equals(c.firstName)
                && lastName.equals(c.lastName);
    }

}
