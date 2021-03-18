package com.joljak.admin.board.service;

import com.joljak.admin.board.dao.BoardRepository;
import com.joljak.dto.ReqBoardDto;
import com.joljak.base.exception.BusinessException;
import com.joljak.dto.NoticeDto;
import com.joljak.dto.NoticeTrxDto;
import com.joljak.dto.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.joljak.base.error.ErrorCode.UPDATE_ERROR;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public List<NoticeDto> getNoticeList(SearchDto dto) {
        return boardRepository.selNoticeList(dto);
    }

    @Override
    public NoticeDto getNotice(ReqBoardDto dto) {
        NoticeDto noticeDto = new NoticeDto();
        noticeDto.setNoticeId(dto.getBoardId());
        return boardRepository.selNotice(noticeDto);
    }

    @Override
    public void readNotice(NoticeTrxDto dto) {
        int x = boardRepository.insNoticeTrx(dto);
        if (x == 0) {
            throw new BusinessException(UPDATE_ERROR, "읽음처리");
        }
    }

}
