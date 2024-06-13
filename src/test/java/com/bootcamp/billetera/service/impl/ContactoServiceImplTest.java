package com.bootcamp.billetera.service.impl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bootcamp.billetera.dao.ContactoDao;
import com.bootcamp.billetera.model.Contacto;

@ExtendWith(MockitoExtension.class)
public class ContactoServiceImplTest {

    @Mock
    private ContactoDao contactoDao;

    @InjectMocks
    private ContactoServiceImpl contactoService;

    @Test
    public void testCrear_Success() {
        when(contactoDao.crear(eq(1), eq(2))).thenReturn(true);

        boolean result = contactoService.crear(1, 2);

        assertTrue(result);
        verify(contactoDao, times(1)).crear(eq(1), eq(2));
    }

    @Test
    public void testCrear_Failure_SameIds() {
        boolean result = contactoService.crear(1, 1);

        assertFalse(result);
        verify(contactoDao, never()).crear(anyInt(), anyInt());
    }

    @Test
    public void testCrear_Failure_DaoException() {
        when(contactoDao.crear(eq(1), eq(2))).thenThrow(new RuntimeException("DB connection error"));

        boolean result = contactoService.crear(1, 2);

        assertFalse(result);
        verify(contactoDao, times(1)).crear(eq(1), eq(2));
    }

    @Test
    public void testEliminar_Success() {
        when(contactoDao.eliminar(eq(1), eq(2))).thenReturn(true);

        boolean result = contactoService.eliminar(1, 2);

        assertTrue(result);
        verify(contactoDao, times(1)).eliminar(eq(1), eq(2));
    }

    @Test
    public void testEliminar_Failure_DaoException() {
        when(contactoDao.eliminar(eq(1), eq(2))).thenThrow(new RuntimeException("DB connection error"));

        boolean result = contactoService.eliminar(1, 2);

        assertFalse(result);
        verify(contactoDao, times(1)).eliminar(eq(1), eq(2));
    }

    @Test
    public void testObtenerContactos_Success() {
        List<Contacto> mockContactos = new ArrayList<>();
        Contacto contacto = new Contacto();
        contacto.setId_usuario(1);
        contacto.setId_contacto(2);
        contacto.setNombre("Usuario1");
        contacto.setApellido("Apellido1");
        contacto.setEmail("usuario1@example.com");
        
        mockContactos.add(contacto);

        when(contactoDao.obtenerContactos(eq(1))).thenReturn(mockContactos);

        List<Contacto> result = contactoService.obtenerContactos(1);

        assertEquals(1, result.size());
        Contacto contacto1 = result.get(0);
        assertEquals(1, contacto1.getId_usuario());
        assertEquals(2, contacto1.getId_contacto());
        assertEquals("Usuario1", contacto1.getNombre());
        assertEquals("Apellido1", contacto1.getApellido());
        assertEquals("usuario1@example.com", contacto1.getEmail());

        verify(contactoDao, times(1)).obtenerContactos(eq(1));
    }

    @Test
    public void testObtenerContactos_EmptyList() {
        when(contactoDao.obtenerContactos(anyInt())).thenReturn(new ArrayList<>());

        List<Contacto> result = contactoService.obtenerContactos(1);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(contactoDao, times(1)).obtenerContactos(eq(1));
    }


}
