package readingList.common.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import readingList.domain.SysUserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        SysUserEntity userEntity = (SysUserEntity) authentication.getPrincipal();
        System.out.println("登录用户：" + userEntity.getUsername());
        // 设置session
        request.getSession().setAttribute("username", userEntity.getUsername());
        request.getSession().setAttribute("userid", userEntity.getId());

        this.setDefaultTargetUrl("/readingList");

        super.onAuthenticationSuccess(request,response,authentication);
    }

}
