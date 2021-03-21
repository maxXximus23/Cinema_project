package com.dut.CinemaProject.security.jwt;

import com.dut.CinemaProject.dao.domain.JwtBlacklist;
import com.dut.CinemaProject.dao.repos.JwtBlacklistRepository;
import com.dut.CinemaProject.exceptions.EmailAlreadyExistsException;
import com.dut.CinemaProject.exceptions.JwtAuthenticationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@AllArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);

        JwtBlacklist jwtBlacklist = jwtTokenProvider.findTokenInBlacklist(token);

        if(jwtBlacklist == null){
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);

                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(req, res);
        }else {
            System.out.println("Token is in blacklist");
            throw new JwtAuthenticationException("Invalid token");
        }



    }
}
