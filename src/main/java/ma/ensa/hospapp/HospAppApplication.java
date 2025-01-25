package ma.ensa.hospapp;

import ma.ensa.hospapp.entities.Patient;
import ma.ensa.hospapp.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
public class HospAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(HospAppApplication.class, args);


        }
    @GetMapping("/index")
    public String index(){
        return "hello world";
    }

    /*
    //METHODE-1:
     @Bean
    public CommandLineRunner start(PatientRepository patientRepository) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                //code
            }

        };
    }*/

    // Methode 2: automatiquement l'injection des dependance est faite
    @Bean
    public CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {
            // @NoArgsConstructor
         Patient p1 = new Patient();
         p1.setNom("Barhrouj");
         p1.setPrenom("saad");
         p1.setScore(10);
         p1.setMalade(true);
         p1.setDateNaissance(new Date());
         //@ALLArgsConstructor
            patientRepository.save(new Patient(null, "Alaoui", "Ali", new Date(), 100, false));
            patientRepository.save(new Patient(null, "Chairi", "Samia", new Date(), 100, true));
            patientRepository.save(new Patient(null, "Tihami", "Hicham", new Date(), 100, true));
            patientRepository.save(new Patient(null, "Rouchdi", "Khalid", new Date(), 100, false));
            patientRepository.save(new Patient(null, "Rhoni", "Nizar", new Date(), 100, false));
            patientRepository.save(new Patient(null, "Chairi", "Samia", new Date(), 100, true));
            patientRepository.save(new Patient(null, "Tihami", "Hicham", new Date(), 100, false));
            patientRepository.save(new Patient(null, "Rouchdi", "Khalid", new Date(), 100, true));
            patientRepository.save(new Patient(null, "Rhoni", "Nizar", new Date(), 100, false));
            patientRepository.save(new Patient(null, "Alaoui", "Ali", new Date(), 100, false));




           /* @Builder : design pattern
             avec @Builder (vous pouvez creer l'objetavec n'importe quelle params sans
             prend en consideration l'ordre ...)

         Patient p3 =Patient.builder()
        .nom("barhrouj3")
        .prenom("saad3")
        .score(100)
        .malade(true)
        .build();
*/





            List<Patient> patients=patientRepository.findAll();
            patients.forEach(p->{
                System.out.println(p.getPrenom());
            });
        };
    }

}
