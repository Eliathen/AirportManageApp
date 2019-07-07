package validators;

import exceptions.WrongPeselException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeselValidatorTest {


    @Test
    void validate() {
        PeselValidator peselValidator = new PeselValidator();
        assertThrows(WrongPeselException.class, ()->{
            String pesel = "46080912152";
            peselValidator.validate(pesel);
        });
    }
}