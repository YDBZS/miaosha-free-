package com.miaoshaproject.dao;

import com.miaoshaproject.dataobjects.UserDO;

public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 04 22:19:28 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 04 22:19:28 CST 2020
     */
    int insert(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 04 22:19:28 CST 2020
     */
    int insertSelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 04 22:19:28 CST 2020
     */
    UserDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 04 22:19:28 CST 2020
     */
    int updateByPrimaryKeySelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 04 22:19:28 CST 2020
     */
    int updateByPrimaryKey(UserDO record);

    /**
     * 根据用户手机号查询用户信息
     *
     * @author 多宝
     * @since 2020/7/12 22:05
     * @param telephone      用户手机号
     * @return com.miaoshaproject.dataobjects.UserDO
     **/
    UserDO selectByTelephone(String telephone);

}