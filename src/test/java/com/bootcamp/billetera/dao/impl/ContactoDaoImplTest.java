package com.bootcamp.billetera.dao.impl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bootcamp.billetera.model.Contacto;

import org.springframework.dao.DataAccessException;

@ExtendWith(MockitoExtension.class)
public class ContactoDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ContactoDaoImpl contactoDao;

    @Test
    public void testCrear_Success() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt())).thenReturn(1);

        boolean result = contactoDao.crear(1, 2);

        assertTrue(result);
        verify(jdbcTemplate, times(1)).update(anyString(), eq(1), eq(2));
    }

    @Test
    public void testCrear_Failure() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt())).thenThrow(new DataAccessException("...") {});

        boolean result = contactoDao.crear(1, 2);

        assertFalse(result);
        verify(jdbcTemplate, times(1)).update(anyString(), eq(1), eq(2));
    }
    
    @Test
    public void testEliminar_Success() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt())).thenReturn(1);

        boolean result = contactoDao.eliminar(1, 2);

        assertTrue(result);
        verify(jdbcTemplate, times(1)).update(anyString(), eq(1), eq(2));
    }

    @Test
    public void testEliminar_Failure() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt())).thenThrow(new DataAccessException("...") {});

        boolean result = contactoDao.eliminar(1, 2);

        assertFalse(result);
        verify(jdbcTemplate, times(1)).update(anyString(), eq(1), eq(2));
    }
    
    @Test
    public void testObtenerContactos_Success() {
        List<Contacto> mockContactos = List.of(new Contacto());
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), anyInt())).thenReturn(mockContactos);

        List<Contacto> contactos = contactoDao.obtenerContactos(1);

        assertNotNull(contactos);
        assertFalse(contactos.isEmpty());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class), eq(1));
    }

    @Test
    public void testObtenerContactos_Failure() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), anyInt())).thenThrow(new DataAccessException("...") {});

        List<Contacto> contactos = contactoDao.obtenerContactos(1);

        assertNotNull(contactos);
        assertTrue(contactos.isEmpty());
        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class), eq(1));
    }
}
