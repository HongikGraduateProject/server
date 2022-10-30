package com.example.appserver.oauth;

import com.example.appserver.login.JwtTokenProvider;
import com.example.appserver.member.Member;
import com.example.appserver.member.MemberService;
import com.example.appserver.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final GoogleOauth googleOauth;
    private final NaverOauth naverOauth;
    private final HttpServletResponse response;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public void request(SocialType socialType) throws IOException {
        String redirectURL;
        switch (socialType){
            case GOOGLE:{
                redirectURL = googleOauth.getOauthRedirectURL();
                break;
            }
            case NAVER:{
                redirectURL = naverOauth.getOauthRedirectURL();
                break;
            }
            default:{
                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
            }
        }
        response.sendRedirect(redirectURL);
    }

    public GetSocialOAuthRes oAuthLogin(SocialType socialType, String code) throws IOException {
        switch (socialType) {
            case GOOGLE:{
                ResponseEntity<String> accessTokenResponse = googleOauth.requestAccessToken(code);
                GoogleOAuthToken oAuthToken = googleOauth.getAccessToken(accessTokenResponse);
                ResponseEntity<String> userInfoResponse = googleOauth.requestUserInfo(oAuthToken);
                GoogleUser googleUser = googleOauth.getUserInfo(userInfoResponse);
                if (memberService.findByEmail(googleUser.getEmail()).isPresent()){
                    Optional<Member> member = memberService.findByEmail(googleUser.getEmail());
                    Long id = member.get().getId();
                    String jwtToken = jwtTokenProvider.createToken(googleUser.getEmail());
                    GetSocialOAuthRes getSocialOAuthRes = new GetSocialOAuthRes(jwtToken, id, googleUser.getName(), googleUser.getEmail(), oAuthToken.getAccess_token(), oAuthToken.getToken_type());
                    return getSocialOAuthRes;
                }
                else {
                    Member member = Member.builder()
                            .username(googleUser.getName())
                            .email(googleUser.getEmail())
                            .password(null)
                            .role(Role.USER)
                            .build();
                    Long id = memberService.join(member);
                    String jwtToken = jwtTokenProvider.createToken(googleUser.getEmail());
                    GetSocialOAuthRes getSocialOAuthRes = new GetSocialOAuthRes(jwtToken, id, googleUser.getName(), googleUser.getEmail(), oAuthToken.getAccess_token(), oAuthToken.getToken_type());
                    return getSocialOAuthRes;
                }
            }
            case NAVER:{
                ResponseEntity<String> accessTokenResponse = naverOauth.requestAccessToken(code);
                NaverOAuthToken oAuthToken = naverOauth.getAccessToken(accessTokenResponse);
                ResponseEntity<String> userInfoResponse = naverOauth.requestUserInfo(oAuthToken);
                NaverUser naverUser = naverOauth.getUserInfo(userInfoResponse);
                if (memberService.findByEmail(naverUser.getEmail()).isPresent()){
                    Optional<Member> member = memberService.findByEmail(naverUser.getEmail());
                    Long id = member.get().getId();
                    String jwtToken = jwtTokenProvider.createToken(naverUser.getEmail());
                    GetSocialOAuthRes getSocialOAuthRes = new GetSocialOAuthRes(jwtToken, id, naverUser.getNickname(), naverUser.getEmail(), oAuthToken.getAccess_token(), oAuthToken.getToken_type());
                    return getSocialOAuthRes;
                }
                else {
                    Member member = Member.builder()
                            .username(naverUser.getNickname())
                            .email(naverUser.getEmail())
                            .password(null)
                            .role(Role.USER)
                            .build();
                    Long id = memberService.join(member);
                    String jwtToken = jwtTokenProvider.createToken(naverUser.getEmail());
                    GetSocialOAuthRes getSocialOAuthRes = new GetSocialOAuthRes(jwtToken, id, naverUser.getNickname(), naverUser.getEmail(), oAuthToken.getAccess_token(), oAuthToken.getToken_type());
                    return getSocialOAuthRes;
                }
            }
            default:{
                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
            }
        }
    }
}
