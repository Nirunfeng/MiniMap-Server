package com.minimap.controller;

import com.minimap.annotation.PointcutAnnotation;
import com.minimap.bo.UserBO;
import com.minimap.pojo.User;
import com.minimap.service.UserService;
import com.minimap.utils.FastDFSClient;
import com.minimap.utils.FileUtils;
import com.minimap.utils.MD5Utils;
import com.minimap.utils.ResponseResult;
import com.minimap.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户相关功能Controller
 * 1.判断登录还是注册
 * 2.登陆注册功能实现
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    /**
     * 引入service
     */
    @Autowired
    private UserService userService;

    @Autowired
    private FastDFSClient fastDFSClient;

    /**
     * 登陆注册业务
     * @param user
     * @return
     */
    @RequestMapping("/registerOrLogin")
    @ResponseBody
    @PointcutAnnotation
    public ResponseResult registerOrlogin(@RequestBody User user){
        User userResult=userService.findUserByUsername(user.getUsername());
        if(userResult!=null){
            //用户存在，进行登录
            /*进行密码校验*/
            try {
                if(!userResult.getPassword().equals(MD5Utils.getMD5Str(user.getPassword()))){
                    return ResponseResult.errorMsg("密码不正确");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            //如果用户名密码为空
            if("".equals(user.getUsername())||"".equals(user.getPassword())){
                return new ResponseResult(500,"用户名或密码不能为空",null);
            }else{
                //用户不存在，进行注册
                user.setNickname(user.getUsername());
                user.setQrcode("");
                try {
                    user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                user.setFaceImage("");
                user.setFaceImageBig("");
                userResult=userService.insertUser(user);
                if(userResult==null){
                    //注册失败
                    return new ResponseResult(500,"注册失败",null);
                }
            }
        }
        UserVO userVO=new UserVO();
        //将前端需要展示的信息copy到userVO中并返回到前端展示
        BeanUtils.copyProperties(userResult,userVO);
        return ResponseResult.ok(userVO);

    }

    /**
     * 修改昵称方法
     * @param user
     * @return
     */
    @RequestMapping("/setNickname")
    @ResponseBody
    public ResponseResult setNickname(@RequestBody User user){
        /*调用service层*/
        User userResult=userService.updateUserInfo(user);
        return ResponseResult.ok(userResult);
    }

    /**
     * 用户头像上传方法
     * @param userBO
     * @return
     */
    @RequestMapping("/uploadFaceBase64")
    @ResponseBody
    public ResponseResult setFaceImage(@RequestBody UserBO userBO) throws Exception {
        //获取前端传来的Base64url
        String base64Data=userBO.getFaceData();
        //将获取到的base64Data转换成文件对象
        String userFacePath="D:\\faceDatatmp"+userBO.getUserId()+"userFaceBase64.png";
        //使用FileUtils类转换base64为文件对象
        FileUtils.base64ToFile(userFacePath,base64Data);
        MultipartFile multipartFile =FileUtils.fileToMultipart(userFacePath);
        //获取fastDFS上传图片的路径
        String url=fastDFSClient.uploadBase64(multipartFile);
        //打印测试url
        System.out.println(url);

        /*定义上传图片的命名规则*/
        String thump="_150*150.";
        String[] arr=url.split("\\.");
        /*缩略图的命名thumpUrl*/
        String thumpUrl=arr[0]+thump+arr[1];

        /*更新用户头像*/
        User user=new User();
        user.setId(userBO.getUserId());
        //设置缩略图
        user.setFaceImage(thumpUrl);
        //设置大头像
        user.setFaceImageBig(url);
        User userResult=userService.updateUserInfo(user);
        return ResponseResult.ok(userResult);
    }
}
