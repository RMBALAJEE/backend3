package com.klu;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTManager {
	
	public final String SECRET_KEY = "eyJhcGkta2V5IjogImFzbmJ0dWVhb3J1ZW9idTQzNW5zdGF1In0K";
	public final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	
	public String generateToken(String name, String role, String email, String password)
	{
		Map<String, String> claims = new HashMap<String, String>();
		claims.put("name", encryptData(name));
		claims.put("role", encryptData(role));
		claims.put("email", encryptData(email));
		claims.put("password", encryptData(password));
						
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + 86400000))
				.signWith(key)
				.compact();			
	}
	
	public Map<String, String> validateToken(String token)
	{
		try
		{
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token)
					.getBody();

			Date expiration = claims.getExpiration();
			if(expiration == null || expiration.before(new Date()))
			{
				Map<String, String> resp = new HashMap<String, String>();
				resp.put("code", "404");
				resp.put("error", "Invalid JWT token");
				return resp;
			}
				
			
			Map<String, String> resp = new HashMap<String, String>();
			resp.put("name", decryptData(claims.get("name", String.class)));
			resp.put("role", decryptData(claims.get("role", String.class)));
			resp.put("email", decryptData(claims.get("email", String.class)));
			resp.put("password", decryptData(claims.get("password", String.class)));
			return resp;
		}catch (Exception e) 
		{
			Map<String, String> resp = new HashMap<String, String>();
			resp.put("code", "404");
			resp.put("error", e.getMessage());
			return resp;
		}
	}
	
	public String encryptData(String data)
	{
		try
		{
			MessageDigest MD5 = MessageDigest.getInstance("SHA-256");
			byte[] keyBytes = MD5.digest("BALAJEE".getBytes());
			SecretKey cryptoKey = new SecretKeySpec(keyBytes, 0, 16, "AES");
			
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, cryptoKey);
			byte[] encryptedBytes = cipher.doFinal(data.getBytes());
			return Base64.getEncoder().encodeToString(encryptedBytes);						
		}catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
	public String decryptData(String data)
	{
		try
		{
			MessageDigest MD5 = MessageDigest.getInstance("SHA-256");
			byte[] keyBytes = MD5.digest("BALAJEE".getBytes());
			SecretKey cryptoKey = new SecretKeySpec(keyBytes, 0, 16, "AES");
			
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, cryptoKey);
			byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(data));
			return new String(decryptedBytes);
		}catch(Exception e)
		{
			return e.getMessage();
		}
	}
}
