package Saludity.User.Config;

import Saludity.User.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Inicio Filter");
//        final String token = getTokenFromRequest(request);
//        final String username;
//
//        if (token==null)
//        {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        System.out.println("Segund Filtro");
//        username=jwtService.extractUsername(token);
//
//        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
//        {
//            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
//
//            if (jwtService.isvalidateToken(token, userDetails))
//            {
//                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities());
//
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String getTokenFromRequest(HttpServletRequest request) {
//        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
//
//        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))
//        {
//            return authHeader.substring(7);
//        }
//        return null;
//    }

//
//
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        System.out.println(authHeader);

        if (authHeader == null ) {
            filterChain.doFilter(request, response);
            System.out.println("Primer Bucle");
            return;
        }
        jwt = authHeader.substring(7);
        System.out.println(jwt);
        userEmail = jwtService.extractUsername(jwt);
        System.out.println(userEmail);

        System.out.println("Medio Filter");
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isvalidateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);

                System.out.println("termino filter1");
            }


        }
        System.out.println("termino filter 2");
        filterChain.doFilter(request, response);
    }
}





