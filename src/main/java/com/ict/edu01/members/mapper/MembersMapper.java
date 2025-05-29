package com.ict.edu01.members.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ict.edu01.members.vo.MembersVO;
import com.ict.edu01.members.vo.RefreshVO;

@Mapper
public interface MembersMapper {

    MembersVO getLogin(MembersVO mvo);

    int getRegister(MembersVO mvo);

    MembersVO getmypage(MembersVO mvo);

    MembersVO findUserById(String m_id);

    void saveRefreshToken(RefreshVO refreshVO);
}