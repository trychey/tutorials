package com.baeldung.interfaces;

import com.baeldung.model.Account;
import java.util.List;

public interface IAccountRepository {
    Account createAccount(Account account);
    Account getAccount(Long accountNumber);
    List allAccounts();
}
