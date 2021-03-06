package zoom.meeting.web.signUp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zoom.meeting.domain.member.Member;
import zoom.meeting.domain.message.Message;
import zoom.meeting.domain.repositoryInterface.MemberRepository;
import zoom.meeting.domain.repositoryInterface.MessageRepository;
import zoom.meeting.web.signUp.form.SignUpForm;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;

    @GetMapping("/signUp")
    public String signUpForm(@ModelAttribute("signUpForm")SignUpForm form) {
        return "signUp/signUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute("signUpForm")SignUpForm form,
                         BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "signUp/signUpForm";
        }
//        if(hasBlank(form.getLoginId())){
//            log.info("공백 포함");
//            bindingResult.addError(new FieldError("form","loginId","ID에는 공백이 있을 수 없습니다."));
//        }
        if(form.getLoginId().contains(" ")){
            bindingResult.addError(new FieldError("form","loginId","ID에는 공백에 있을 수 없습니다."));
            return "signUp/signUpForm";
        }
        if(form.getNickName().contains(" ")){
            bindingResult.addError(new FieldError("form","nickName","닉네임에는 공백에 있을 수 없습니다."));
            return "signUp/signUpForm";
        }

        /**
         * 중복 확인 로직 구성 예정
         */

        // 닉네임 중복시
        if(memberRepository.allNickName().contains(form.getNickName())){
            log.info("nickName 중복 = {}", form.getNickName());
            bindingResult.addError(new FieldError(
                    "form","nickName",form.getNickName(),false,
                    new String[]{"nickNameOverlap"},null,"이미 존재하는 닉네임 입니다."));

            return "signUp/signUpForm";
        }
        // 로그인 id 중복시
        if(memberRepository.allLoginId().contains(form.getLoginId())){
            log.info("ID 중복 = {}", form.getLoginId());
            bindingResult.addError(
                    new FieldError("form","loginId",form.getLoginId(),false,
                            new String[]{"loginIdOverlap"},null,"이미 존재하는 id 입니다."));
            return "signUp/signUpForm";
        }

        // 로그인 id와 pw 중복시
        if(form.getLoginId().equals(form.getPassword())){
            log.info("로그인 id, pw 동일");
            bindingResult.addError(
                    new FieldError("form","password",null,false,
                            new String[]{"IdPasswordOverlap"},null,"ID와  PW가 동일합니다."));
            return "signUp/signUpForm";
        }


        //success signUp
        memberRepository.save(new Member(
                form.getLoginId(),
                form.getPassword(),
                form.getName(),
                form.getNickName()));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");
        String time = now.format(formatter);

        Message message = new Message("운영자", form.getNickName(),
                time,
                "회원가입을 환영합니다.",
                "Face talk 회원가입을 환영합니다!!  좋은 하루되시길 바라며 많은 이용 부탁드립니다~~!!",
                "N");
        messageRepository.send(message);

        return "redirect:/login";
    }

//    private static boolean hasBlank(String str){
//        int strLen;
//        if(str==null || (strLen = str.length()) == 0){
//            return true;
//        }
//        for (int i = 0; i < strLen; i++) {
//            if((!Character.isWhitespace(str.charAt(i)))){
//                return false;
//            }
//        }
//        return true;
//    }

}
