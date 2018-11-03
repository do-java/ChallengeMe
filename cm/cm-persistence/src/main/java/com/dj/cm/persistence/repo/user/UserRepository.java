package com.dj.cm.persistence.repo.user;

import com.dj.cm.model.entity.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findUserModelByEmail (String userEmail);
}
