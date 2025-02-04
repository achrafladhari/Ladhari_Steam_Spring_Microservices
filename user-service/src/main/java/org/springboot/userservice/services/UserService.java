package org.springboot.userservice.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springboot.userservice.exception.UserNotFoundException;
import org.springboot.userservice.library.LibraryClient;
import org.springboot.userservice.mapper.UserMapper;
import org.springboot.userservice.repository.UserRepo;
import org.springboot.userservice.request.UserRequest;
import org.springboot.userservice.user.LoginRequest;
import org.springboot.userservice.user.ResponseMapper;
import org.springboot.userservice.user.UserApp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper mapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final LibraryClient libraryClient;

    //IMAGES
    private String giveMeNewName(String oldName)
    {

        String firstpart = oldName.substring(0, oldName.lastIndexOf("."));
        String secondpart = oldName.substring(oldName.lastIndexOf(".") + 1);
        return firstpart + System.currentTimeMillis() + "." + secondpart;
    }
// GET IMAGES :
public Resource getImageByUserId(String username) throws IOException {
    // Fetch the image name from the database or user object based on userId
    UserApp user = userRepo.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
    String imageName = user.getImage();

    // Construct the image file path
    Path imagePath = Paths.get(uploadDir).resolve(imageName);

    // Check if the image exists
    if (Files.exists(imagePath)) {
        return new FileSystemResource(imagePath);
    } else {
        throw new RuntimeException("Image not found for user " + username);
    }
}
    @Value("${uploads.dir}")
    private String uploadDir;
    public String saveImage2(MultipartFile mf) throws IOException {
        String newName = giveMeNewName(mf.getOriginalFilename());
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath))
            Files.createDirectory(uploadPath);
        Path pathFile = uploadPath.resolve(newName);
        Files.write(pathFile, mf.getBytes());
        return newName;
    }


    //login
    public ResponseMapper login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var user = userRepo.findByUsername(request.username())
                .orElseThrow();
        var token = jwtService.generateToken(user);
        return new ResponseMapper(token);
    }

    //getUsersBy pagination
    public Page<UserApp> getUsersPagination(
            String name, Pageable pageable
    ){
        if (name == null){
            return
                    userRepo.findAll(pageable);
        }
        return userRepo.findByNameContaining(name, pageable);
    }

    //add new user to system REGISTER!
    public ResponseMapper createUser(UserRequest userRequest,MultipartFile mf) throws IOException {
        UserApp userToSave= mapper.toUser(userRequest);
        if (!mf.isEmpty()){
            userToSave.setImage(saveImage2(mf));
        }
        userRepo.save(userToSave);
        var token = jwtService.generateToken(userToSave);
        libraryClient.createLibrary(userToSave,token);
        return new ResponseMapper(token) ;
    }

    //GET ACUTAL USER NOT USED !!
    public String userConnected() {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserApp user= (UserApp) authentication.getPrincipal();
            return user.getId();
        } else {
            return null;
        }
    }

    //update a user from the system !!
    public ResponseMapper updateUserByUsername(UserRequest userRequest, String username) {
        UserApp userToUpdate=new UserApp();
        userToUpdate.setId(username);
        userToUpdate = userRepo.findByUsername(userToUpdate.getId()).orElseThrow(()-> new UserNotFoundException(
                "User with id not found"
        ));
        mergeUser(userToUpdate,userRequest);
        userRepo.save(userToUpdate);
        return new ResponseMapper (userToUpdate.getId());
    }


    //update a user from the system !!
    public String updateUser(UserRequest userRequest, String id) {
        UserApp userToUpdate=new UserApp();
        userToUpdate.setId(id);
         userToUpdate = userRepo.findById(userToUpdate.getId()).orElseThrow(()-> new UserNotFoundException(
                 "User with id not found"
        ));
        mergeUser(userToUpdate,userRequest);
        userRepo.save(userToUpdate);
        return userToUpdate.getId();
    }


    //find All USers
    public List<UserApp> findAllUsers() {
        return userRepo.findAll();
    }


    private void mergeUser(UserApp userToUpdate, UserRequest userRequest) {
        if (StringUtils.isNotBlank(userRequest.password())){
            userToUpdate.setPassword(passwordEncoder.encode(userRequest.password()));
        }
        if (StringUtils.isNotBlank(userRequest.name())){
            userToUpdate.setName(userRequest.name());
        }
        if (StringUtils.isNotBlank(userRequest.email())){
            userToUpdate.setEmail(userRequest.email());
        }
        if (StringUtils.isNotBlank(userRequest.username())){
            userToUpdate.setUsername(userRequest.username());
        }
        if (StringUtils.isNotBlank(userRequest.address())){
            userToUpdate.setAddress(userRequest.address());
        }
    }

    public UserApp findById(String id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("No User found with the provided ID: %s", id)));
    }

    public UserApp findByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("No User found with the provided USERNAME: %s", username)));
    }

    public boolean existsById(String id) {
        return userRepo.findById(id)
                .isPresent();
    }

    public String deleteUser(String id,String token) {
        var userToDelete=userRepo.findById(id);
        libraryClient.deleteLibrary(userToDelete.get().getUsername(),token);
       userRepo.deleteById(id);
       return "Deleted user with id: " + id;
    }
}
