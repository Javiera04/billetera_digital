package com.bootcamp.billetera.dao.impl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bootcamp.billetera.mapper.TransaccionRowMapper;
import com.bootcamp.billetera.model.Transaccion;

import org.springframework.dao.DataAccessException;

@ExtendWith(MockitoExtension.class)
public class TransaccionDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TransaccionDaoImpl transaccionDao;

    private Transaccion transaccion;

    @BeforeEach
    public void setUp() {
        transaccion = new Transaccion();
        transaccion.setId_cuenta_origen(1);
        transaccion.setId_cuenta_destino(2);
        transaccion.setFecha_transaccion(new java.util.Date());
        transaccion.setMonto(100);
        transaccion.setId_tipo(1);
    }

    @Test
    public void testCrear_Success() {
        when(jdbcTemplate.update(anyString(), anyInt(), any(Date.class), anyInt(), anyInt())).thenReturn(1);

        boolean result = transaccionDao.crear(transaccion);

        assertTrue(result);
        verify(jdbcTemplate, times(1)).update(anyString(), 
                                               eq(transaccion.getId_cuenta_origen()), 
                                               eq(transaccion.getFecha_transaccion()), 
                                               eq(transaccion.getMonto()), 
                                               eq(transaccion.getId_tipo()));
    }

    @Test
    public void testCrear_Failure() {
        when(jdbcTemplate.update(anyString(), anyInt(), any(Date.class), anyInt(), anyInt())).thenThrow(new DataAccessException("...") {});

        boolean result = transaccionDao.crear(transaccion);

        assertFalse(result);
        verify(jdbcTemplate, times(1)).update(anyString(), 
                                               eq(transaccion.getId_cuenta_origen()), 
                                               eq(transaccion.getFecha_transaccion()), 
                                               eq(transaccion.getMonto()), 
                                               eq(transaccion.getId_tipo()));
    }
    
    @Test
    public void testObtenerTransacciones_Success() {
        Transaccion transaccion = new Transaccion();
        transaccion.setId_transaccion(1);
        transaccion.setId_cuenta_origen(1);
        transaccion.setId_cuenta_destino(2);
        transaccion.setFecha_transaccion(new java.util.Date());
        transaccion.setMonto(100);
        transaccion.setId_tipo(1);

        when(jdbcTemplate.query(anyString(), any(TransaccionRowMapper.class), anyString())).thenReturn(Collections.singletonList(transaccion));

        List<Transaccion> result = transaccionDao.obtenerTransacciones("testuser");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId_transaccion());
    }

    @Test
    public void testObtenerTransacciones_Failure() {
        when(jdbcTemplate.query(anyString(), any(TransaccionRowMapper.class), anyString())).thenThrow(new RuntimeException("..."));

        List<Transaccion> result = transaccionDao.obtenerTransacciones("testuser");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
    @Test
    public void testCrearTransaccionDestino_Success() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt(), any(Date.class), anyInt(), anyInt())).thenReturn(1);

        boolean result = transaccionDao.crearTransaccionDestino(transaccion);

        assertTrue(result);
        verify(jdbcTemplate, times(1)).update(anyString(), 
                                               eq(transaccion.getId_cuenta_origen()), 
                                               eq(transaccion.getId_cuenta_destino()), 
                                               eq(transaccion.getFecha_transaccion()), 
                                               eq(transaccion.getMonto()), 
                                               eq(transaccion.getId_tipo()));
    }

    @Test
    public void testCrearTransaccionDestino_Failure() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt(), any(Date.class), anyDouble(), anyInt())).thenThrow(new DataAccessException("...") {});

        boolean result = transaccionDao.crearTransaccionDestino(transaccion);

        assertFalse(result);
        verify(jdbcTemplate, times(1)).update(anyString(), 
                                               eq(transaccion.getId_cuenta_origen()), 
                                               eq(transaccion.getId_cuenta_destino()), 
                                               eq(transaccion.getFecha_transaccion()), 
                                               eq(transaccion.getMonto()), 
                                               eq(transaccion.getId_tipo()));
    }
}

