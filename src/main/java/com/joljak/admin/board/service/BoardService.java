package com.joljak.admin.board.service;

import com.joljak.dto.ReqBoardDto;
import com.joljak.dto.NoticeDto;
import com.joljak.dto.NoticeTrxDto;
import com.joljak.dto.SearchDto;

import java.util.List;

public interface BoardService {
    //공지사항
    public List<NoticeDto> getNoticeList(SearchDto dto);

    public NoticeDto getNotice(ReqBoardDto dto);

    public void readNotice(NoticeTrxDto dto);

}
