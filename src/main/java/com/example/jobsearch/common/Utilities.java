package com.example.jobsearch.common;



import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


public class Utilities {
    private Utilities() {
    }

    public static String getSiteUrl(HttpServletRequest request) {
        String siteUrl = request.getRequestURL().toString();
        return siteUrl.replace(request.getServletPath(), "");
    }
}
