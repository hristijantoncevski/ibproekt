package mk.ukim.finki.ibproekt.web;

import mk.ukim.finki.ibproekt.model.Role;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.ibproekt.model.exceptions.UsernameWithThatCnAlreadyExists;
import mk.ukim.finki.ibproekt.service.AuthService;
import mk.ukim.finki.ibproekt.service.VoterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import javax.security.auth.x500.X500Principal;
import javax.servlet.http.HttpServletRequest;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    private final VoterService voterService;

    public RegisterController(AuthService authService, VoterService voterService) {
        this.authService = authService;
        this.voterService = voterService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping String register(@RequestParam String name,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam Role role, HttpServletRequest request) throws InvalidNameException {
        X509Certificate certs[] = (X509Certificate[])request.getAttribute("javax.servlet.request.X509Certificate");
        X509Certificate clientCert = certs[0];
        X500Principal subjectDN = clientCert.getSubjectX500Principal();
        String dn = subjectDN.getName();
        LdapName ldapDN = new LdapName(dn);
        String CN = null;
        for(Rdn rdn : ldapDN.getRdns()) {
            if(rdn.getType().equalsIgnoreCase("CN")) {
                String firstCN = rdn.getValue().toString();
                CN = firstCN;
                break;
            }
        }
        try{
            this.voterService.create(name,username,password,role,CN);
            return "redirect:/login";
        } catch(InvalidArgumentsException | UsernameWithThatCnAlreadyExists exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
