package com.example.warsztatsamochodowy.service;

import com.example.warsztatsamochodowy.dto.UserDto;
import com.example.warsztatsamochodowy.entity.*;
import com.example.warsztatsamochodowy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.findByEmail(usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Użytkownik nie istnieje"));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public List<String> validateAdmin(UserDto userDto) {
        List<String> errors = new ArrayList<>();

        if (!userDto.getConfirmPassword().equals(userDto.getPassword())) {
            errors.add("Hasła muszą być jednakowe.");
        }
        if (!userDto.getTelefon().isBlank() && !Pattern.compile("^[()+0-9 ]{6,}$").matcher(userDto.getTelefon()).find()) {
            errors.add("Niepoprawny numer telefonu.");
        }
        return errors;
    }

    public List<String> validate(UserDto userDto) {
        List<String> errors = new ArrayList<>();

        if(userDto.getId() == 0){
            if (userDto.getPassword().isBlank()) {
                errors.add("Hasło jest wymagane.");
            }
            if (userDto.getConfirmPassword().isBlank()) {
                errors.add("Potwierdzenie hasła jest wymagane.");
            }
        }
        if (!userDto.getConfirmPassword().equals(userDto.getPassword())) {
            errors.add("Hasła muszą być jednakowe.");
        }
        if (userDto.getAdres().isBlank()) {
            errors.add("Adres jest wymagany.");
        }
        if (userDto.getTelefon().isBlank()) {
            errors.add("Numer telefonu jest wymagany.");
        }
        if (!Pattern.compile("^[()+0-9 ]{6,}$").matcher(userDto.getTelefon()).find()) {
            errors.add("Niepoprawny numer telefonu.");
        }
        validate(userDto, errors);
        return errors;
    }

    public void validate(UserDto userDto, List<String> errors) {
        if (userDto.getUserType() == UserDto.CUSTOMER_PERSON) {
            if (userDto.getImie().isBlank()) {
                errors.add("Imię jest wymagane.");
            }
            if (userDto.getNazwisko().isBlank()) {
                errors.add("Nazwisko jest wymagane.");
            }
            if (userDto.getPesel().isBlank()) {
                errors.add("PESEL jest wymagane.");
            }
            if (!Pattern.compile("^[0-9]{6,}$").matcher(userDto.getPesel()).find()) {
                errors.add("Niepoprawny PESEL.");
            }
        } else {
            if (userDto.getNazwa().isBlank()) {
                errors.add("Nazwa jest wymagane.");
            }
            if (userDto.getRegon().isBlank()) {
                errors.add("REGON jest wymagany.");
            }
            if (userDto.getNip().isBlank()) {
                errors.add("NIP jest wymagany.");
            }
        }
    }

    public List<String> registerUser(UserDto userDto) {
        List<String> errors = new ArrayList<>();

        if (userDto.getId() > 0 && userRepository.existsByEmail(userDto.getEmail())) {
            errors.add("Istnieje użytkownik o takim adresie email.");
            return errors;
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Set<Role> roles = getBasicRoles();
        if (userDto.getUserType() == UserDto.CUSTOMER_PERSON) {
            registerPerson(userDto, roles);
        } else {
            registerOrg(userDto, roles);
        }

        return errors;
    }

    public List<String> update(UserDto userDto) {
        List<String> errors = new ArrayList<>();

        if (userDto.getId() > 0 && userRepository.existsByEmail(userDto.getEmail())) {
            errors.add("Istnieje użytkownik o takim adresie email.");
            return errors;
        }

        return errors;
    }

    private void registerPerson(UserDto userDto, Set<Role> roles) {
        PhysicalPerson person = new PhysicalPerson(userDto, roles);
        userRepository.save(person);
    }

    private void registerOrg(UserDto userDto, Set<Role> roles) {
        LegalPerson person = new LegalPerson(userDto, roles);
        userRepository.save(person);
    }

    private Set<Role> getBasicRoles() {
        Set<Role> roles = new HashSet<>();
        Optional<Role> opt = roleRepository.findByName(Role.ROLE_USER);
        if (opt.isEmpty()) return roles;

        roles.add(opt.get());
        return roles;
    }
}
