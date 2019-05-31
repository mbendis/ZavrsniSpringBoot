package hr.fer.marin.zavrsni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.thymeleaf.spring5.view.ThymeleafView;

@EnableJpaAuditing
@SpringBootApplication
public class ZavrsniApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZavrsniApplication.class, args);
    }

    @Bean(name="content-part")
    @Scope("prototype")
    public ThymeleafView someViewBean() {
        ThymeleafView view = new ThymeleafView("home");
        view.setMarkupSelector("content");
        return view;
    }
}

