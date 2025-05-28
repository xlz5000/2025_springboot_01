package com.ict.edu01.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ict.edu01.guestbook.service.GuestBookService;
import com.ict.edu01.guestbook.vo.GuestBookVO;
import com.ict.edu01.members.vo.DataVO;

@RestController
@RequestMapping("api/guestbook")
public class GuestBookController {

    @Autowired
    private GuestBookService guestBookService;

    @GetMapping("/guestbooklist")
    public DataVO getGuestBookList() {
        DataVO dataVO = new DataVO();

        try {
            List<GuestBookVO> list = guestBookService.guestbooklist();
            if (list == null) {

                dataVO.setSuccess(true);
                dataVO.setMessage("데이터가 존재하지 않습니다.");

            } else {
                dataVO.setSuccess(true);
                dataVO.setMessage("데이터가 존재합니다다.");
                dataVO.setData(list);
            }

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("서버 오류...");
        }

        return dataVO;
    }

    @GetMapping("/searchguestbook")
    public DataVO searchGuestBook(@RequestParam("gb_idx") String gb_idx) {
        DataVO dataVO = new DataVO();

        try {
            GuestBookVO guestbook = guestBookService.searchguestbook(gb_idx);

            if (guestbook == null) {

                dataVO.setSuccess(false);
                dataVO.setMessage("데이터가 존재하지 않습니다.");

            } else {

                dataVO.setSuccess(true);
                dataVO.setMessage("데이터가 존재합니다다");
                dataVO.setData(guestbook);
            }
        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("서버 오류...");
        }

        return dataVO;
    }

}
