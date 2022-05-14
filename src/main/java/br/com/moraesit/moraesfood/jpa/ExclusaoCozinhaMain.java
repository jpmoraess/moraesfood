package br.com.moraesit.moraesfood.jpa;

import br.com.moraesit.moraesfood.MoraesfoodApplication;
import br.com.moraesit.moraesfood.domain.entity.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExclusaoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(MoraesfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        final CadastroCozinha cadastroCozinha = context.getBean(CadastroCozinha.class);

        final Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);

        cadastroCozinha.remover(cozinha);
    }
}
