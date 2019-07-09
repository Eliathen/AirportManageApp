package validators;

import exceptions.WrongPeselException;

public class PeselValidator {

    private Integer[] value = {1, 3, 7, 9, 1, 3, 7 ,9 ,1 ,3};

    private Integer checksum = 0;

    public PeselValidator() {
    }

    public void validate(String pesel) throws WrongPeselException {
        if(pesel.matches("[0-9]") || pesel.length()!=11 ){
            throw new WrongPeselException("Incorrect format. Pesel must have 11 digits");
        }
        for (int i = 0; i < 10; i++)
        checksum += Integer.parseInt(pesel.substring(i, i+1)) * value[i];
        int checkDigit = Integer.parseInt(pesel.substring(10, 11));
        checksum %= 10;
        checksum = 10 - checksum;
        checksum %= 10;
        if (checksum != checkDigit){
            throw new WrongPeselException("Incorrect PESEL");
        }

    }
}
