package com.bootcamp.billetera.service.impl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bootcamp.billetera.dao.CuentaDao;
import com.bootcamp.billetera.model.Cuenta;

@ExtendWith(MockitoExtension.class)
public class CuentaServiceImplTest {

    @Mock
    private CuentaDao cuentaDao;

    @InjectMocks
    private CuentaServiceImpl cuentaService;

    @Test
    public void testObtenerPorUser_Success() {
        Cuenta cuenta = new Cuenta();
        when(cuentaDao.obtenerCuentaPorUser(anyString())).thenReturn(cuenta);

        Cuenta result = cuentaService.obtenerPorUser("testuser");

        assertNotNull(result);
        verify(cuentaDao, times(1)).obtenerCuentaPorUser(anyString());
    }

    @Test
    public void testRetirar_Success() {
        when(cuentaDao.obtenerSaldo(anyString())).thenReturn(500);

        boolean result = cuentaService.retirar("testuser", 100);

        assertTrue(result);
        verify(cuentaDao, times(1)).obtenerSaldo(anyString());
        verify(cuentaDao, times(1)).actualizarSaldo(anyString(), eq(400));
    }

    @Test
    public void testRetirar_Failure_InsufficientFunds() {
        when(cuentaDao.obtenerSaldo(anyString())).thenReturn(100);

        boolean result = cuentaService.retirar("testuser", 200);

        assertFalse(result);
        verify(cuentaDao, times(1)).obtenerSaldo(anyString());
        verify(cuentaDao, never()).actualizarSaldo(anyString(), anyInt());
    }



    @Test
    public void testDepositar_Success() {
        when(cuentaDao.obtenerSaldo(anyString())).thenReturn(500);

        boolean result = cuentaService.depositar("testuser", 100);

        assertTrue(result);
        verify(cuentaDao, times(1)).obtenerSaldo(anyString());
        verify(cuentaDao, times(1)).actualizarSaldo(anyString(), eq(600));
    }

    @Test
    public void testObtenerPorId_Success() {
        Cuenta cuenta = new Cuenta();
        when(cuentaDao.obtenerCuentaPorId(anyInt())).thenReturn(cuenta);

        Cuenta result = cuentaService.obtenerPorId(1);

        assertNotNull(result);
        verify(cuentaDao, times(1)).obtenerCuentaPorId(anyInt());
    }

    @Test
    public void testTransferir_Success() {
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setSaldo(500);
        Cuenta cuentaDestino = new Cuenta();
        cuentaDestino.setSaldo(200);

        when(cuentaDao.obtenerCuentaPorId(eq(1))).thenReturn(cuentaOrigen);
        when(cuentaDao.obtenerCuentaPorId(eq(2))).thenReturn(cuentaDestino);

        boolean result = cuentaService.transferir(1, 2, 100);

        assertTrue(result);
        verify(cuentaDao, times(1)).obtenerCuentaPorId(eq(1));
        verify(cuentaDao, times(1)).obtenerCuentaPorId(eq(2));
        verify(cuentaDao, times(1)).actualizarSaldoPorId(eq(1), eq(400));
        verify(cuentaDao, times(1)).actualizarSaldoPorId(eq(2), eq(300));
    }

    @Test
    public void testTransferir_Failure_InsufficientFunds() {
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setSaldo(100);
        Cuenta cuentaDestino = new Cuenta();
        cuentaDestino.setSaldo(200);

        when(cuentaDao.obtenerCuentaPorId(eq(1))).thenReturn(cuentaOrigen);
        when(cuentaDao.obtenerCuentaPorId(eq(2))).thenReturn(cuentaDestino);

        boolean result = cuentaService.transferir(1, 2, 200);

        assertFalse(result);
        verify(cuentaDao, times(1)).obtenerCuentaPorId(eq(1));
        verify(cuentaDao, times(1)).obtenerCuentaPorId(eq(2));
        verify(cuentaDao, never()).actualizarSaldoPorId(eq(1), anyInt());
        verify(cuentaDao, never()).actualizarSaldoPorId(eq(2), anyInt());
    }

    @Test
    public void testTransferir_Failure_InvalidAmount() {
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setSaldo(500);
        Cuenta cuentaDestino = new Cuenta();
        cuentaDestino.setSaldo(200);

        when(cuentaDao.obtenerCuentaPorId(eq(1))).thenReturn(cuentaOrigen);
        when(cuentaDao.obtenerCuentaPorId(eq(2))).thenReturn(cuentaDestino);

        boolean result = cuentaService.transferir(1, 2, -100);

        assertFalse(result);
        verify(cuentaDao, times(1)).obtenerCuentaPorId(eq(1));
        verify(cuentaDao, times(1)).obtenerCuentaPorId(eq(2));
        verify(cuentaDao, never()).actualizarSaldoPorId(eq(1), anyInt());
        verify(cuentaDao, never()).actualizarSaldoPorId(eq(2), anyInt());
    }


}

