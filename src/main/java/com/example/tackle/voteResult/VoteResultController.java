package com.example.tackle.voteResult;


import com.example.tackle._enum.ApiResponseCode;
import com.example.tackle.dto.ResultDTO;
import com.example.tackle.exception.CustomException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/voteResult")
@Tag(name = "투표결과 API", description = "")
public class VoteResultController {

    private final VoteResultService voteResultService;

    @GetMapping("/info")
    public ResultDTO voteResultInfo(@RequestParam("resultId") Long resultId , Principal principal) {

        System.out.println("resultId = " + resultId);
        String memberEmail = principal.getName(); // 사용자 id값

        System.out.println("memberIdx = " + memberEmail);

        try{
            return ResultDTO.of(voteResultService.info(resultId, memberEmail), ApiResponseCode.SUCCESS.getCode(), "조회 성공", null);
        }catch (CustomException e){
            return ResultDTO.of(false, e.getCustomErrorCode().getStatusCode(), e.getDetailMessage(), null);
        }

    }

}
