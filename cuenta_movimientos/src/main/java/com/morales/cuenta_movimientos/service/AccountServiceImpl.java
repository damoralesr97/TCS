package com.morales.cuenta_movimientos.service;

import com.morales.cuenta_movimientos.config.NonNullBeanProperties;
import com.morales.cuenta_movimientos.dto.AccountDTO;
import com.morales.cuenta_movimientos.dto.mapper.AccountMapper;
import com.morales.cuenta_movimientos.model.Account;
import com.morales.cuenta_movimientos.repository.IAccountRepository;
import com.morales.cuenta_movimientos.service.interfaces.IAccountService;
import com.morales.cuenta_movimientos.utils.MessageUtil;
import com.morales.cuenta_movimientos.utils.Messages;
import com.morales.cuenta_movimientos.utils.exceptions.TCSException;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements IAccountService {

    private final Random random = new Random();

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<AccountDTO> findAll() throws TCSException {
        try {
            List<Account> accountList = this.accountRepository.findByStatusTrue();
            return accountMapper.toDTOList(accountList);
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public AccountDTO findByAccountNumber(String accountNumber) throws TCSException {
        try{
            Optional<Account> account = this.accountRepository.findByAccountNumberAndStatusTrue(accountNumber);
            return this.accountMapper.toDTO(
                    account.orElseThrow(() -> new TCSException(MessageUtil.getMessage(Messages.ACCOUNT_NOT_FOUND)))
            );
        }catch (Exception e){
            throw new TCSException(e.getMessage(),e);
        }
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) throws TCSException {
        try {
            accountDTO.setNumeroCuenta(this.generateUniqueAccountNumber());
            return this.accountMapper.toDTO(this.accountRepository.save(this.accountMapper.toEntity(accountDTO)));
        } catch (ConstraintViolationException | DataIntegrityViolationException ev){
            throw ev;
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public AccountDTO updateByAccountNumber(String accountNumber, AccountDTO accountDTO) throws TCSException {
        try{
            Account account = this.accountRepository.findByAccountNumberAndStatusTrue(
                    accountNumber).orElseThrow(() -> new TCSException(MessageUtil.getMessage(Messages.ACCOUNT_NOT_FOUND))
            );

            BeanUtilsBean beanUtils = new NonNullBeanProperties();
            beanUtils.copyProperties(account, this.accountMapper.toEntity(accountDTO));

            return this.accountMapper.toDTO(this.accountRepository.save(account));
        }catch (Exception e){
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteByAccountNumber(String accountNumber) throws TCSException {
        try {
            Account account = this.accountRepository.findByAccountNumberAndStatusTrue(
                    accountNumber).orElseThrow(() -> new TCSException(MessageUtil.getMessage(Messages.ACCOUNT_NOT_FOUND))
            );

            account.setStatus(Boolean.FALSE);
            this.accountRepository.save(account);

            return Boolean.TRUE;
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = String.valueOf(100000 + random.nextInt(900000));
        } while (this.accountRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }

}
