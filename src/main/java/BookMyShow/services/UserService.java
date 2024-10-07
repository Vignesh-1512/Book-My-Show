package BookMyShow.services;

import BookMyShow.exceptions.UserExistException;
import BookMyShow.requestDtos.AddUserRequest;
import BookMyShow.entities.User;
import BookMyShow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUser(AddUserRequest addUserRequest) throws UserExistException
    {
        User existingUser=userRepository.getByEmailId(addUserRequest.getEmailId());
        if(existingUser!=null)
        {
            throw new UserExistException("User " +existingUser.getName()+" already registered using this Email Id");
        }
        User existingUser2=userRepository.getByMobile(addUserRequest.getMobile());
        if(existingUser2!=null)
        {
            throw new UserExistException("User "+existingUser2.getName()+" already registered using this mobile number");
        }
        User user = new User();
        user.setName(addUserRequest.getName());
        user.setMobile(addUserRequest.getMobile());
        user.setEmailId(addUserRequest.getEmailId());
        user.setAge(addUserRequest.getAge());
        userRepository.save(user);
        return "User "+addUserRequest.getName()+" has been added";
    }

    //getAllUsers
    public List<AddUserRequest> getAllUser(){
        List<User> userList=userRepository.findAll();
        return userList.stream().map(user -> new AddUserRequest(
                user.getName(),
                user.getEmailId(),
                user.getMobile(),
                user.getAge()
        )).collect(Collectors.toList());
    }

}

