package com.bootcamp.billetera.dao.impl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bootcamp.billetera.mapper.CuentaRowMapper;
import com.bootcamp.billetera.model.Cuenta;

import org.springframework.dao.DataAccessException;

@ExtendWith(MockitoExtension.class)
public class CuentaDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CuentaDaoImpl cuentaDao;

    private Cuenta cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta();
        cuenta.setNro_cuenta(123456);
        cuenta.setSaldo(1000);
        cuenta.setId_usuario(1);
    }

    @Test
    public void testCrear_Success() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt(), anyInt())).thenReturn(1);

        boolean result = cuentaDao.crear(cuenta);

        assertTrue(result);
        verify(jdbcTemplate, times(1)).update(anyString(), eq(cuenta.getNro_cuenta()), eq(cuenta.getSaldo()), eq(cuenta.getId_usuario()));
    }

    @Test
    public void testCrear_Failure() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt(), anyInt())).thenThrow(new DataAccessException("...") {});

        boolean result = cuentaDao.crear(cuenta);

        assertFalse(result);
        verify(jdbcTemplate, times(1)).update(anyString(), eq(cuenta.getNro_cuenta()), eq(cuenta.getSaldo()), eq(cuenta.getId_usuario()));
    }
    
    @Test
    public void testObtenerNumeroCuenta_Exists() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyInt())).thenReturn(1);

        boolean result = cuentaDao.obtenerNumeroCuenta(123456);

        assertTrue(result);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), eq(Integer.class), eq(123456));
    }

    @Test
    public void testObtenerNumeroCuenta_NotExists() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyInt())).thenReturn(0);

        boolean result = cuentaDao.obtenerNumeroCuenta(123456);

        assertFalse(result);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), eq(Integer.class), eq(123456));
    }
    
    @Test
    public void testObtenerCuentaPorUser_Success() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId_cuenta(1);
        cuenta.setNro_cuenta(123456);
        cuenta.setSaldo(1000);
        cuenta.setId_usuario(1);

        when(jdbcTemplate.queryForObject(anyString(), any(CuentaRowMapper.class), anyString())).thenReturn(cuenta);

        Cuenta result = cuentaDao.obtenerCuentaPorUser("testuser");

        assertNotNull(result);
        assertEquals(1, result.getId_cuenta());
        assertEquals(123456, result.getNro_cuenta());
    }

    @Test
    public void testObtenerCuentaPorUser_Failure() {
        when(jdbcTemplate.queryForObject(anyString(), any(CuentaRowMapper.class), anyString())).thenThrow(new DataAccessException("...") {});

        Cuenta result = cuentaDao.obtenerCuentaPorUser("testuser");

        assertNull(result);
    }
    
    @Test
    public void testObtenerSaldo_Success() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyString())).thenReturn(1000);

        int saldo = cuentaDao.obtenerSaldo("testuser");

        assertEquals(1000, saldo);
    }

    @Test
    public void testObtenerSaldo_Failure() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyString())).thenThrow(new DataAccessException("...") {});

        int saldo = cuentaDao.obtenerSaldo("testuser");

        assertEquals(-1, saldo);
    }
    
    @Test
    public void testActualizarSaldo_Success() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyString())).thenReturn(1);

        cuentaDao.actualizarSaldo("testuser", 2000);

        verify(jdbcTemplate, times(1)).update(anyString(), eq(2000), eq("testuser"));
    }

    @Test
    public void testActualizarSaldo_Failure() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyString())).thenThrow(new DataAccessException("...") {});

        cuentaDao.actualizarSaldo("testuser", 2000);

        verify(jdbcTemplate, times(1)).update(anyString(), eq(2000), eq("testuser"));
    }
    
    @Test
    public void testObtenerCuentaPorId_Success() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId_cuenta(1);
        cuenta.setNro_cuenta(123456);
        cuenta.setSaldo(1000);
        cuenta.setId_usuario(1);

        when(jdbcTemplate.queryForObject(anyString(), any(CuentaRowMapper.class), anyInt())).thenReturn(cuenta);

        Cuenta result = cuentaDao.obtenerCuentaPorId(1);

        assertNotNull(result);
        assertEquals(1, result.getId_cuenta());
        assertEquals(123456, result.getNro_cuenta());
    }

    @Test
    public void testObtenerCuentaPorId_Failure() {
        when(jdbcTemplate.queryForObject(anyString(), any(CuentaRowMapper.class), anyInt())).thenThrow(new DataAccessException("...") {});

        Cuenta result = cuentaDao.obtenerCuentaPorId(1);

        assertNull(result);
    }
    
    @Test
    public void testActualizarSaldoPorId_Success() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt())).thenReturn(1);

        cuentaDao.actualizarSaldoPorId(1, 2000);

        verify(jdbcTemplate, times(1)).update(anyString(), eq(2000), eq(1));
    }

    @Test
    public void testActualizarSaldoPorId_Failure() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt())).thenThrow(new DataAccessException("...") {});

        cuentaDao.actualizarSaldoPorId(1, 2000);

        verify(jdbcTemplate, times(1)).update(anyString(), eq(2000), eq(1));
    }
    
}

