package br.com.moraesit.moraesfood.jpa;

import br.com.moraesit.moraesfood.MoraesfoodApplication;
import br.com.moraesit.moraesfood.domain.entity.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class InclusaoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(MoraesfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        final CadastroCozinha cadastroCozinha = context.getBean(CadastroCozinha.class);

        final Cozinha c1 = new Cozinha();
        c1.setNome("Brasileira");

        final Cozinha c2 = new Cozinha();
        c2.setNome("Italiana");

        cadastroCozinha.adicionar(c1);
        cadastroCozinha.adicionar(c2);
    }
}
