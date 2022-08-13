package hu.progmatic.webpage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// https://www.baeldung.com/spring-security-expressions
// https://bushansirgur.in/everything-need-to-know-about-matchers-methods-in-spring-security/
// https://www.baeldung.com/spring-security-granted-authority-vs-role
@Configuration
@EnableWebSecurity
public class PageSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // anyRequest() -> minden útvonal (endpoint)
        // antMatchers("/admin**")
        // * az adott szegmensre vonatkozó wildcard ("/admin/actions/*/self")
        // ** a teljes route maradék részére illeszkedik ("/admin**")
        // mvcMatchers
        // regexMatchers

        // permitAll() -> mindenki számára elérhetővé tesszük az adott útvonalat (endpoint)
        // denyAll() -> senki számára nem elérhető az adott útvonal (endpoint)
        // authenticated() -> bármelyik bejelentkezett felhasználó számára elérhető
        // hasRole("ADMIN") -> "ADMIN" jogosultsággal rendelkező felhasználók számára elérhető

        // .and().formLogin();
        // Automatikusan generál egy login oldalt.

        // Első találatot veszi figyelembe a Spring Security.
        // Fent kell a specifikus matchereket megadni.
        // "blacklist" -> amit nem tiltok, azt engedélyezem

        http.authorizeRequests()
                /* .anyRequest()
                .denyAll() */
                // Nem lenne értelme, mert a többi matcher soha nem futna le.
                .antMatchers("/admin**", "/secret**")
                .hasRole("ADMIN")
                .antMatchers("/profile**")
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin();

        // "whitelist" -> amit nem engedélyezek, azt tiltom
        // login oldal használhat css-t (pl. BootStrap)
        /* http.authorizeRequests()
                .antMatchers("/login", "/logout", "/css/**")
                .permitAll()
                .anyRequest()
                .denyAll()
                .and()
                .formLogin(); */
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        final String password = "password";
        System.out.println("Hash of password \"" + password + "\" is " + encoder().encode(password) + ".");

        auth.inMemoryAuthentication()
                .withUser("user")
                .password(encoder().encode(password))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(encoder().encode(password))
                .roles("USER", "ADMIN");
    }

    // "factoryval" létrehozott @Component
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
