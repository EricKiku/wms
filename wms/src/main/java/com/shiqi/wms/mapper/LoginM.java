package com.shiqi.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiqi.wms.pojo.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface LoginM extends BaseMapper<Rule> {

    @Select("select * from rule")
    List<Rule> selectList();

    //查找一条数据
    @Select("select u_id,u_name,u_rule,u_menu from rule where u_account = #{uAccount} and u_pwd = #{uPwd}")
    Rule selectOne(String uAccount,String uPwd);

    // 查询账号
    @Select("select u_account from rule where u_account = #{uAccount}")
    String selectAccount(String uAccount);
}
