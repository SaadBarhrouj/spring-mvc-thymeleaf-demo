package ma.ensa.hospapp.repositories;
import ma.ensa.hospapp.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Page<Patient> findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(String nom,String prenom, Pageable pageable);
}
