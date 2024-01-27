package com.m0ncld.sso.webapp2.user;

import com.m0ncld.sso.webapp2.util.GenericReadController;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserController extends GenericReadController<UserModel, UUID, UserRepository> {

    protected UserController(UserRepository repository) {
        super(repository);
    }

}
