package com.evanslaton.codefellowship.applicationuser;

import com.evanslaton.codefellowship.applicationuser.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
    public ApplicationUser findByUsername(String username);
}
