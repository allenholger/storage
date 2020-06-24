package com.example.storage.controller;

import com.example.storage.model.response.ApiResponse;
import com.example.storage.util.ImageVerificationCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 本类是验证码控制器
 *  目前仅包括图片验证码
 *  以后会包含短信验证码、动态图片验证码、滑动验证码等验证码方式
 */
@Api("验证码的前端控制器")
@RestController
@RequestMapping("verificationCode")
@Slf4j
public class VerificationCodeController {

    @ApiOperation(value = "获取图片验证码", notes = "获取图片验证码")
    @ApiImplicitParam(name = "HttpServletResponse", required = true)
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ApiResponse getImage(HttpServletResponse response){
        Random random = new Random();
        //生产验证码
        String code = ImageVerificationCodeUtils.generateString(random, 6);
        BufferedImage bufferedImage = ImageVerificationCodeUtils.generateImage(random, 140, 100, code);
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, MediaType.IMAGE_JPEG_VALUE, outputStream);
            outputStream.flush();
            return ApiResponse.success(code);
        } catch (IOException e) {
            log.info("验证码写入到输出流中失败！ " + e.getMessage());
            return ApiResponse.fail("获取验证码失败, 请重试！");
        }
    }

}
