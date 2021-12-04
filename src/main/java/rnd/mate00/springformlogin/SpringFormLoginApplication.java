package rnd.mate00.springformlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rnd.mate00.springformlogin.user.DbUser;
import rnd.mate00.springformlogin.user.DbUserRepository;

@SpringBootApplication
public class SpringFormLoginApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringFormLoginApplication.class, args);
	}

	@Autowired
	private DbUserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		DbUser u1 = new DbUser();
		u1.setUsername("u1");
		u1.setPassword(new BCryptPasswordEncoder().encode("p1"));
		u1.setEmail("u1@mail.com");
		u1.setEnabled(true);

		userRepository.save(u1);
	}
}
