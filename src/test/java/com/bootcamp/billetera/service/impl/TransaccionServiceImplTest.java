package com.bootcamp.billetera.service.impl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bootcamp.billetera.dao.TransaccionDao;
import com.bootcamp.billetera.model.Transaccion;

@ExtendWith(MockitoExtension.class)
public class TransaccionServiceImplTest {

    @Mock
    private TransaccionDao transaccionDao;

    @InjectMocks
    private TransaccionServiceImpl transaccionService;

    @Test
    public void testCrear_Success() {
        Transaccion transaccion = new Transaccion();
        when(transaccionDao.crear(any(Transaccion.class))).thenReturn(true);

        boolean result = transaccionService.crear(transaccion);

        assertTrue(result);
        verify(transaccionDao, times(1)).crear(any(Transaccion.class));
    }

    @Test
    public void testCrear_Failure() {
        Transaccion transaccion = new Transaccion();
        when(transaccionDao.crear(any(Transaccion.class))).thenThrow(new RuntimeException("..."));

        boolean result = transaccionService.crear(transaccion);

        assertFalse(result);
        verify(transaccionDao, times(1)).crear(any(Transaccion.class));
    }

    @Test
    public void testObtenerTransacciones_Success() {
        List<Transaccion> mockTransacciones = List.of(new Transaccion());
        when(transaccionDao.obtenerTransacciones(anyString())).thenReturn(mockTransacciones);

        List<Transaccion> transacciones = transaccionService.obtenerTransacciones("testuser");

        assertNotNull(transacciones);
        assertFalse(transacciones.isEmpty());
        verify(transaccionDao, times(1)).obtenerTransacciones(anyString());
    }

    @Test
    public void testObtenerTransacciones_Failure() {
        when(transaccionDao.obtenerTransacciones(anyString())).thenReturn(Collections.emptyList());

        List<Transaccion> transacciones = transaccionService.obtenerTransacciones("testuser");

        assertNotNull(transacciones);
        assertTrue(transacciones.isEmpty());
        verify(transaccionDao, times(1)).obtenerTransacciones(anyString());
    }

    @Test
    public void testCrearTransaccionDestino_Success() {
        Transaccion transaccion = new Transaccion();
        when(transaccionDao.crearTransaccionDestino(any(Transaccion.class))).thenReturn(true);

        boolean result = transaccionService.crearTransaccionDestino(transaccion);

        assertTrue(result);
        verify(transaccionDao, times(1)).crearTransaccionDestino(any(Transaccion.class));
    }

    @Test
    public void testCrearTransaccionDestino_Failure() {
        Transaccion transaccion = new Transaccion();
        when(transaccionDao.crearTransaccionDestino(any(Transaccion.class))).thenThrow(new RuntimeException("..."));

        boolean result = transaccionService.crearTransaccionDestino(transaccion);

        assertFalse(result);
        verify(transaccionDao, times(1)).crearTransaccionDestino(any(Transaccion.class));
    }
}

