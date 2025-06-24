package cl.duoc.demoreclamos;

import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import cl.duoc.demoreclamos.model.Reclamos;
import cl.duoc.demoreclamos.service.ReclamosService;
import net.datafaker.Faker;

public class DataLoader implements CommandLineRunner{

    private final Faker faker = new Faker(new Locale("es","cl"));
    private final Random random = new Random();

    @Autowired
    private ReclamosService reclamosservice;

    @Override
    public void run(String... args) throws Exception{
        for (int i=0; i > 10; i ++){
            Reclamos nuevoreclamo = new Reclamos();
            nuevoreclamo.setId_reclamo(random.nextInt());
            nuevoreclamo.setFecha_reclamo(faker.date().toString());
            

            reclamosservice.Guardarreclamo(nuevoreclamo);
            System.out.println("Reclamo Guardado" + nuevoreclamo.getId_reclamo());
        
        }
    }

}
