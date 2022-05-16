package br.com.moraesit.moraesfood;

import br.com.moraesit.moraesfood.domain.entity.Cozinha;
import br.com.moraesit.moraesfood.domain.repository.CozinhaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class Controller {

    private final CozinhaRepository cozinhaRepository;

    public Controller(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello!";
    }

//    @GetMapping("/cozinhas")
//    public List<Cozinha> consultarPorNome(@RequestParam("nome") String nome) {
//        return cozinhaRepository.consultarPorNome(nome);
//    }
}
