package cl.duoc.demoreclamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.demoreclamos.model.Reclamos;
import cl.duoc.demoreclamos.repository.ReclamosRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional

public class ReclamosService {
    @Autowired
    private ReclamosRepository reclamosrepository;

    public List<Reclamos> BuscarReclamos(){
        return reclamosrepository.findAll();
    }

    public Reclamos BuscarunReclamo(Long id_reclamo){
        return reclamosrepository.findById(id_reclamo).get();
    }

    public Reclamos Guardarreclamo(Reclamos reclamo){
        return reclamosrepository.save(reclamo);
    }

    

}
