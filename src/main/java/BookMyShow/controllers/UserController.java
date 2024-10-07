package BookMyShow.controllers;

import BookMyShow.entities.User;
import BookMyShow.exceptions.UserExistException;
import BookMyShow.requestDtos.AddUserRequest;
import BookMyShow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public String save(@RequestBody AddUserRequest addUserRequest)
    {
        try {
            return userService.addUser(addUserRequest);
        }catch (UserExistException e){
            return e.getMessage();
        }
    }

//    @GetMapping("/get/{id}")
//    public Optional<User> getUser(@PathVariable Integer id) throws Exception
//    {
//        return  userService.view(id);
//    }
//
//    @PutMapping("/update/{id}")
//    public Optional<User> update(@PathVariable Integer id,@RequestBody User updatedUser)
//    {
//        return userService.update(id,updatedUser);
//    }
//
    @GetMapping("/getAll")
    public List<AddUserRequest> getAllUser()
    {
        return  userService.getAllUser();
    }
//
//    @DeleteMapping("/delete/{id}")
//    public String delete(@PathVariable Integer id)
//    {
//        return userService.delete(id);
//    }
//
//    @GetMapping("/max-salary")
//    public Long getUserWithMaxSalary() {
//        return userService.getUserWithMaxSalary();
//    }
}
