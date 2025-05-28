package com.ict.edu01.members.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.edu01.jwt.JwtService;
import com.ict.edu01.members.service.MembersService;
import com.ict.edu01.members.vo.DataVO;
import com.ict.edu01.members.vo.MembersVO;

@RestController
@RequestMapping("/api/members")
public class MembersController {

    @Autowired
    private MembersService membersService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, SpringBoot";
    }

    @PostMapping("/login")
    public DataVO getLogin(@RequestBody MembersVO mvo) {

        DataVO dataVO = new DataVO();

        try {

            // DataVO dataVO = new DataVO();
            // 만약에 맞면
            // dataVO.setSuccess(true);
            // dataVO.setMessage("로그인 성공");

            // 만약 정보 전달할 data가 하나이면
            // dataVO.setData(정보);

            // 만약 정보 전달할 data가 여러개 이면
            // Map<String,Object> result = new HashMap<>();
            // result.put("list", list);
            // result.put("membersVO", mvo);
            // result.put("totalCount", totalCount);

            // 맞지 않으면
            // dataVO.setSuccess(false);
            // dataVO.setMessage("로그인 실패");

            // DB 에 가서 m_id 와 m_pw가 맞는지 확인한다.
            /*
             * MembersVO membersVO = membersService.getLogin(mvo);
             * 
             * if (membersVO == null) {
             * dataVO.setSuccess(false);
             * dataVO.setMessage("로그인 실패");
             * } else {
             * dataVO.setSuccess(true);
             * dataVO.setMessage("로그인 성공");
             * dataVO.setData(membersVO);
             * }
             */

            // jwt를 활용한 로그인 처리
            Map<String, String> tokens = jwtService.login(mvo);
            dataVO.setSuccess(true);
            dataVO.setData(tokens);
            dataVO.setMessage("로그인 성공");

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("서버 오류 : " + e.getMessage());
        }

        return dataVO;
    }

    @PostMapping("/register")
    public DataVO getRegister(@RequestBody MembersVO mvo) {

        DataVO dataVO = new DataVO();

        try {

            int result = membersService.getRegister(mvo);

            if (result > 0) {
                dataVO.setSuccess(true);
                dataVO.setMessage("회원가입 성공");
                dataVO.setData(mvo);
            } else {
                dataVO.setSuccess(false);
                dataVO.setMessage("회원가입 실패2");

            }

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("서버 오류 : " + e.getMessage());
        }

        return dataVO;
    }

    @PostMapping("/mypage")
    // 토큰 받기
    public DataVO getmypage(@RequestBody MembersVO mvo) {
        DataVO dataVO = new DataVO();

        try {
            // MembersVO mvo = membersService.getMyPage(mvo2.getM_idx());
            MembersVO membersVO = membersService.getmypage(mvo);
            if (membersVO == null) {
                dataVO.setSuccess(false);
                dataVO.setMessage("마이페이지 조회 실패");
            } else {
                // 로그인 성공했을때 token 처리
                dataVO.setSuccess(true);
                dataVO.setMessage("마이페이지 조회 성공");
                dataVO.setData(membersVO);
            }

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("서버 오류 : " + e.getMessage());

        }

        return dataVO;
    }

}