package org.example.mockexam.configs.util;

import static org.example.mockexam.configs.util.ConstantUtil.AUTH_TOKEN_HEADER;

public class TokenUtil {
    public static String extractBearerToken(String header) {
        if (header != null && header.startsWith(AUTH_TOKEN_HEADER)) {
            return header.substring(AUTH_TOKEN_HEADER.length()).trim();
        }
        return null;
    }
}
