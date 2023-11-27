package com.basketballticketsproject.basketballticketsproject.scheduler;

import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.SorteoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import com.basketballticketsproject.basketballticketsproject.service.SorteoService;
import com.basketballticketsproject.basketballticketsproject.utils.EnviarEmailUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;
import java.util.Set;

import static com.basketballticketsproject.basketballticketsproject.utils.Constants.PATH_CARPETA_FECHAS_PARTIDOS;

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

    @Scheduled(cron = "* * 14 L * *")
    public void enviarCorreo() {
        List<Partido> fechasSortAsc = partidoRepo.getFechasSortAsc();
        if (!CollectionUtils.isEmpty(fechasSortAsc)) {
            Partido partido = fechasSortAsc.get(0);
            System.out.println(partido.getFechaPartido());
            Set<Usuario> usuariosSorteo = sorteoService.getUsuariosSorteo(partido.getFechaPartido());
            usuariosSorteo.forEach(usuario -> {
                String emailEntrada = enviarEmail.enviarEmailEntrada(usuario, partido.getFechaPartido());
                System.out.println("emaill entrada: " + emailEntrada);
                usuario.setEntrada(emailEntrada);
                File borrar = new File(PATH_CARPETA_FECHAS_PARTIDOS + "\\" + partido.getFechaPartido() + "\\" + emailEntrada);
                System.out.println(borrar);
                if (borrar.delete() ) {
                    System.out.println("oleole");
                }
                usuarioRepo.save(usuario);
            });
        }
    }
}
