package cz.muni.fi.pa165.machrent.security;

import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.facade.RentalUserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vaclav.kadera on 17-Dec-16.
 */
@WebFilter( urlPatterns = {"/public/*"})
public class PublicFilter implements Filter {


    final static Logger log = LoggerFactory.getLogger(PublicFilter.class);

    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        log.error("protect filter");
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;

        HttpSession session = request.getSession(true);

        RentalUserDto userDto = (RentalUserDto) session.getAttribute("authUser");
        if (userDto == null) {
            response401(response);
            return;
        }
        RentalUserFacade rentalUserFacade = WebApplicationContextUtils.getWebApplicationContext(r.getServletContext()).getBean(RentalUserFacade.class);
        log.error(userDto.toString());

        request.setAttribute("authUser", userDto);
        chain.doFilter(request, response);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.sendRedirect("/pa165/authentication/authentication");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}