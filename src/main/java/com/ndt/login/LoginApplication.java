package com.ndt.login;

import com.ndt.login.model.Role;
import com.ndt.login.model.User;
import com.ndt.login.service.role.IRoleService;
import com.ndt.login.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class LoginApplication {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init(){
        List<Role> roles=(List<Role>) roleService.findAll();
        if (roles.isEmpty()){
            Role roleAdmin= new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleService.save(roleAdmin);
            Role roleUser=new Role();
            roleUser.setName("ROLE_USER");
            roleService.save(roleUser);
        }
        List<User> users=(List<User>) userService.findAll();
        if (users.isEmpty()){
            User admin=new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            Role roleAdmin= new Role();
            roleAdmin.setId(1L);
            Set<Role> roleSet=new HashSet<>();
            roleSet.add(roleAdmin);
            admin.setRoles(roleSet);
            userService.save(admin);

            User user=new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            Role roleUser= new Role();
            roleUser.setId(2L);
            Set<Role> roleSetUser=new HashSet<>();
            roleSetUser.add(roleUser);
            admin.setRoles(roleSetUser);
            userService.save(user);
        }
    }
    public static void main(String[] args) {

        SpringApplication.run(LoginApplication.class, args);
    }

}
