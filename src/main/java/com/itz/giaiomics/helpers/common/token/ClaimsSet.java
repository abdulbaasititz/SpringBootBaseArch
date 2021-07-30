package com.itz.giaiomics.helpers.common.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itz.giaiomics.helpers.utils.JwtUtil;
import com.itz.giaiomics.models.UserMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClaimsSet {
    @Autowired
    JwtUtil jwtUtil;

    public ClaimsSet() {
        super();
    }

//    public Boolean setClaimsDetailsFromToken(String token) throws Exception {
//        ClaimsDao claimsDao = getClaimsDetails(token);
//        jwtUtil.setOrgDetails(claimsDao.getPlt(), claimsDao.getEid());
//        return true;
//    }

    public static Map<String, Object> setClaimsDetails(UserMaster userDao) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", userDao.getDesignation());
        claims.put("eid", userDao.getUserName());
        claims.put("unt", userDao.getBaseCurrency());
        return claims;
    }

    public ClaimsDao getClaimsDetailsAfterSet(String token) throws Exception {
        ClaimsDao claimsDao = getClaimsDetails(token);
        jwtUtil.setOrgDetails(claimsDao.getRol(), claimsDao.getEid());
        return claimsDao;
    }

    public ClaimsDao getClaimsDetailsAfterSetNewPlt(String token, String plant) throws Exception {
        ClaimsDao claimsDao = getClaimsDetails(token);
        jwtUtil.setOrgDetails(plant, claimsDao.getEid());
        return claimsDao;
    }

    public ClaimsDao getClaimsDetails(String token) throws Exception {
        try {
            java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
            token = token.substring(7);
            String[] parts = token.split("\\.");
            String payloadJson = new String(decoder.decode(parts[1]));
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(payloadJson, ClaimsDao.class);
        } catch (JsonProcessingException e) {
            throw new Exception("Json error");
        }
    }
}
