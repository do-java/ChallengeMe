package com.dj.cm.biz.service.user;

import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.model.entity.UserModel;
import com.dj.cm.biz.service.exception.AlreadyExistBizException;

/**
 * User Service.
 */
public interface UserService {

    /**
     * Get all echos;
     *
     * @return @{@link UserModel} list
     */
    Iterable<UserModel> findAllUsers();

    /**
     * Get user by id;
     *
     * @param id get by id
     * @return user
     * @throws NotFoundBizException if not found
     */
    UserModel getUserById(Long id);

    /**
     * Delete user.
     *
     * @param id user id to delete
     * @throws NotFoundBizException if user not found
     */
    void deleteUserById(Long id);

    /**
     * Create user.
     *
     * @param userModel user to create
     * @return created user
     */
    UserModel createUser(UserModel userModel);

    /**
     * Update echo.
     *
     * @param userModel user to update
     * @return updated user
     * @throws NotFoundBizException if not found
     * @throws AlreadyExistBizException if user email already exist
     */
    UserModel updateUser(UserModel userModel);

}
