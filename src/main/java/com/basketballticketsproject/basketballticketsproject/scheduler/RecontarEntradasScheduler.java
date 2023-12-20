package com.basketballticketsproject.basketballticketsproject.scheduler;

import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import com.basketballticketsproject.basketballticketsproject.service.SorteoService;
import com.basketballticketsproject.basketballticketsproject.utils.EnviarEmailUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Configuration
@EnableScheduling
public class RecontarEntradasScheduler {

    @Autowired
    private PartidoRepo partidoRepo;

    @Autowired
    EnviarEmailUsuarios enviarEmail;

    @Autowired
    private SorteoService sorteoService;

    @Autowired
    private UsuarioRepo usuarioRepo;

    //@Scheduled(cron = "0 0 14 ? * 4 ")
    public void enviarCorreo() {
        List<Partido> fechasSortAsc = partidoRepo.getFechasSortAsc();
        if (!CollectionUtils.isEmpty(fechasSortAsc)) {
            Partido partido = fechasSortAsc.get(0);
            System.out.println(partido.getFechaPartido());
            Set<Usuario> usuariosSorteo = sorteoService.getUsuariosSorteo(partido.getFechaPartido());
            usuariosSorteo.forEach(usuario -> {
                String emailEntrada = enviarEmail.enviarEmailEntrada(usuario, partido.getFechaPartido());
                System.out.println("emaill entrada: " + emailEntrada);
                //usuario.setEntrada(emailEntrada);
                /*try {
                    crearCarpetaEntradasAsignadas(partido.getFechaPartido(), emailEntrada);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                */


                usuarioRepo.save(usuario);
            });
        }
    }

}
