package cl.duoc.demoreclamos.TestReclamos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.duoc.demoreclamos.model.Reclamos;
import cl.duoc.demoreclamos.repository.ReclamosRepository;
import cl.duoc.demoreclamos.service.ReclamosService;

public class TestReclamosService {

    @Mock
    private ReclamosRepository reclamosrepository;

    @InjectMocks
    private ReclamosService reclamosservice;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodo(){

        List<Reclamos> lista = new ArrayList<>();

        Reclamos Reclamo1 = new Reclamos();
        Reclamos Reclamo2 = new Reclamos();

        Reclamo1.setId_reclamo(1);
        Reclamo1.setFecha_reclamo("01/01/2025");
        Reclamo1.setEstado("activo");
        Reclamo1.setDescripcion("mala aplicacion");


        Reclamo2.setId_reclamo(2);
        Reclamo2.setFecha_reclamo("02/02/2025");
        Reclamo2.setEstado("revisado");
        Reclamo2.setDescripcion("muy buena aplicacion");


        lista.add(Reclamo1);
        lista.add(Reclamo2);

        when(reclamosrepository.findAll()).thenReturn(lista);

        List<Reclamos> resultadoBusqueda = reclamosservice.BuscarReclamos();

        assertEquals(2, resultadoBusqueda.size());
        verify(reclamosrepository, times(1)).findAll();
    }

    @Test
    public void testBuscarUnReclamo(){
        Reclamos Reclamo1 = new Reclamos();
        Reclamo1.setId_reclamo(1);
        Reclamo1.setFecha_reclamo("01/01/2025");
        Reclamo1.setEstado("activo");
        Reclamo1.setDescripcion("mala aplicacion");



        when(reclamosrepository.findById(1)).thenReturn(Optional.of(Reclamo1));

        Reclamos Reclamobuscado = reclamosservice.BuscarunReclamo(1);
        assertEquals(1, Reclamobuscado.getId_reclamo());
        verify(reclamosrepository, times(1)).findById(1);
    }

    @Test
    public void testGuardarReclamos(){
        Reclamos Reclamo1 = new Reclamos();
        Reclamo1.setId_reclamo(1);
        Reclamo1.setFecha_reclamo("01/01/2025");
        Reclamo1.setEstado("activo");
        Reclamo1.setDescripcion("mala aplicacion");


        when(reclamosrepository.save(Reclamo1)).thenReturn(Reclamo1);

        Reclamos ReclamosGuardado = reclamosservice.Guardarreclamo(Reclamo1);

        assertEquals(1, ReclamosGuardado.getId_reclamo());
        verify(reclamosrepository, times(1)).save(Reclamo1);

    }

}
