package com.example.warsztatsamochodowy.service;

import com.example.warsztatsamochodowy.dto.WorkerDto;
import com.example.warsztatsamochodowy.entity.Worker;
import com.example.warsztatsamochodowy.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, PasswordEncoder passwordEncoder) {
        this.workerRepository = workerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Iterable<Worker> getAll() {
        return workerRepository.findAll();
    }

    public List<String> validate(WorkerDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto.getId() == 0) {
            if (workerRepository.existsByEmail(dto.getEmail())) {
                errors.add("Istnieje użytkownik o takim adresie email.");
                return errors;
            }
            if (dto.getPassword().isBlank()) {
                errors.add("Hasło jest wymagane.");
            }
            if (dto.getConfirmPassword().isBlank()) {
                errors.add("Potwierdzenie hasła jest wymagane.");
            }
        }

        if (dto.getImie().isBlank()) {
            errors.add("Imię jest wymagane.");
        }
        if (dto.getNazwisko().isBlank()) {
            errors.add("Nazwisko jest wymagane.");
        }
        if (!dto.getConfirmPassword().equals(dto.getPassword())) {
            errors.add("Hasła muszą być jednakowe.");
        }
        if (dto.getAdres().isBlank()) {
            errors.add("Adres jest wymagany.");
        }
        if (dto.getTelefon().isBlank()) {
            errors.add("Numer telefonu jest wymagany.");
        }
        if (!Pattern.compile("^[()+0-9 ]{6,}$").matcher(dto.getTelefon()).find()) {
            errors.add("Niepoprawny numer telefonu.");
        }
        if (dto.getPesel().isBlank()) {
            errors.add("PESEL jest wymagane.");
        }
        if (!Pattern.compile("^[0-9]{6,}$").matcher(dto.getPesel()).find()) {
            errors.add("Niepoprawny PESEL.");
        }
        if (dto.getStanowiskopracy().isBlank()) {
            errors.add("Stanowisko pracy jest wymagane.");
        }
        return errors;
    }

    public long save(WorkerDto dto) {
        Worker worker;

        if (dto.getId() == 0) {
            worker = new Worker();
        } else {
            Optional<Worker> opt = workerRepository.findById(dto.getId());
            if (opt.isEmpty()) return 0;
            worker = opt.get();
        }

        worker.setImie(dto.getImie());
        worker.setNazwisko(dto.getNazwisko());
        if (!dto.getPassword().isBlank()) {
            worker.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        worker.setEmail(dto.getEmail());
        worker.setAdres(dto.getAdres());
        worker.setTelefon(dto.getTelefon());
        worker.setPesel(dto.getPesel());
        worker.setDataurodzenia(dto.getDataurodzenia());
        worker.setStanowiskopracy(dto.getStanowiskopracy());

        worker = workerRepository.save(worker);
        return worker.getId();
    }

    public boolean delete(long id) {
        Optional<Worker> opt = workerRepository.findById(id);
        if (opt.isEmpty()) return true;

        Worker worker = opt.get();
        if(!worker.getZamowienia().isEmpty()){
            return false;
        }
        workerRepository.delete(worker);
        return true;
    }
}
