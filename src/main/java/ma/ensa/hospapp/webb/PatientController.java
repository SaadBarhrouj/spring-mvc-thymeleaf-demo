package ma.ensa.hospapp.webb;
import ma.ensa.hospapp.entities.Patient;
import ma.ensa.hospapp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(
            Model model,
            @RequestParam(name= "page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "10") int size,
            @RequestParam(name="keyword",defaultValue = "")String keyword
    )
    {
     Page<Patient> patientsPage= patientRepository.findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(keyword,keyword, PageRequest.of(page, size));
       model.addAttribute("listPatients",patientsPage.getContent());
       model.addAttribute("pages",new int [patientsPage.getTotalPages()]);
       model.addAttribute("currentPage",page);
       model.addAttribute("keyword",keyword);
       return "patients";
    }


    @GetMapping("/deletePatient")
    public String delete(
            @RequestParam(name = "id") Long id,
            String keyword,
            int page
    ) {
        patientRepository.deleteById(id);
        return "redirect:/index?page="+ page + "&keyword="+keyword;
    }

}


