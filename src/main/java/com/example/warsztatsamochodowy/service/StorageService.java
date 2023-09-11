package com.example.warsztatsamochodowy.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

    private final Path root = Paths.get("uploads");

    public StorageService() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public void save(long id, MultipartFile file) {
        try {
            Path p = root.resolve(Long.toString(id));
            if(p.toFile().exists()){
                deleteFromDir(p);
            }else{
                Files.createDirectories(p);
            }
            Files.copy(
                    file.getInputStream(),
                    p.resolve( Objects.requireNonNull(file.getOriginalFilename()))
            );
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteFromDir(Path path) {
        Arrays.stream(Objects.requireNonNull(path.toFile().listFiles())).forEach(File::delete);
    }

    public Resource load(long id, String filename) {
        try {
            Path file = root.resolve(id + "/" + filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
