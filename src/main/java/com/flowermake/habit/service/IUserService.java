package com.flowermake.habit.service;

import com.flowermake.habit.domain.User;

/**
 * @author lpp
 *
 */
public interface IUserService {
	
	/**
	 * 注册一个用户
	 * @param user 要注册的用户对象
	 * @return 如果注册成功，则返回该用户id，否则返回-1
	 * @throws Exception
	 */
	public int addUser(User user) throws Exception;
	
//	/**根据用户微信id查询用户
//	 * @param wid 用户微信id
//	 * @return 查询出来的用户对象
//	 * @throws Exception
//	 */
//	public User findUserByWid(String wid) throws Exception;
	
	/**根据用户id查询用户
	 * @param id 用户id
	 * @return 查询出来的用户对象
	 * @throws Exception
	 */
	public User findUserById(long id) throws Exception;
	
	/**修改一个用户
	 * @param user 要修改的用户
	 * @return 如果修改成功，返回修改的用户id，否则返回-1
	 * @throws Exception
	 */
	public int updateUser(User user)throws Exception;
	
}
