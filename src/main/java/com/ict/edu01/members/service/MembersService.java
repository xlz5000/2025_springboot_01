package com.ict.edu01.members.service;

import java.util.Date;

import com.ict.edu01.members.vo.MembersVO;

public interface MembersService {
     MembersVO getLogin(MembersVO mvo);

     int getRegister(MembersVO mvo);

     MembersVO getmypage(MembersVO mvo);

     void saveRefreshToken(String m_id, String refreshToken, Date expiry_date);

}
