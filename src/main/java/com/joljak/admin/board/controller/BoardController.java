package com.joljak.admin.board.controller;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/board")

@Api(value = "BoardController", description = "공지사항/FAQ/1대1 게시글관리 기능을 담은 Controller")

public class BoardController {

    private static Logger logger = LoggerFactory.getLogger(BoardController.class);


}
