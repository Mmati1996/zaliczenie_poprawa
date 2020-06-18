package edu.iis.mto.testreactor.atm;

import edu.iis.mto.testreactor.atm.bank.AccountException;
import edu.iis.mto.testreactor.atm.bank.AuthorizationException;
import edu.iis.mto.testreactor.atm.bank.AuthorizationToken;
import edu.iis.mto.testreactor.atm.bank.Bank;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;

import static org.junit.Assert.assertThat;

public class ATMachineTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void itCompiles() {
        assertThat(true, Matchers.equalTo(true));
    }

    @Test(expected = Exception.class)
    public void tryingToWithdrawWithNoMoney(){
        PinCode pin = PinCode.createPIN(1,2,3,4);
        Card card = Card.create("123-456");
        Money money = new Money(0,Currency.getInstance("PLN"));
        Bank bank = new Bank() {
            @Override
            public AuthorizationToken autorize(String pin, String cardNumber) throws AuthorizationException {
                return null;
            }

            @Override
            public void charge(AuthorizationToken token, Money amount) throws AccountException {

            }
        };
        Currency curr = Currency.getInstance("PLN");
        ATMachine atm= new ATMachine(bank,curr);
        try{
            atm.withdraw(pin,card,new Money(10,Currency.getInstance("PLN")));
        }catch (Exception e){
        }

    }





}
