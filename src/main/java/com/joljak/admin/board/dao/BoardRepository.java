package com.joljak.admin.board.dao;

import com.joljak.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardRepository {
    public List<NoticeDto> selNoticeList(SearchDto dto);

    public NoticeDto selNotice(NoticeDto dto);

    public int insNoticeTrx(NoticeTrxDto dto);
}
