package BookMyShow.Transformers;

import BookMyShow.RequestDtos.AddUserRequest;
import BookMyShow.entities.User;

public class UserTransformers {

    public static User convertAddUserRequestToUserEntity(AddUserRequest addUserRequest)
    {
        User userObj=User.builder()
                .age(addUserRequest.getAge())
                .emailId(addUserRequest.getEmailId())
                .mobile(addUserRequest.getMobile())
                .name(addUserRequest.getName())
                .build();

        return userObj;
    }
}
