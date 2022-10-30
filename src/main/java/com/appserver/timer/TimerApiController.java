package com.appserver.timer;

import com.appserver.member.Member;
import com.appserver.member.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TimerApiController {
    private final MemberService memberService;

    @PostMapping("/api/timer/{userId}")
    public SetTimerResponse update(@PathVariable Long userId, @RequestBody @Valid SetTimerRequest request) {
        Member member = memberService.saveTimer(userId, request.time);
        return new SetTimerResponse(member.getId(), request.time);
    }

    @Data
    static class SetTimerRequest {
        @NotEmpty
        private Long time;
    }

    @Data
    static class SetTimerResponse {
        @NotEmpty
        private Long id;
        @NotEmpty
        private Long time;

        public SetTimerResponse(Long id, Long time) {
            this.id = id;
            this.time = time;
        }
    }
}
