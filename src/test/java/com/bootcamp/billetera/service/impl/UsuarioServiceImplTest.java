package com.bootcamp.billetera.service.impl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bootcamp.billetera.dao.UsuarioDao;
import com.bootcamp.billetera.model.Usuario;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @Mock
    private UsuarioDao usuarioDao;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    public void testCrear_Success() {
        Usuario usuario = new Usuario();
        when(usuarioDao.crear(any(Usuario.class))).thenReturn(true);

        boolean result = usuarioService.crear(usuario);

        assertTrue(result);
        verify(usuarioDao, times(1)).crear(any(Usuario.class));
    }

    @Test
    public void testCrear_Failure() {
        Usuario usuario = new Usuario();
        when(usuarioDao.crear(any(Usuario.class))).thenThrow(new RuntimeException("..."));

        boolean result = usuarioService.crear(usuario);

        assertFalse(result);
        verify(usuarioDao, times(1)).crear(any(Usuario.class));
    }

    @Test
    public void testObtenerPorUser_Success() {
        Usuario mockUsuario = new Usuario();
        when(usuarioDao.obtenerPorUser(anyString())).thenReturn(mockUsuario);

        Usuario usuario = usuarioService.obtenerPorUser("testuser");

        assertNotNull(usuario);
        verify(usuarioDao, times(1)).obtenerPorUser(anyString());
    }

    @Test
    public void testObtenerPorUser_Failure() {
        when(usuarioDao.obtenerPorUser(anyString())).thenReturn(null);

        Usuario usuario = usuarioService.obtenerPorUser("testuser");

        assertNull(usuario);
        verify(usuarioDao, times(1)).obtenerPorUser(anyString());
    }

    @Test
    public void testObtenerPorId_Success() {
        Usuario mockUsuario = new Usuario();
        when(usuarioDao.obtenerPorId(anyInt())).thenReturn(mockUsuario);

        Usuario usuario = usuarioService.obtenerPorId(1);

        assertNotNull(usuario);
        verify(usuarioDao, times(1)).obtenerPorId(anyInt());
    }

    @Test
    public void testObtenerPorId_Failure() {
        when(usuarioDao.obtenerPorId(anyInt())).thenReturn(null);

        Usuario usuario = usuarioService.obtenerPorId(1);

        assertNull(usuario);
        verify(usuarioDao, times(1)).obtenerPorId(anyInt());
    }

    @Test
    public void testObtenerPorNroCuenta_Success() {
        Usuario mockUsuario = new Usuario();
        when(usuarioDao.obtenerPorNroCuenta(anyInt())).thenReturn(mockUsuario);

        Usuario usuario = usuarioService.obtenerPorNroCuenta(123456);

        assertNotNull(usuario);
        verify(usuarioDao, times(1)).obtenerPorNroCuenta(anyInt());
    }

    @Test
    public void testObtenerPorNroCuenta_Failure() {
        when(usuarioDao.obtenerPorNroCuenta(anyInt())).thenReturn(null);

        Usuario usuario = usuarioService.obtenerPorNroCuenta(123456);

        assertNull(usuario);
        verify(usuarioDao, times(1)).obtenerPorNroCuenta(anyInt());
    }
}

