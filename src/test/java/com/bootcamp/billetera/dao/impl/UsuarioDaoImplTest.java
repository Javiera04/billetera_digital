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

import com.bootcamp.billetera.dao.CuentaDao;
import com.bootcamp.billetera.mapper.UsuarioRowMapper;
import com.bootcamp.billetera.model.Cuenta;
import com.bootcamp.billetera.model.Usuario;

import org.springframework.dao.DataAccessException;

@ExtendWith(MockitoExtension.class)
class UsuarioDaoImplTest {

	@Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private CuentaDao cuentaDao;

    @InjectMocks
    private UsuarioDaoImpl usuarioDao;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setUsername("testuser");
        usuario.setPassword("password");
        usuario.setNombre("Test");
        usuario.setApellido("User");
        usuario.setEmail("testuser@example.com");
    }

    @Test
    public void testCrear_Success() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(1);
        when(cuentaDao.crear(any(Cuenta.class))).thenReturn(true);
        when(cuentaDao.obtenerNumeroCuenta(anyInt())).thenReturn(false);

        boolean result = usuarioDao.crear(usuario);

        assertTrue(result);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), eq(Integer.class));
        verify(cuentaDao, times(1)).crear(any(Cuenta.class));
    }

    @Test
    public void testCrear_Fail() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenThrow(new DataAccessException("...") {});
        
        boolean result = usuarioDao.crear(usuario);

        assertFalse(result);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
        verify(jdbcTemplate, times(0)).queryForObject(anyString(), eq(Integer.class));
        verify(cuentaDao, times(0)).crear(any(Cuenta.class));
    }
    
    @Test
    public void testObtenerPorId_Success() {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setUsername("testuser");

        when(jdbcTemplate.queryForObject(anyString(), any(UsuarioRowMapper.class), anyInt())).thenReturn(usuario);

        Usuario result = usuarioDao.obtenerPorId(1);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    public void testObtenerPorId_Failure() {
        when(jdbcTemplate.queryForObject(anyString(), any(UsuarioRowMapper.class), anyInt())).thenThrow(new DataAccessException("...") {});

        Usuario result = usuarioDao.obtenerPorId(1);

        assertNull(result);
    }
}


