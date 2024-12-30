//package com.example.demo.commom;
//
//import io.jsonwebtoken.*;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.util.StringUtils;
//
//import java.util.Date;
//import java.util.HashMap;
//
///**
// * Created with IntelliJ IDEA.
// * Description:
// * User:龙宝
// * Date:2023-11-07
// * Time:9:23
// */
//public class JWTUtils {
//    private static final long EXPIERS = 1000 * 60 * 60 * 24; //设置为一天
//
//    private static final String SECRET = "123";//设置密钥，每个公司都不一样~
//
//    // 内存中保存token
//    public final static HashMap<Integer, String> map = new HashMap<>();
//
//    /**
//     * 使用JWT生成token
//     * @param id
//     * @param username
//     * @return
//     */
//    public static String getTWTToken(Integer id,String username) {
//        if(map.get(id) != null){
//            return map.get(id);
//        }
//        String JWTToken = Jwts.builder()
//                //JWT头
//                .setHeaderParam("typ","JWT")
//                .setHeaderParam("alg","HS256")
//
//                //JWT主体
//                //这三个为可选
//                .setSubject("guli-user") //主题
//                .setIssuedAt(new Date()) //创建时间
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIERS)) // 过期时间
//
//                //主题中存储用户信息的部分
//                .claim("id",id)
//                .claim("username",username)
//
//                //设置签名哈希，防伪标志
//                .signWith(SignatureAlgorithm.HS256,SECRET.getBytes())
//                .compact();
//        map.put(id,JWTToken);
//        return JWTToken;
//    }
//
//    /**
//     * 验证token是否存在  +  有效
//     * @param JWTToken
//     * @return
//     */
//    public static boolean checkToken(String JWTToken) {
//        if(!StringUtils.hasLength(JWTToken)) {
//            return false;
//        }
//        try{
//            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(JWTToken);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 验证token是否存在  +  有效
//     * @param request
//     * @return
//     */
//    public static boolean checkToken(HttpServletRequest request) {
//        try {
//            String token = request.getHeader("token");
//            if(!StringUtils.hasLength(token)) {
//                return false;
//            }
//            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 根据token获取id
//     * @param request
//     * @return
//     */
//    public static Integer getMemberIdByJwtToekn(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        if(!StringUtils.hasLength(token)) {
//            return 0;
//        }
//        //解析token
//        Jwt<Header, Claims> claimsJwt = Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(token);
//        //获取载荷中的有效信息【用户信息】
//        Claims claims = claimsJwt.getBody();
//        return Integer.valueOf(claims.getId());
//    }
//
//}