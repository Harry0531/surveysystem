package bit.ss.surveysystem.modules.sys.Controller;

import bit.ss.surveysystem.common.web.BaseApi;
import bit.ss.surveysystem.common.web.MsgType;
import bit.ss.surveysystem.modules.sys.Entity.UserInfoEntity;
import bit.ss.surveysystem.modules.sys.Service.UserService;
import bit.ss.surveysystem.modules.sys.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/sys/user")
public class UserController extends BaseApi {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "isNameUsed", method = RequestMethod.POST)
    @ResponseBody
    public Object isNameUnused(@RequestParam("userName") String userName) {
        try {
            if (userService.isNameUnused(userName))
                return retMsg.Set(MsgType.SUCCESS, true);
            return retMsg.Set(MsgType.SUCCESS, false);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody UserEntity user) {
        try {
            UserEntity result = userService.login(user);
            if (result!= null){
                return retMsg.Set(MsgType.SUCCESS, result);
            }
             else{
                return retMsg.Set(MsgType.ERROR, "不存在此用户");
                }
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(@RequestBody UserEntity user) {
        try {
            if (userService.register(user))
                return retMsg.Set(MsgType.SUCCESS, true);
            return retMsg.Set(MsgType.SUCCESS, false);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }

    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserInfo(@RequestBody UserInfoEntity user) {
        try {
            if (userService.updateUserInfo(user))
                return retMsg.Set(MsgType.SUCCESS, true);
            return retMsg.Set(MsgType.SUCCESS, false);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }


    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserInfo(@RequestBody UserInfoEntity user) {
        try {
            UserInfoEntity userInfoEntity = userService.getUserInfoById(user);
            return retMsg.Set(MsgType.SUCCESS,userInfoEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }
}
