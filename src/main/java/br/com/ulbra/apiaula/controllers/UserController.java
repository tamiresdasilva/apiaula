package br.com.ulbra.apiaula.controllers;

import br.com.ulbra.apiaula.models.User;
import br.com.ulbra.apiaula.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users") //Mapear a request
public class UserController {
    private UserService userService; //Atributo do tipo UserService

    //Injetando a dependência no construtor UserController
    public UserController(UserService userService){
        this.userService = userService;
    }


    //Lista todos os usuários
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    //Busca um usuário específico com base no ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    //Remove um usuário com base no ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build(); //Retorna 204 (sem conteúdo)
    }

    //Cria um novo usuário
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        //Cria URI para o novo recurso
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        //Retprma 201 (Created) com o URI e o usuário criado
        return ResponseEntity.created(uri).body(userService.createUser(user));
    }

    //Atualiza um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        try {
            User updateUser = this.userService.updateUser(id, user);
            return ResponseEntity.ok(updateUser);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
