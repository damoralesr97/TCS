package com.morales.cuenta_movimientos.service.interfaces;

import com.morales.cuenta_movimientos.dto.MovementDTO;
import com.morales.cuenta_movimientos.dto.MovementRequestDTO;
import com.morales.cuenta_movimientos.dto.ReportDTO;
import com.morales.cuenta_movimientos.utils.exceptions.TCSException;

import java.util.List;

public interface IMovementService {

    /**
     * Obtener todos los movimientos
     * @return List<MovementDTO>
     * @throws TCSException Excepcion Controlada
     */
    List<MovementDTO> findAll() throws TCSException;

    /**
     * Retorna un movimiento, buscando por id
     * @param id id del movimiento
     * @return MovementDTO
     * @throws TCSException Excepcion Controlada
     */
    MovementDTO findById(Integer id) throws TCSException;

    /**
     * Registrar moviemiento nueva
     * @param request Movimiento a crear
     * @return MovementDTO
     * @throws TCSException Excepcion Controlada
     */
    MovementDTO save(MovementRequestDTO request) throws TCSException;

    /**
     * Actualizar un movimiento si existe, buscando por id
     * @param id id de movimiento
     * @param movementDTO Movimiento a actualizar
     * @return MovementDTO
     * @throws TCSException Excepcion Controlada
     */
    MovementDTO updateById(Integer id, MovementDTO movementDTO) throws TCSException;

    /**
     * Eliminar un movimiento si existe, buscando por id
     * @param id id de movimiento
     * @return Boolean
     * @throws TCSException Excepcion Controlada
     */
    Boolean deleteById(Integer id) throws TCSException;

    /**
     * Generacio de reportes
     * @param dni Identificaion del cliente
     * @param startDate fecha de inicio de busqueda
     * @param endDate fecha final de busqueda
     * @return List<MovementDTO>
     * @throws TCSException Excepcion Controlada
     */
    List<ReportDTO> findMoventsByClientDniBetweenDate(String dni, String startDate, String endDate) throws TCSException;

}
