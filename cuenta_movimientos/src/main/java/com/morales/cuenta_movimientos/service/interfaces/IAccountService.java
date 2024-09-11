package com.morales.cuenta_movimientos.service.interfaces;

import com.morales.cuenta_movimientos.dto.AccountDTO;
import com.morales.cuenta_movimientos.utils.exceptions.TCSException;

import java.util.List;

public interface IAccountService {

    /**
     * Obtener todas las cuentas activas
     * @return List<AccountDTO>
     * @throws TCSException Excepcion Controlada
     */
    List<AccountDTO> findAll() throws TCSException;

    /**
     * Retorna una cuenta, buscando por numero de cuenta
     * @param numberAccount numero de la cuenta
     * @return AccountDTO
     * @throws TCSException Excepcion Controlada
     */
    AccountDTO findByAccountNumber(String numberAccount) throws TCSException;

    /**
     * Registrar cuenta nueva
     * @param accountDTO Cuenta a crear
     * @return AccountDTO
     * @throws TCSException Excepcion Controlada
     */
    AccountDTO save(AccountDTO accountDTO) throws TCSException;

    /**
     * Actualizar una cuenta si existe, buscando por numero de cuenta
     * @param numberAccount numero de la cuenta
     * @param accountDTO Cuenta a actualizar
     * @return AccountDTO
     * @throws TCSException Excepcion Controlada
     */
    AccountDTO updateByAccountNumber(String numberAccount, AccountDTO accountDTO) throws TCSException;

    /**
     * Eliminar una cuenta si existe, buscando por numero de cuenta
     * @param numberAccount numero de la cuenta
     * @return Boolean
     * @throws TCSException Excepcion Controlada
     */
    Boolean deleteByAccountNumber(String numberAccount) throws TCSException;

}
