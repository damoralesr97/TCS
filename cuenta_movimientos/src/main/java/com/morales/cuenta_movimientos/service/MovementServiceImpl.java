package com.morales.cuenta_movimientos.service;

import com.morales.cuenta_movimientos.config.NonNullBeanProperties;
import com.morales.cuenta_movimientos.dto.MovementDTO;
import com.morales.cuenta_movimientos.dto.MovementRequestDTO;
import com.morales.cuenta_movimientos.dto.ReportDTO;
import com.morales.cuenta_movimientos.dto.mapper.MovementMapper;
import com.morales.cuenta_movimientos.dto.mapper.ReportMapper;
import com.morales.cuenta_movimientos.model.Account;
import com.morales.cuenta_movimientos.model.Movement;
import com.morales.cuenta_movimientos.repository.IAccountRepository;
import com.morales.cuenta_movimientos.repository.IMovementRepository;
import com.morales.cuenta_movimientos.service.interfaces.IMovementService;
import com.morales.cuenta_movimientos.utils.DateUtils;
import com.morales.cuenta_movimientos.utils.MessageUtil;
import com.morales.cuenta_movimientos.utils.Messages;
import com.morales.cuenta_movimientos.utils.enums.MovementTypeEnum;
import com.morales.cuenta_movimientos.utils.exceptions.TCSException;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovementServiceImpl implements IMovementService {

    @Autowired
    private IMovementRepository movementRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private MovementMapper movementMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<MovementDTO> findAll() throws TCSException {
        try {
            List<Movement> movementList = this.movementRepository.findAll();
            return this.movementMapper.toMovementDtos(movementList);
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public MovementDTO findById(Integer id) throws TCSException {
        try{
            Optional<Movement> movement = this.movementRepository.findById(id);
            return this.movementMapper.toMovementDto(
                    movement.orElseThrow(() -> new TCSException(MessageUtil.getMessage(Messages.MOVEMENT_NOT_FOUND)))
            );
        }catch (Exception e){
            throw new TCSException(e.getMessage(),e);
        }
    }

    @Override
    public MovementDTO save(MovementRequestDTO request) throws TCSException {
        try {
            Account account = this.validateAccount(request.getNumeroCuenta());

            BigDecimal balance;

            if (CollectionUtils.isEmpty(account.getMovements())) {
                balance = account.getInitialBalance();
            } else {
                balance = account.getMovements().stream().max(Comparator.comparing(Movement::getMovementDate)).get().getBalance();
            }

            MovementTypeEnum movementType;
            if (request.getValor().signum() > 0) {
                movementType = MovementTypeEnum.DEPOSITO;
            } else if (request.getValor().signum() < 0) {
                movementType = MovementTypeEnum.RETIRO;
                if (request.getValor().abs().compareTo(balance) > 0)
                    throw new TCSException(MessageUtil.getMessage(Messages.INSUFFICIENT_BALANCE));
            } else {
                throw new TCSException(MessageUtil.getMessage(Messages.MOVEMENT_ZERO));
            }
            Movement movement = Movement.builder()
                    .movementDate(new Date())
                    .movementType(movementType)
                    .value(request.getValor())
                    .balance(balance.add(request.getValor()))
                    .account(account)
                    .build();

            return this.movementMapper.toMovementDto(this.movementRepository.save(movement));
        } catch (ConstraintViolationException | DataIntegrityViolationException ev){
            throw ev;
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public MovementDTO updateById(Integer id, MovementDTO movementDTO) throws TCSException {
        try{
            Movement movement = this.movementRepository.findById(
                    id).orElseThrow(() -> new TCSException(MessageUtil.getMessage(Messages.MOVEMENT_NOT_FOUND))
            );

            BeanUtilsBean beanUtils = new NonNullBeanProperties();
            beanUtils.copyProperties(movement, this.movementMapper.toMovement(movementDTO));

            return this.movementMapper.toMovementDto(this.movementRepository.save(movement));
        }catch (Exception e){
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteById(Integer id) throws TCSException {
        try {
            this.movementRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public List<ReportDTO> findMoventsByClientDniBetweenDate(String dni, String startDate, String endDate) throws TCSException {
        try {
            List<Movement> movementList = this.movementRepository.findByAccountClientDniAndMovementDateIsBetween(
                    dni,
                    DateUtils.convertStringToDate(startDate, Boolean.TRUE),
                    DateUtils.convertStringToDate(endDate, Boolean.FALSE)
            );
            return this.reportMapper.toReportDtos(movementList);
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    private Account validateAccount(String accountNumber) throws TCSException {
        if (StringUtils.isBlank(accountNumber))
            throw new TCSException(MessageUtil.getMessage(Messages.REQUIRED_ACCOUNT));
        Optional<Account> account = this.accountRepository.findByAccountNumberAndStatusTrue(accountNumber);
        if (account.isEmpty())
            throw new TCSException(MessageUtil.getMessage(Messages.ACCOUNT_NOT_FOUND));
        return account.get();
    }

}
